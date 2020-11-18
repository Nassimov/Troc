package troc.project.troc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
import troc.project.troc.repositories.FileTrocRepository;
import troc.project.troc.repositories.ListMsgRepository;

@Controller
public class GenerateXMLController {
    public static final String xmlFilePath = "D:\\M1 DSC\\Document\\TrocProject\\Troc\\troc\\src\\main\\resources\\files\\sndFiles\\xmlfile.xml";
    @Autowired
    FileTrocRepository fileTrocRepository;
    @Autowired
    ListMsgRepository listMsgRepository;

    @RequestMapping(value = "/generateXML", method = RequestMethod.GET)
    public String generateXML() {
        FileTroc lastFIle = fileTrocRepository.findTopByOrderByIdFileTrocDesc();
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

            attr.setValue(lastFIle.getIdFileTroc() + "");
            header.setAttributeNode(attr);

            Element nbMsg = document.createElement("nbMsg");
            nbMsg.appendChild(document.createTextNode(lastFIle.getHeader().getNbrMsg() + ""));
            header.appendChild(nbMsg);

            Element transmitter = document.createElement("transmitter");
            transmitter.appendChild(document.createTextNode(lastFIle.getHeader().getTransmitter().getName() + " "
                    + lastFIle.getHeader().getTransmitter().getLastName()));
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
                authRef.appendChild(document.createTextNode(lastFIle.getHeader().getAuth().getAuthRef()));
                header.appendChild(authRef);

                Element authDate = document.createElement("authDate");
                authDate.appendChild(document.createTextNode(lastFIle.getHeader().getAuth().getAuthDate() + ""));
                header.appendChild(authDate);
            }

            // Body
            Element listMsg = document.createElement("listMsg");
            body.appendChild(listMsg);
            if (lastFIle.getHeader().getNbrMsg() > 0) {
                List<ListMsg> listDesMessages = listMsgRepository.findAllByMsgList(lastFIle.getMsgList());

                for (ListMsg listMsg2 : listDesMessages) {
                    Element message = document.createElement("message");
                    listMsg.appendChild(message);
                    Attr idMsg = document.createAttribute("idMsg");

                    idMsg.setValue(listMsg2.getMessage().getIdMessage() + "");
                    message.setAttributeNode(idMsg);

                    Element dateMsg = document.createElement("dateMsg");
                    dateMsg.appendChild(document.createTextNode(listMsg2.getMessage().getMessageDate() + ""));
                    message.appendChild(dateMsg);
                    Element validityDuration = document.createElement("validityDuration");
                    validityDuration
                            .appendChild(document.createTextNode(listMsg2.getMessage().getValidityDuration() + ""));
                    message.appendChild(validityDuration);

                    // Message Type

                    if (listMsg2.getMessage().getAuthRequest() != null) {
                        Element authRequest = document.createElement("authRequest");
                        authRequest.appendChild(document.createTextNode(listMsg2.getMessage().getAuthRequest() + ""));
                        message.appendChild(authRequest);
                    }
                    if (listMsg2.getMessage().getAuth() != null) {
                        Element auth = document.createElement("auth");
                        message.appendChild(auth);
                        Element authReff = document.createElement("authRef");
                        authReff.appendChild(
                                document.createTextNode(listMsg2.getMessage().getAuth().getAuthRef() + ""));
                        auth.appendChild(authReff);
                        Element authDatee = document.createElement("authDate");
                        authDatee.appendChild(document.createTextNode(listMsg2.getMessage().getAuth().get + ""));
                        auth.appendChild(authDatee);
                    }

                }

            }
            // Transformation
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));

            transformer.transform(domSource, streamResult);

            System.out.println("Fichier XML créé");

        } catch (

        ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

        return "redirect:/";
    }

}