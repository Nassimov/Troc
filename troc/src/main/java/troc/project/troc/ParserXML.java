package troc.project.troc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.text.ParseException;

import lombok.Data;

/**
 * @author komah Mohamed * @version 1.0
 */
@Data
public class ParserXML {

    public ParserXML() {
    }

    public static DocumentBuilderFactory racineFichier() {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        return factory;
    }

    /**
     * @param fichier
     * @return l'element buider
     */
    public static Element builder(String fichier) {
        String myPath = "\\src\\main\\resources\\dossierXML\\";
        // String myPath = "/src/main/resources/dossierXML";

        String fileLocation = System.getProperty("user.dir") + myPath;

        try {
            DocumentBuilder builder = racineFichier().newDocumentBuilder();
            final Document document = builder.parse(new File(fileLocation + fichier));
            Element enteteFichier = document.getDocumentElement();
            return enteteFichier;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param fichier
     * @return IdF du fichier XML passer en paramettre
     */
    public static int recupIdF(String fichier) throws Exception {
        Element rootFichier = builder(fichier);
        String idFichier = rootFichier.getElementsByTagName("header").item(0).getAttributes().item(0).getTextContent();
        return Integer.parseInt(idFichier);
    }

    /**
     * @author ALSIBAI, AYADA
     * @param fichier
     * @return IdUser de emmitteur
     */
    public static int IdUserTra(String fichier) {
        Element rootFichier = builder(fichier);
        String idUe = rootFichier.getElementsByTagName("transmitter").item(0).getAttributes().item(0).getTextContent();
        return Integer.parseInt(idUe);
    }

    /**
     * 
     * @param fichier
     * @return le transmitter du fichier
     * @throws ParseException
     * @throws NullPointerException
     */
    public static String recupererTransmitter(String fichier) throws Exception {
        String nameTransmitter;
        Element rootFichier = builder(fichier);
        nameTransmitter = rootFichier.getElementsByTagName("transmitter").item(0).getTextContent();
        return nameTransmitter;
    }

    public static int recupNbrMsg(String fichier) throws Exception {
        String nbrMsg;
        Element rootFichier = builder(fichier);
        nbrMsg = rootFichier.getElementsByTagName("nbMsg").item(0).getTextContent();
        return Integer.parseInt(nbrMsg);
    }

    /**
     * @author ALSIBAI, AYADA
     * @param fichier
     * @return IdUser de recepteur
     */
    public static int IdUserRecev(String fichier) throws Exception {
        Element rootFichier = builder(fichier);
        String idUr = rootFichier.getElementsByTagName("receiver").item(0).getAttributes().item(0).getTextContent();
        return Integer.parseInt(idUr);
    }

    /**
     * @author ALSIBAI, AYADA
     * @param fichier
     * @return IdUser de recepteur
     */
    public static int idMsg(String fichier) throws Exception {
        Element rootFichier = builder(fichier);
        String idMsg = rootFichier.getElementsByTagName("message").item(0).getAttributes().item(0).getTextContent();
        return Integer.parseInt(idMsg);
    }

    public static int SavoirIdMsg(String fichier, String attribute) throws Exception {
        try {
            Element rootFichier = builder(fichier);
            String idMsgFinal = rootFichier.getElementsByTagName(attribute).item(0).getParentNode().getAttributes()
                    .item(0).getTextContent();
            return Integer.parseInt(idMsgFinal);
        } catch (NullPointerException e) {
            return -1;
        }
    }

    /**
     * @author ALSIBAI, AYADA
     * @param fichier
     * @return la date de message
     * @throws ParseException
     * @throws NullPointerException
     */
    /*
     * public static String recupDateMsg(String fichier) throws Exception { String
     * msgDate; Element rootFichier = builder(fichier);
     * msgDate=rootFichier.getElementsByTagName("dateMsg").item(0).getTextContent();
     * return msgDate; }
     */
    public static ArrayList<String> recupDateMsg(String fichier) throws Exception {

        ArrayList<String> myListeMsg = new ArrayList<String>();
        String attribObject;
        String idMsg;
        Element rootFichier = builder(fichier);
        NodeList myOfNodes1 = rootFichier.getElementsByTagName("listMsg");

        for (int i = 0; i < myOfNodes1.getLength(); i++) {
            Element newElt1 = (Element) myOfNodes1.item(i);
            int nbObj = newElt1.getElementsByTagName("message").getLength();
            for (int j = 0; j < nbObj; j++) {
                attribObject = rootFichier.getElementsByTagName("dateMsg").item(j).getTextContent();
                idMsg = rootFichier.getElementsByTagName("dateMsg").item(j).getParentNode().getAttributes().item(0)
                        .getTextContent();
                myListeMsg.add(idMsg);
                myListeMsg.add(attribObject);
            }
        }
        return myListeMsg;
    }

    public static ArrayList<String> dateMsgList(String fichier) throws Exception {
        try {
            return recupDateMsg(fichier);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static ArrayList<String> recupDateValid(String fichier) throws Exception {

        ArrayList<String> myListeMsg = new ArrayList<String>();
        String attribObject;
        String idMsg;
        Element rootFichier = builder(fichier);
        NodeList myOfNodes1 = rootFichier.getElementsByTagName("listMsg");

        for (int i = 0; i < myOfNodes1.getLength(); i++) {
            Element newElt1 = (Element) myOfNodes1.item(i);
            int nbObj = newElt1.getElementsByTagName("message").getLength();
            for (int j = 0; j < nbObj; j++) {
                attribObject = rootFichier.getElementsByTagName("validityDuration").item(j).getTextContent();
                idMsg = rootFichier.getElementsByTagName("validityDuration").item(j).getParentNode().getAttributes()
                        .item(0).getTextContent();
                myListeMsg.add(idMsg);
                myListeMsg.add(attribObject);
            }
        }
        return myListeMsg;
    }

    public static ArrayList<String> ListDateValid(String fichier) throws Exception {
        try {
            return recupDateValid(fichier);
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * @author ALSIBAI, AYADA
     * @param fichier
     * @return Autorisation Request
     * @throws ParseException
     * @throws NullPointerException
     */
    public static ArrayList<String> AuthReq(String fichier) throws Exception {

        ArrayList<String> myListeMsg = new ArrayList<String>();
        String attribObject;
        String idMsg;
        Element rootFichier = builder(fichier);
        NodeList myOfNodes1 = rootFichier.getElementsByTagName("listMsg");

        for (int i = 0; i < myOfNodes1.getLength(); i++) {
            Element newElt1 = (Element) myOfNodes1.item(i);
            int nbObj = newElt1.getElementsByTagName("message").getLength();
            for (int j = 0; j < nbObj; j++) {
                attribObject = rootFichier.getElementsByTagName("authRequest").item(j).getTextContent();
                idMsg = rootFichier.getElementsByTagName("authRequest").item(j).getParentNode().getAttributes().item(0)
                        .getTextContent();
                myListeMsg.add(idMsg);
                myListeMsg.add(attribObject);
            }
        }
        return myListeMsg;
    }

    public static ArrayList<String> ListAuthReq(String fichier) throws Exception {
        try {
            return AuthReq(fichier);
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * @author ALSIBAI, AYADA
     * @param fichier
     * @return idPropositionMsgAcc
     */
    public static ArrayList<String> recupIdPropMsgAcc(String fichier) throws Exception {

        ArrayList<String> myListeMsg = new ArrayList<String>();
        String attribObject;
        String idMsg;
        Element rootFichier = builder(fichier);
        NodeList myOfNodes1 = rootFichier.getElementsByTagName("listMsg");

        for (int i = 0; i < myOfNodes1.getLength(); i++) {
            Element newElt1 = (Element) myOfNodes1.item(i);
            int nbObj = newElt1.getElementsByTagName("message").getLength();
            for (int j = 0; j < nbObj; j++) {
                attribObject = rootFichier.getElementsByTagName("accept").item(j).getTextContent();
                idMsg = rootFichier.getElementsByTagName("accept").item(j).getParentNode().getAttributes().item(0)
                        .getTextContent();
                myListeMsg.add(idMsg);
                myListeMsg.add(attribObject);
            }
        }
        return myListeMsg;
    }

    public static ArrayList<String> ListAcc(String fichier) throws Exception {
        try {
            return recupIdPropMsgAcc(fichier);
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * @author ALSIBAI, AYADA
     * @param fichier
     * @return idPropositionMsgDeny
     */
    public static ArrayList<String> recupIdPropMsgDeny(String fichier) throws Exception {

        ArrayList<String> myListeMsg = new ArrayList<String>();
        String attribObject;
        String idMsg;
        Element rootFichier = builder(fichier);
        NodeList myOfNodes1 = rootFichier.getElementsByTagName("listMsg");

        for (int i = 0; i < myOfNodes1.getLength(); i++) {
            Element newElt1 = (Element) myOfNodes1.item(i);
            int nbObj = newElt1.getElementsByTagName("message").getLength();
            for (int j = 0; j < nbObj; j++) {
                attribObject = rootFichier.getElementsByTagName("deny").item(j).getTextContent();
                idMsg = rootFichier.getElementsByTagName("deny").item(j).getParentNode().getAttributes().item(0)
                        .getTextContent();
                myListeMsg.add(idMsg);
                myListeMsg.add(attribObject);
            }
        }
        return myListeMsg;
    }

    public static ArrayList<String> ListDeny(String fichier) throws Exception {
        try {
            return recupIdPropMsgDeny(fichier);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static ArrayList<String> reasonDeny(String fichier) throws Exception {

        ArrayList<String> myListeMsg = new ArrayList<String>();
        String attribObject;
        String idMsg;
        Element rootFichier = builder(fichier);
        NodeList myOfNodes1 = rootFichier.getElementsByTagName("listMsg");

        for (int i = 0; i < myOfNodes1.getLength(); i++) {
            Element newElt1 = (Element) myOfNodes1.item(i);
            int nbObj = newElt1.getElementsByTagName("message").getLength();
            for (int j = 0; j < nbObj; j++) {
                attribObject = rootFichier.getElementsByTagName("reason").item(j).getTextContent();
                idMsg = rootFichier.getElementsByTagName("reason").item(j).getParentNode().getAttributes().item(0)
                        .getTextContent();
                myListeMsg.add(idMsg);
                myListeMsg.add(attribObject);
            }
        }
        return myListeMsg;
    }

    public static ArrayList<String> ListReasonDeny(String fichier) throws Exception {
        try {
            return reasonDeny(fichier);
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * 
     * @param fichier
     * @return une chaine de carac. (recupère le nom et l'attribut en chaine de du
     *         receveur du fichier)
     * @throws SAXException
     * @throws Exception
     */
    public static String recupReceiver(String fichier) throws Exception {
        String nameReceiver;
        Element rootFichier = builder(fichier);
        nameReceiver = rootFichier.getElementsByTagName("receiver").item(0).getTextContent();
        return nameReceiver;
    }

    /**
     * 
     * @param fichier
     * @return une chaine de caractere (la reference de l'auteur)
     * @throws SAXException
     * @throws Exception
     */
    public static ArrayList<String> recupAuthRef(String fichier) throws Exception {

        ArrayList<String> myListeMsg = new ArrayList<String>();
        String attribObject;
        String idMsg;
        Element rootFichier = builder(fichier);
        NodeList myOfNodes1 = rootFichier.getElementsByTagName("listMsg");

        for (int i = 0; i < myOfNodes1.getLength(); i++) {
            Element newElt1 = (Element) myOfNodes1.item(i);
            int nbObj = newElt1.getElementsByTagName("message").getLength();
            for (int j = 0; j < nbObj; j++) {
                attribObject = rootFichier.getElementsByTagName("authRef").item(j).getTextContent();
                idMsg = rootFichier.getElementsByTagName("authRef").item(j).getParentNode().getAttributes().item(0)
                        .getTextContent();
                myListeMsg.add(idMsg);
                myListeMsg.add(attribObject);
            }
        }
        return myListeMsg;
    }

    public static ArrayList<String> ListAuthref(String fichier) throws Exception {
        try {
            return recupAuthRef(fichier);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static String recupAuthRefHead(String fichier) throws Exception {
        try {
            String authReq;
            Element rootFichier = builder(fichier);
            authReq = rootFichier.getElementsByTagName("authRef").item(0).getTextContent();
            String choix = rootFichier.getElementsByTagName("authRef").item(0).getParentNode().getNodeName();
            if (choix == "header")
                return authReq;
            else
                return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * 
     * @param fichier
     * @return la date d'envoi du fichier
     * @throws SAXException
     * @throws Exception
     */
    public static ArrayList<String> recupDate(String fichier) throws Exception {

        ArrayList<String> myListeMsg = new ArrayList<String>();
        String attribObject;
        String idMsg;
        Element rootFichier = builder(fichier);
        NodeList myOfNodes1 = rootFichier.getElementsByTagName("listMsg");

        for (int i = 0; i < myOfNodes1.getLength(); i++) {
            Element newElt1 = (Element) myOfNodes1.item(i);
            int nbObj = newElt1.getElementsByTagName("message").getLength();
            for (int j = 0; j < nbObj; j++) {
                attribObject = rootFichier.getElementsByTagName("authDate").item(j).getTextContent();
                idMsg = rootFichier.getElementsByTagName("authDate").item(j).getParentNode().getAttributes().item(0)
                        .getTextContent();
                myListeMsg.add(idMsg);
                myListeMsg.add(attribObject);
            }
        }
        return myListeMsg;
    }

    public static ArrayList<String> ListAuthDate(String fichier) throws Exception {
        try {
            return recupDate(fichier);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static String recupDateHead(String fichier) throws Exception {
        try {
            String authDate;
            Element rootFichier = builder(fichier);
            authDate = rootFichier.getElementsByTagName("authDate").item(0).getTextContent();
            String choix = rootFichier.getElementsByTagName("authDate").item(0).getParentNode().getNodeName();
            if (choix == "header")
                return authDate;
            else
                return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static ArrayList<String> recupCatReq(String fichier) throws Exception {

        ArrayList<String> myListeMsg = new ArrayList<String>();
        String attribObject;
        String idMsg;
        Element rootFichier = builder(fichier);
        NodeList myOfNodes1 = rootFichier.getElementsByTagName("listMsg");

        for (int i = 0; i < myOfNodes1.getLength(); i++) {
            Element newElt1 = (Element) myOfNodes1.item(i);
            int nbObj = newElt1.getElementsByTagName("message").getLength();
            for (int j = 0; j < nbObj; j++) {
                attribObject = rootFichier.getElementsByTagName("catRequest").item(j).getTextContent();
                idMsg = rootFichier.getElementsByTagName("catRequest").item(j).getParentNode().getAttributes().item(0)
                        .getTextContent();
                myListeMsg.add(idMsg);
                myListeMsg.add(attribObject);
            }
        }
        return myListeMsg;
    }

    public static ArrayList<String> ListCatReq(String fichier) throws Exception {
        try {
            return recupCatReq(fichier);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static ArrayList<String> recupCatDateETreqMsg(String fichier) throws Exception {

        ArrayList<String> myListeMsg = new ArrayList<String>();
        String attribObject;
        String attribObject2;
        String idMsg;
        Element rootFichier = builder(fichier);
        NodeList myOfNodes1 = rootFichier.getElementsByTagName("listMsg");

        for (int i = 0; i < myOfNodes1.getLength(); i++) {
            Element newElt1 = (Element) myOfNodes1.item(i);
            int nbObj = newElt1.getElementsByTagName("cat").getLength();
            for (int j = 0; j < nbObj; j++) {
                attribObject = rootFichier.getElementsByTagName("idCatRequestMsg").item(j).getTextContent();
                attribObject2 = rootFichier.getElementsByTagName("catDate").item(j).getTextContent();
                idMsg = rootFichier.getElementsByTagName("catDate").item(j).getParentNode().getParentNode()
                        .getAttributes().item(0).getTextContent();
                myListeMsg.add(idMsg);
                myListeMsg.add(attribObject);
                myListeMsg.add(attribObject2);
            }
        }
        return myListeMsg;
    }

    public static ArrayList<String> ListCatReqMsgETdate(String fichier) throws Exception {
        try {
            return recupCatDateETreqMsg(fichier);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static ArrayList<String> recupNoCatReasETreqMsg(String fichier) throws Exception {

        ArrayList<String> myListeMsg = new ArrayList<String>();
        String attribObject;
        String attribObject2;
        String idMsg;
        Element rootFichier = builder(fichier);
        NodeList myOfNodes1 = rootFichier.getElementsByTagName("listMsg");

        for (int i = 0; i < myOfNodes1.getLength(); i++) {
            Element newElt1 = (Element) myOfNodes1.item(i);
            int nbObj = newElt1.getElementsByTagName("cat").getLength();
            for (int j = 0; j < nbObj; j++) {
                attribObject = rootFichier.getElementsByTagName("idCatRequestMsg").item(j).getTextContent();
                attribObject2 = rootFichier.getElementsByTagName("reason").item(j).getTextContent();
                idMsg = rootFichier.getElementsByTagName("reason").item(j).getParentNode().getParentNode()
                        .getAttributes().item(0).getTextContent();
                myListeMsg.add(idMsg);
                myListeMsg.add(attribObject);
                myListeMsg.add(attribObject2);
            }
        }
        return myListeMsg;
    }

    public static ArrayList<String> ListNoCat(String fichier) throws Exception {
        try {
            return recupNoCatReasETreqMsg(fichier);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static ArrayList<String> recupErr(String fichier) throws Exception {

        ArrayList<String> myListeMsg = new ArrayList<String>();
        String attribObject;
        String attribObject2;
        String attribObject3;
        String idMsg;
        Element rootFichier = builder(fichier);
        NodeList myOfNodes1 = rootFichier.getElementsByTagName("listMsg");

        for (int i = 0; i < myOfNodes1.getLength(); i++) {
            Element newElt1 = (Element) myOfNodes1.item(i);
            int nbObj = newElt1.getElementsByTagName("cat").getLength();
            for (int j = 0; j < nbObj; j++) {
                attribObject = rootFichier.getElementsByTagName("errorMsg").item(j).getAttributes().item(1)
                        .getTextContent();
                attribObject2 = rootFichier.getElementsByTagName("errorMsg").item(j).getAttributes().item(0)
                        .getTextContent();
                attribObject3 = rootFichier.getElementsByTagName("errorMsg").item(j).getTextContent();
                idMsg = rootFichier.getElementsByTagName("errorMsg").item(j).getParentNode().getAttributes().item(0)
                        .getTextContent();
                myListeMsg.add(idMsg);
                myListeMsg.add(attribObject);
                myListeMsg.add(attribObject2);
                myListeMsg.add(attribObject3);
            }
        }
        return myListeMsg;
    }

    public static ArrayList<String> ListErr(String fichier) throws Exception {
        try {
            return recupErr(fichier);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static int idMsgErr(String fichier) throws Exception {
        try {
            Element rootFichier = builder(fichier);
            String idMsg = rootFichier.getElementsByTagName("errorMsg").item(0).getAttributes().item(1)
                    .getTextContent();
            return Integer.parseInt(idMsg);
        } catch (NullPointerException e) {
            return -1;
        }
    }

    public static int idErr(String fichier) throws Exception {
        try {
            Element rootFichier = builder(fichier);
            String idMsg = rootFichier.getElementsByTagName("errorMsg").item(0).getAttributes().item(0)
                    .getTextContent();
            return Integer.parseInt(idMsg);
        } catch (NullPointerException e) {
            return -1;
        }
    }

    public static String errMsg(String fichier) throws Exception {
        try {
            Element rootFichier = builder(fichier);
            String Msg = rootFichier.getElementsByTagName("errorMsg").item(0).getTextContent();
            return Msg;
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * 
     * @param fichier
     * @return le nombre de message
     * @throws SAXException
     * @throws Exception
     */
    public static int nombreMsg(String fichier) throws Exception {
        Element rootFichier = builder(fichier);
        return Integer.parseInt(rootFichier.getElementsByTagName("nbMsg").item(0).getTextContent());
    }

    public static ArrayList<Integer> nbMsgReel(String fichier) throws Exception {

        ArrayList<Integer> myListeMsg = new ArrayList<Integer>(); // le tableau contient toutes les informations sur la
                                                                  // liste de msg
        String attribObject;

        Element rootFichier = builder(fichier); // la racine du fichier

        // recuperation des informations sur les objects dans la liste d'object
        NodeList myOfNodes1 = rootFichier.getElementsByTagName("listMsg");

        for (int i = 0; i < myOfNodes1.getLength(); i++) {
            // System.out.println(i);
            Element newElt1 = (Element) myOfNodes1.item(i);
            int nbObj = newElt1.getElementsByTagName("message").getLength();
            for (int j = 0; j < nbObj; j++) {
                // recuperation des valeurs des object
                attribObject = rootFichier.getElementsByTagName("message").item(j).getAttributes().item(0)
                        .getTextContent();

                myListeMsg.add(Integer.parseInt(attribObject));
            }
        }

        return myListeMsg;
    }

    public static ArrayList<String> recupReceiverObjet(String fichier) throws Exception {

        ArrayList<String> myListeMsg = new ArrayList<String>();
        String attribObject;
        String objectName;
        String obejectDetails;
        String obejectImage;
        String idMsg;

        Element rootFichier = builder(fichier); // la racine du fichier
        Element myElt = (Element) rootFichier.getElementsByTagName("listMsg").item(0);
        NodeList myOfNodes1 = myElt.getElementsByTagName("rcvObjectList");
        NodeList myOfNodes2 = myElt.getElementsByTagName("barter");
        if (myOfNodes1.item(0).getParentNode().getNodeName() == "barter") {
            for (int i = 0; i < myOfNodes1.getLength(); i++) {
                Element newElt1 = (Element) myOfNodes1.item(i);
                int nbObj = newElt1.getElementsByTagName("object").getLength();
                for (int j = 0; j < nbObj; j++) {
                    attribObject = newElt1.getElementsByTagName("object").item(j).getAttributes().item(0)
                            .getTextContent();
                    objectName = newElt1.getElementsByTagName("objectName").item(j).getTextContent();
                    obejectDetails = newElt1.getElementsByTagName("objectDetails").item(j).getTextContent();
                    obejectImage = newElt1.getElementsByTagName("objectImage").item(j).getTextContent();
                    idMsg = myOfNodes2.item(i).getParentNode().getAttributes().item(0).getTextContent();
                    myListeMsg.add(attribObject);
                    myListeMsg.add(objectName);
                    myListeMsg.add(obejectDetails);
                    myListeMsg.add(obejectImage);
                    myListeMsg.add(idMsg);
                }
            }
            return myListeMsg;
        } else
            return null;

    }

    public static ArrayList<String> recupSndObjet(String fichier) throws Exception {

        ArrayList<String> myListeMsg = new ArrayList<String>();
        String attribObject;
        String objectName;
        String obejectDetails;
        String obejectImage;
        String idMsg;

        Element rootFichier = builder(fichier);

        Element myElt = (Element) rootFichier.getElementsByTagName("listMsg").item(0);
        NodeList myOfNodes1 = myElt.getElementsByTagName("sndObjectList");
        NodeList myOfNodes2 = myElt.getElementsByTagName("barter");
        if (myOfNodes1.item(0).getParentNode().getNodeName() == "barter") {
            for (int i = 0; i < myOfNodes1.getLength(); i++) {
                // System.out.println(i);
                Element newElt1 = (Element) myOfNodes1.item(i);
                int nbObj = newElt1.getElementsByTagName("object").getLength();
                for (int j = 0; j < nbObj; j++) {
                    // recuperation des valeurs des object
                    attribObject = newElt1.getElementsByTagName("object").item(j).getAttributes().item(0)
                            .getTextContent();
                    objectName = newElt1.getElementsByTagName("objectName").item(j).getTextContent();
                    obejectDetails = newElt1.getElementsByTagName("objectDetails").item(j).getTextContent();
                    obejectImage = newElt1.getElementsByTagName("objectImage").item(j).getTextContent();
                    idMsg = myOfNodes2.item(i).getParentNode().getAttributes().item(0).getTextContent();
                    // ajout des valeurs dans la liste
                    myListeMsg.add(attribObject);
                    myListeMsg.add(objectName);
                    myListeMsg.add(obejectDetails);
                    myListeMsg.add(obejectImage);
                    myListeMsg.add(idMsg);
                }
            }
            return myListeMsg;
        } else
            return null;
    }

    public static ArrayList<String> recupDonationObject(String fichier) throws Exception {

        ArrayList<String> myListeMsg = new ArrayList<String>();
        String attribObject;
        String objectName;
        String obejectDetails;
        String obejectImage;
        String idMsg;

        Element rootFichier = builder(fichier); // la racine du fichier

        Element myElt = (Element) rootFichier.getElementsByTagName("listMsg").item(0);
        NodeList myOfNodes1 = myElt.getElementsByTagName("sndObjectList");
        NodeList myOfNodes2 = myElt.getElementsByTagName("donation");
        if (myOfNodes1.item(0).getParentNode().getNodeName() == "donation") {
            for (int i = 0; i < myOfNodes1.getLength(); i++) {
                Element newElt1 = (Element) myOfNodes1.item(i);
                int nbObj = newElt1.getElementsByTagName("object").getLength();
                for (int j = 0; j < nbObj; j++) {
                    // recuperation des valeurs des object
                    attribObject = rootFichier.getElementsByTagName("object").item(j).getAttributes().item(0)
                            .getTextContent();
                    objectName = newElt1.getElementsByTagName("objectName").item(j).getTextContent();
                    obejectDetails = newElt1.getElementsByTagName("objectDetails").item(j).getTextContent();
                    obejectImage = newElt1.getElementsByTagName("objectImage").item(j).getTextContent();
                    idMsg = myOfNodes2.item(i).getParentNode().getAttributes().item(0).getTextContent();
                    // ajout des valeurs dans la liste
                    myListeMsg.add(attribObject);
                    myListeMsg.add(objectName);
                    myListeMsg.add(obejectDetails);
                    myListeMsg.add(obejectImage);
                    myListeMsg.add(idMsg);
                }
            }

            return myListeMsg;
        } else
            return null;
    }

    public static ArrayList<String> recupCatObject(String fichier) throws Exception {
        ArrayList<String> myListeObject = new ArrayList<String>();

        String attribObject;
        String objectName;
        String obejectDetails;
        String obejectImage;
        String idMsg;

        Element rootFichier = builder(fichier); // la racine du fichier

        Element myElt = (Element) rootFichier.getElementsByTagName("listMsg").item(0);
        NodeList myOfNodes1 = myElt.getElementsByTagName("cat");
        for (int i = 0; i < myOfNodes1.getLength(); i++) {

            Element newElt1 = (Element) myOfNodes1.item(i);
            int nbObj = newElt1.getElementsByTagName("object").getLength();
            for (int j = 0; j < nbObj; j++) {
                // recuperation des valeurs des object
                attribObject = rootFichier.getElementsByTagName("object").item(j).getAttributes().item(0)
                        .getTextContent();
                objectName = newElt1.getElementsByTagName("objectName").item(j).getTextContent();
                obejectDetails = newElt1.getElementsByTagName("objectDetails").item(j).getTextContent();
                obejectImage = newElt1.getElementsByTagName("objectImage").item(j).getTextContent();
                idMsg = myOfNodes1.item(i).getParentNode().getAttributes().item(0).getTextContent();
                // ajout des valeurs dans la liste
                myListeObject.add(attribObject);
                myListeObject.add(objectName);
                myListeObject.add(obejectDetails);
                myListeObject.add(obejectImage);
                myListeObject.add(idMsg);
            }
        }
        return myListeObject;

    }

    public static ArrayList<String> recupReqObject(String fichier) throws Exception {
        ArrayList<String> myListeObject = new ArrayList<String>();

        String attribObject;
        String objectName;
        String obejectDetails;
        String obejectImage;
        String idMsg;

        Element rootFichier = builder(fichier); // la racine du fichier
        Element myElt = (Element) rootFichier.getElementsByTagName("listMsg").item(0);
        NodeList myOfNodes1 = myElt.getElementsByTagName("rcvObjectList");
        NodeList myOfNodes2 = myElt.getElementsByTagName("request");
        // System.err.println("je suis laaaaaaaaaaa " +
        // myOfNodes1.item(0).getParentNode().getNodeName());
        if (myOfNodes1.item(0).getParentNode().getNodeName() == "request") {
            for (int i = 0; i < myOfNodes1.getLength(); i++) {
                Element newElt1 = (Element) myOfNodes1.item(i);
                int nbObj = newElt1.getElementsByTagName("object").getLength();
                for (int j = 0; j < nbObj; j++) {
                    attribObject = rootFichier.getElementsByTagName("object").item(j).getAttributes().item(0)
                            .getTextContent();
                    objectName = newElt1.getElementsByTagName("objectName").item(j).getTextContent();
                    obejectDetails = newElt1.getElementsByTagName("objectDetails").item(j).getTextContent();
                    obejectImage = newElt1.getElementsByTagName("objectImage").item(j).getTextContent();
                    idMsg = myOfNodes2.item(i).getParentNode().getAttributes().item(0).getTextContent();
                    // ajout des valeurs dans la liste
                    myListeObject.add(attribObject);
                    myListeObject.add(objectName);
                    myListeObject.add(obejectDetails);
                    myListeObject.add(obejectImage);
                    myListeObject.add(idMsg);
                }
            }
            return myListeObject;
        } else
            return null;
    }

    public static ArrayList<String> listObjRcvBarter(String fichier) throws Exception {
        try {
            return recupReceiverObjet(fichier);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static ArrayList<String> listObjSndBarter(String fichier) throws Exception {
        try {
            return recupSndObjet(fichier);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static ArrayList<String> listObjRcvReq(String fichier) throws Exception {
        try {
            return recupReqObject(fichier);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static ArrayList<String> listObjSndDonn(String fichier) throws Exception {
        try {
            return recupDonationObject(fichier);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static ArrayList<String> listObjCat(String fichier) throws Exception {
        try {
            return recupCatObject(fichier);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static ArrayList<String> recupPrevMsg(String fichier) throws Exception {

        ArrayList<String> myListeMsg = new ArrayList<String>();
        String attribObject;
        String idMsg;
        Element rootFichier = builder(fichier);
        NodeList myOfNodes1 = rootFichier.getElementsByTagName("listMsg");

        for (int i = 0; i < myOfNodes1.getLength(); i++) {
            Element newElt1 = (Element) myOfNodes1.item(i);
            int nbObj = newElt1.getElementsByTagName("idPrevMsg").getLength();
            for (int j = 0; j < nbObj; j++) {
                if (rootFichier.getElementsByTagName("idPrevMsg").item(j).getParentNode().getNodeName() == "barter") {
                    attribObject = rootFichier.getElementsByTagName("idPrevMsg").item(j).getTextContent();
                    idMsg = rootFichier.getElementsByTagName("idPrevMsg").item(j).getParentNode().getParentNode()
                            .getAttributes().item(0).getTextContent();
                    myListeMsg.add(idMsg);
                    myListeMsg.add(attribObject);
                }
            }
        }
        return myListeMsg;

    }

    public static ArrayList<String> ListPrevMsg(String fichier) throws Exception {
        try {
            return recupPrevMsg(fichier);
        } catch (NullPointerException e) {
            return null;
        }
    }
}