package troc.project.troc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import troc.project.troc.model.FileTroc;
import troc.project.troc.model.ListMsg;
import troc.project.troc.model.Message;
import troc.project.troc.model.CatObjects;
import troc.project.troc.model.ObjectRCVList;
import troc.project.troc.model.ObjectSNDList;

import troc.project.troc.repositories.CatObjectRepository;
import troc.project.troc.repositories.FileTrocRepository;
import troc.project.troc.repositories.ListMsgRepository;
import troc.project.troc.repositories.MessageRepository;
import troc.project.troc.repositories.ObjectRCVListRepository;
import troc.project.troc.repositories.ObjectSNDListRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class GenerateXMLController {
        @Autowired
        FileTrocRepository fileTrocRepository;
        @Autowired
        ListMsgRepository listMsgRepository;
        @Autowired
        CatObjectRepository catObjectRepository;
        @Autowired
        ObjectRCVListRepository objectRCVListRepository;
        @Autowired
        ObjectSNDListRepository objectSNDListRepository;

        @RequestMapping(value = "/generateXML", method = RequestMethod.GET)
        public String generateXML(Model m) {
                FileTroc lastFIle = fileTrocRepository.findTopByOrderByIdFileTrocDesc();
                lastFIle.setIdf(lastFIle.getIdFileTroc());
                fileTrocRepository.save(lastFIle);
                try {

                        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

                        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

                        Document document = documentBuilder.newDocument();

                        // file element
                        Element file = document.createElement("file");
                        document.appendChild(file);

                        // header element
                        Element header = document.createElement("header");

                        file.appendChild(header);

                        Element body = document.createElement("body");

                        file.appendChild(body);

                        Attr attr = document.createAttribute("idF");

                        attr.setValue(lastFIle.getIdf() + "");
                        header.setAttributeNode(attr);

                        Element nbMsg = document.createElement("nbMsg");
                        nbMsg.appendChild(document.createTextNode(lastFIle.getHeader().getNbrMsg() + ""));
                        header.appendChild(nbMsg);

                        Element transmitter = document.createElement("transmitter");
                        transmitter.appendChild(document.createTextNode(lastFIle.getHeader().getTransmitter().getName()
                                        + " " + lastFIle.getHeader().getTransmitter().getLastName()));
                        header.appendChild(transmitter);
                        Attr idUserT = document.createAttribute("idUser");

                        idUserT.setValue(lastFIle.getHeader().getTransmitter().getIdUser() + "");
                        transmitter.setAttributeNode(idUserT);
                        Element receiver = document.createElement("receiver");
                        receiver.appendChild(document.createTextNode(lastFIle.getHeader().getReceiver().getName() + " "
                                        + lastFIle.getHeader().getReceiver().getLastName()));
                        Attr idUserR = document.createAttribute("idUser");

                        idUserR.setValue(lastFIle.getHeader().getReceiver().getIdUser() + "");
                        receiver.setAttributeNode(idUserR);
                        header.appendChild(receiver);

                        if (lastFIle.getHeader().getAuth() != null) {
                                Element authRef = document.createElement("authRef");
                                authRef.appendChild(
                                                document.createTextNode(lastFIle.getHeader().getAuth().getAuthRef()));
                                header.appendChild(authRef);

                                Element authDate = document.createElement("authDate");
                                authDate.appendChild(document
                                                .createTextNode(lastFIle.getHeader().getAuth().getAuthDate() + ""));
                                header.appendChild(authDate);
                        }

                        // Body
                        Element listMsg = document.createElement("listMsg");
                        body.appendChild(listMsg);
                        if (lastFIle.getHeader().getNbrMsg() > 0) {
                                List<ListMsg> listDesMessages = listMsgRepository
                                                .findAllByMsgList(lastFIle.getMsgList());

                                for (ListMsg listMsg2 : listDesMessages) {
                                        Element message = document.createElement("message");
                                        listMsg.appendChild(message);
                                        Attr idMsg = document.createAttribute("idMsg");

                                        idMsg.setValue(listMsg2.getMessage().getIdMessage() + "");
                                        message.setAttributeNode(idMsg);

                                        Element dateMsg = document.createElement("dateMsg");
                                        dateMsg.appendChild(document
                                                        .createTextNode(listMsg2.getMessage().getMessageDate() + ""));
                                        message.appendChild(dateMsg);
                                        Element validityDuration = document.createElement("validityDuration");
                                        validityDuration.appendChild(document.createTextNode(
                                                        listMsg2.getMessage().getValidityDuration() + ""));
                                        message.appendChild(validityDuration);

                                        // Message Type

                                        if (listMsg2.getMessage().getAuthRequest() != null) {
                                                Element authRequest = document.createElement("authRequest");
                                                authRequest.appendChild(document.createTextNode(
                                                                listMsg2.getMessage().getAuthRequest() + ""));
                                                message.appendChild(authRequest);
                                        }
                                        if (listMsg2.getMessage().getAuth() != null) {
                                                Element auth = document.createElement("auth");
                                                message.appendChild(auth);
                                                Element authReff = document.createElement("authRef");
                                                authReff.appendChild(document.createTextNode(
                                                                listMsg2.getMessage().getAuth().getAuthRef() + ""));
                                                auth.appendChild(authReff);
                                                Element authDatee = document.createElement("authDate");
                                                authDatee.appendChild(document.createTextNode(
                                                                listMsg2.getMessage().getAuth().getAuthDate() + ""));
                                                auth.appendChild(authDatee);
                                        }
                                        if (listMsg2.getMessage().getAccept() != null) {
                                                Element accept = document.createElement("accept");
                                                message.appendChild(accept);
                                                Element idPropositionMsg = document.createElement("idPropositionMsg");
                                                idPropositionMsg.appendChild(document.createTextNode(
                                                                listMsg2.getMessage().getAccept().getIdPropositionMsg()
                                                                                + ""));
                                                accept.appendChild(idPropositionMsg);
                                        }
                                        if (listMsg2.getMessage().getDeny() != null) {
                                                Element deny = document.createElement("deny");
                                                message.appendChild(deny);
                                                Element idPropositionMsg2 = document.createElement("idPropositionMsg");
                                                idPropositionMsg2.appendChild(document.createTextNode(
                                                                listMsg2.getMessage().getDeny().getIdPropositionMsg()
                                                                                + ""));
                                                deny.appendChild(idPropositionMsg2);
                                                Element reason = document.createElement("reason");
                                                reason.appendChild(document.createTextNode(
                                                                listMsg2.getMessage().getDeny().getReason() + ""));
                                                deny.appendChild(reason);
                                        }
                                        if (listMsg2.getMessage().getErrorMessage() != null) {
                                                Element errorMsg = document.createElement("errorMsg");
                                                errorMsg.appendChild(document.createTextNode(listMsg2.getMessage()
                                                                .getErrorMessage().getIdErrorMessage() + ""));
                                                message.appendChild(errorMsg);
                                                Attr idError = document.createAttribute("idError");

                                                idError.setValue(listMsg2.getMessage().getErrorMessage()
                                                                .getIdErrorMessage() + "");
                                                errorMsg.setAttributeNode(idError);
                                                Attr idMsg12 = document.createAttribute("idMsg");

                                                idMsg12.setValue(listMsg2.getMessage().getIdMessage() + "");
                                                errorMsg.setAttributeNode(idMsg12);
                                        }

                                        if (listMsg2.getMessage().getNoCat() != null) {
                                                Element noCat = document.createElement("noCat");
                                                message.appendChild(noCat);
                                                Element idCatRequestMsg = document.createElement("idCatRequestMsg");
                                                idCatRequestMsg.appendChild(document.createTextNode(
                                                                listMsg2.getMessage().getNoCat().getIdCatRequestMsg()
                                                                                + ""));
                                                noCat.appendChild(idCatRequestMsg);
                                                Element reason2 = document.createElement("reason");
                                                reason2.appendChild(document.createTextNode(
                                                                listMsg2.getMessage().getNoCat().getReason() + ""));
                                                noCat.appendChild(reason2);
                                        }

                                        if (listMsg2.getMessage().getRequest() != null) {
                                                Element request = document.createElement("request");
                                                message.appendChild(request);
                                                Element rcvObjectList = document.createElement("rcvObjectList");
                                                rcvObjectList.appendChild(document.createTextNode(
                                                                listMsg2.getMessage().getRequest().getRcvObjectList()
                                                                                .getIdRcvObjectList() + ""));
                                                request.appendChild(rcvObjectList);
                                                Element idPrevMsg = document.createElement("idPrevMsg");
                                                idPrevMsg.appendChild(document.createTextNode(
                                                                listMsg2.getMessage().getRequest().getIdPrevMsg()
                                                                                + ""));
                                                request.appendChild(idPrevMsg);
                                        }
                                        if (listMsg2.getMessage().getCatRequest() != null) {
                                                Element catRequest = document.createElement("catRequest");
                                                catRequest.appendChild(document.createTextNode(
                                                                listMsg2.getMessage().getCatRequest() + ""));
                                                message.appendChild(catRequest);
                                        }
                                        if (listMsg2.getMessage().getDonation() != null) {
                                                Element donation = document.createElement("donation");
                                                message.appendChild(donation);
                                                Element sndObjectList = document.createElement("sndObjectList");
                                                sndObjectList.appendChild(document.createTextNode(
                                                                listMsg2.getMessage().getDonation().getSndObjectList()
                                                                                .getIdSndObjectList() + ""));
                                                donation.appendChild(sndObjectList);
                                                Element idPrevMsg = document.createElement("idPrevMsg");
                                                idPrevMsg.appendChild(document.createTextNode(
                                                                listMsg2.getMessage().getDonation().getIdPrevMsg()
                                                                                + ""));
                                                donation.appendChild(idPrevMsg);
                                        }

                                        if (listMsg2.getMessage().getBarter() != null) {
                                                Element barter = document.createElement("barter");
                                                message.appendChild(barter);

                                                Element BarterIdPrevMsg = document.createElement("idPrevMsg");
                                                BarterIdPrevMsg.appendChild(document.createTextNode(
                                                                listMsg2.getMessage().getBarter().getIdPrevMsg() + ""));
                                                barter.appendChild(BarterIdPrevMsg);

                                                Element BarterRcvObjectList = document.createElement("rcvObjectList");
                                                barter.appendChild(BarterRcvObjectList);
                                                Element BarterSndObjectList = document.createElement("sndObjectList");
                                                barter.appendChild(BarterSndObjectList);

                                                List<ObjectRCVList> associationObjetRcv = objectRCVListRepository
                                                                .findAllByRcvObjectList(listMsg2.getMessage()
                                                                                .getBarter().getRcvObjectList());

                                                List<ObjectSNDList> associationObjetSnd = objectSNDListRepository
                                                                .findAllBySndObjectList(listMsg2.getMessage()
                                                                                .getBarter().getSndObjectList());

                                                if (associationObjetRcv.size() > 0) {

                                                        for (ObjectRCVList rcvElement : associationObjetRcv) {
                                                                Element objectRcv = document.createElement("object");
                                                                BarterRcvObjectList.appendChild(objectRcv);
                                                                Attr idObject = document.createAttribute("idObject");

                                                                idObject.setValue(rcvElement.getObject().getObjectId()
                                                                                + "");
                                                                objectRcv.setAttributeNode(idObject);

                                                                Element objectNameRcv = document
                                                                                .createElement("objectName");
                                                                objectNameRcv.appendChild(document.createTextNode(
                                                                                rcvElement.getObject().getObjectName()
                                                                                                + ""));
                                                                objectRcv.appendChild(objectNameRcv);

                                                                Element objectDetailsRcv = document
                                                                                .createElement("objectDetails");
                                                                objectDetailsRcv.appendChild(document
                                                                                .createTextNode(rcvElement.getObject()
                                                                                                .getObjectDetails()
                                                                                                + ""));
                                                                objectRcv.appendChild(objectDetailsRcv);

                                                                Element objectImageRcv = document
                                                                                .createElement("objectImage");
                                                                objectImageRcv.appendChild(document.createTextNode(
                                                                                rcvElement.getObject().getObjectImage()
                                                                                                + ""));
                                                                objectRcv.appendChild(objectImageRcv);
                                                        }

                                                }
                                                if (associationObjetSnd.size() > 0) {
                                                        for (ObjectSNDList sndElement : associationObjetSnd) {
                                                                Element objectSnd = document.createElement("object");
                                                                BarterSndObjectList.appendChild(objectSnd);
                                                                Attr idObjectSND = document.createAttribute("idObject");

                                                                idObjectSND.setValue(
                                                                                sndElement.getObject().getObjectId()
                                                                                                + "");
                                                                objectSnd.setAttributeNode(idObjectSND);

                                                                Element objectNameSnd = document
                                                                                .createElement("objectName");
                                                                objectNameSnd.appendChild(document.createTextNode(
                                                                                sndElement.getObject().getObjectName()
                                                                                                + ""));
                                                                objectSnd.appendChild(objectNameSnd);

                                                                Element objectDetailsSnd = document
                                                                                .createElement("objectDetails");
                                                                objectDetailsSnd.appendChild(document
                                                                                .createTextNode(sndElement.getObject()
                                                                                                .getObjectDetails()
                                                                                                + ""));
                                                                objectSnd.appendChild(objectDetailsSnd);

                                                                Element objectImageSnd = document
                                                                                .createElement("objectImage");
                                                                objectImageSnd.appendChild(document.createTextNode(
                                                                                sndElement.getObject().getObjectImage()
                                                                                                + ""));
                                                                objectSnd.appendChild(objectImageSnd);
                                                        }
                                                }

                                        }

                                        if (listMsg2.getMessage().getCat() != null) {
                                                Element cat = document.createElement("cat");
                                                message.appendChild(cat);
                                                Element idCatRequestMsg = document.createElement("idCatRequestMsg");
                                                idCatRequestMsg.appendChild(document.createTextNode(
                                                                listMsg2.getMessage().getCat().getIdCat() + ""));
                                                cat.appendChild(idCatRequestMsg);
                                                Element catDate = document.createElement("catDate");
                                                catDate.appendChild(document.createTextNode(
                                                                listMsg2.getMessage().getCat().getCatDate() + ""));
                                                cat.appendChild(catDate);
                                                List<CatObjects> associationCatObj = catObjectRepository
                                                                .findAllByCat(listMsg2.getMessage().getCat());
                                                if (associationCatObj.size() > 0) {
                                                        for (CatObjects association : associationCatObj) {
                                                                Element object = document.createElement("object");
                                                                cat.appendChild(object);
                                                                Attr idObject = document.createAttribute("idObject");

                                                                idObject.setValue(association.getObject().getObjectId()
                                                                                + "");
                                                                object.setAttributeNode(idObject);

                                                                Element objectName = document
                                                                                .createElement("objectName");
                                                                objectName.appendChild(document.createTextNode(
                                                                                association.getObject().getObjectName()
                                                                                                + ""));
                                                                object.appendChild(objectName);

                                                                Element objectDetails = document
                                                                                .createElement("objectDetails");
                                                                objectDetails.appendChild(document
                                                                                .createTextNode(association.getObject()
                                                                                                .getObjectDetails()
                                                                                                + ""));
                                                                object.appendChild(objectDetails);

                                                                Element objectImage = document
                                                                                .createElement("objectImage");
                                                                objectImage.appendChild(document.createTextNode(
                                                                                association.getObject().getObjectImage()
                                                                                                + ""));
                                                                object.appendChild(objectImage);

                                                        }
                                                }
                                        }
                                }

                        }
                        // Transformation
                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        DOMSource domSource = new DOMSource(document);
                        String fileApplicationServerPath = "/home/ramez/M1/DN/Troc/troc/src/main/resources/files/sndFiles/De"
                                        + lastFIle.getHeader().getTransmitter().getName() + "  "
                                        + lastFIle.getHeader().getTransmitter().getLastName() + " Vers "
                                        + lastFIle.getHeader().getReceiver().getName() + " "
                                        + lastFIle.getHeader().getReceiver().getLastName() + ".xml";
                        StreamResult streamResult = new StreamResult(new File(fileApplicationServerPath));

                        transformer.transform(domSource, streamResult);
                        Path path = Paths.get(fileApplicationServerPath);

                        try {

                                // size of a file (in bytes)
                                long fileSizeInBytes = Files.size(path);
                                if (fileSizeInBytes > 5120) {
                                        // Delete generated file and dete last file
                                        fileTrocRepository.delete(lastFIle);
                                        Files.delete(path);

                                        m.addAttribute("fileSize", fileSizeInBytes / 1024);

                                        return "warning";
                                }

                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                } catch (

                ParserConfigurationException pce) {
                        pce.printStackTrace();
                } catch (TransformerException tfe) {
                        tfe.printStackTrace();
                }

                // gérer la taille du fichier:

                return "successGeneration";
        }

}