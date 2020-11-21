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
 * @author komah Mohamed 
 * * @version 1.0
 * */
@Data
public class ParserXML {

        public ParserXML(){
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
            try {
                DocumentBuilder builder = racineFichier().newDocumentBuilder();
                final Document document = builder.parse(new File(System.getProperty("user.dir")+"/src/main/resources/dossierXML/" + fichier));
                Element enteteFichier = document.getDocumentElement();
                return enteteFichier;
            }catch(ParserConfigurationException | IOException | SAXException e) {
                e.printStackTrace();
            return null;
            } 
        }

        /**
        * @param fichier
        * @return IdF du fichier XML passer en paramettre
        */
    public static  int recupIdF(String fichier) throws    Exception {
            Element rootFichier = builder(fichier);
            String idFichier = rootFichier.getElementsByTagName("header").item(0).getAttributes().item(0).getTextContent();
            return Integer.parseInt(idFichier);
        }


        /**
        * @author ALSIBAI, AYADA 
        * @param fichier
        * @return IdUser de emmitteur
        */
    public static int IdUserTra(String fichier){
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
        public static  String recupererTransmitter(String fichier) throws    Exception {
            String nameTransmitter;
            Element rootFichier = builder(fichier); 
            nameTransmitter = rootFichier.getElementsByTagName("transmitter").item(0).getTextContent();
            return nameTransmitter;
        }  
        
        /**
        * @author ALSIBAI, AYADA 
        * @param fichier
        * @return IdUser de recepteur
        */
        public static int IdUserRecev(String fichier) throws    Exception{
            Element rootFichier = builder(fichier);
            String idUr = rootFichier.getElementsByTagName("receiver").item(0).getAttributes().item(0).getTextContent();
            return Integer.parseInt(idUr);
        }

        /**
        * @author ALSIBAI, AYADA 
        * @param fichier
        * @return IdUser de recepteur
        */
        public static int IdMsg(String fichier) throws    Exception{
            Element rootFichier = builder(fichier);
            String idMsg = rootFichier.getElementsByTagName("message").item(0).getAttributes().item(0).getTextContent();
            return Integer.parseInt(idMsg);
        }

        /**
         * @author ALSIBAI, AYADA
         * @param fichier
         * @return la date de message 
         * @throws ParseException
         * @throws NullPointerException
         */
        public static String recupDateMsg(String fichier) throws    Exception {
            String msgDate;
            Element rootFichier = builder(fichier);
            msgDate=rootFichier.getElementsByTagName("dateMsg").item(0).getTextContent();
            return msgDate;
        }
        
        /**
         * @author ALSIBAI, AYADA
         * @param fichier
         * @return la date de validite 
         * @throws ParseException
         * @throws NullPointerException
         */
        public static String recupDateValid(String fichier) throws    Exception {
            try{
                String msgDateValide;
                Element rootFichier = builder(fichier);
                msgDateValide=rootFichier.getElementsByTagName("validityDuration").item(0).getTextContent();
                return msgDateValide;
            }catch(NullPointerException e){
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
        public static String AuthReq(String fichier) throws    Exception {
            try{
                String authReq;
                Element rootFichier = builder(fichier);
                authReq=rootFichier.getElementsByTagName("authRequest").item(0).getTextContent();
                return authReq;
            }catch(NullPointerException e){
                return null;
            }
        }
        
        /**
        * @author ALSIBAI, AYADA 
        * @param fichier
        * @return idPropositionMsgAcc
        */
        public static int recupIdPropMsgAcc(String fichier) throws    Exception{
            try{
                Element rootFichier = builder(fichier);
                String propMsg = rootFichier.getElementsByTagName("accept").item(0).getChildNodes().item(1).getTextContent();
                return Integer.parseInt(propMsg);
            }catch(NullPointerException e){
                return -1;
            }
        }

        /**
        * @author ALSIBAI, AYADA 
        * @param fichier
        * @return idPropositionMsgDeny
        */
        public static int recupIdPropMsgDeny(String fichier) throws    Exception{
            try{
                    Element rootFichier = builder(fichier);
                    String propMsgDeny = rootFichier.getElementsByTagName("deny").item(0).getChildNodes().item(1).getTextContent();
                    return Integer.parseInt(propMsgDeny);
                }catch(NullPointerException e){
                    return -1;
                }
        }

        public static String reasonDeny(String fichier) throws    Exception {
            try{
                String reasDeny;
                Element rootFichier = builder(fichier);
                reasDeny = rootFichier.getElementsByTagName("deny").item(0).getChildNodes().item(3).getTextContent();
                return reasDeny;
            }catch(NullPointerException e){
                return null;
            }
        }

        /**
         * 
         * @param fichier
         * @return une chaine de carac. (recupère le nom et l'attribut en chaine de  du receveur du fichier)
         * @throws SAXException
         * @throws Exception
         */
        public static String recupReceiver(String fichier) throws    Exception {
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
        public static String recupAuthRef(String fichier) throws Exception {
            try{
                    String authReq;
                    Element rootFichier = builder(fichier);
                    authReq = rootFichier.getElementsByTagName("authRef").item(0).getTextContent();
                    String choix = rootFichier.getElementsByTagName("authRef").item(0).getParentNode().getNodeName();
                    if(choix == "auth")
                        return authReq;
                    else
                        return null;
                }catch(NullPointerException e){
                    return null;
                }
        }
        public static String recupAuthRefHead(String fichier) throws Exception {
            try{
                    String authReq;
                    Element rootFichier = builder(fichier);
                    authReq = rootFichier.getElementsByTagName("authRef").item(0).getTextContent();
                    String choix = rootFichier.getElementsByTagName("authRef").item(0).getParentNode().getNodeName();
                    if(choix == "header")
                        return authReq;
                    else
                        return null;
                }catch(NullPointerException e){
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
        public static String recupDate(String fichier) throws    Exception {
            try{
                String authDate;
                Element rootFichier = builder(fichier);
                authDate=rootFichier.getElementsByTagName("authDate").item(0).getTextContent();
                String choix = rootFichier.getElementsByTagName("authDate").item(0).getParentNode().getNodeName();
                if(choix == "auth")
                    return authDate;
                else
                    return null;
            }catch(NullPointerException e){
                return null;
        }
        }

        public static String recupDateHead(String fichier) throws    Exception {
            try{
                String authDate;
                Element rootFichier = builder(fichier);
                authDate=rootFichier.getElementsByTagName("authDate").item(0).getTextContent();
                String choix = rootFichier.getElementsByTagName("authDate").item(0).getParentNode().getNodeName();
                if(choix == "header")
                    return authDate;
                else
                    return null;
            }catch(NullPointerException e){
                return null;
        }
        }

        public static String recupCatDate(String fichier) throws Exception {
            try{
                String catDate;
                Element rootFichier = builder(fichier);
                catDate=rootFichier.getElementsByTagName("catDate").item(0).getTextContent();
                return catDate;
            }catch(NullPointerException e){
                return null;
        }
        }

        public static String recupCatReq(String fichier) throws Exception {
            try{
                String catReq;
                Element rootFichier = builder(fichier);
                catReq=rootFichier.getElementsByTagName("catRequest").item(0).getTextContent();
                return catReq;
            }catch(NullPointerException e){
                return null;
            }
        }

        public static int idMsgErr(String fichier) throws    Exception{
            try{
                Element rootFichier = builder(fichier);
                String idMsg = rootFichier.getElementsByTagName("errorMsg").item(0).getAttributes().item(1).getTextContent();
                return Integer.parseInt(idMsg);
            }catch(NullPointerException e){
                return -1;
        }
        }

        public static int idErr(String fichier) throws    Exception{
            try{
                Element rootFichier = builder(fichier);
                String idMsg = rootFichier.getElementsByTagName("errorMsg").item(0).getAttributes().item(0).getTextContent();
                return Integer.parseInt(idMsg);
            }catch(NullPointerException e){
                return -1;
        }
        }

        public static String errMsg(String fichier) throws    Exception{
            try{
                Element rootFichier = builder(fichier);
                String Msg = rootFichier.getElementsByTagName("errorMsg").item(0).getTextContent();
                return Msg;
            }catch(NullPointerException e){
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
        public static int nombreMsg(String fichier) throws    Exception {
            Element rootFichier = builder(fichier);
            return Integer.parseInt(rootFichier.getElementsByTagName("nbMsg").item(0).getTextContent());
        }
        
    public static ArrayList<String> recupReceiverObjet(String fichier) throws Exception{
            
        ArrayList<String> myListeMsg = new ArrayList<String>(); // le tableau contient toutes les informations sur la liste de msg
        String  attribObject;
        String objectName ;
        String obejectDetails;
        String obejectImage ;
        
        
        Element rootFichier = builder(fichier); // la racine du fichier
        
        Element myElt = (Element) rootFichier.getElementsByTagName("barter").item(0); // recuperation noeud liste de msg
        
        // recuperation des informations sur les objects dans la liste d'object
        NodeList myOfNodes1 = myElt.getElementsByTagName("rcvObjectList");
        
        for (int i = 0; i < myOfNodes1.getLength(); i++) {
            Element newElt1 = (Element) myOfNodes1.item(i);
            int nbObj = newElt1.getElementsByTagName("object").getLength();
                for (int j = 0; j < nbObj; j++) {
                    attribObject = rootFichier.getElementsByTagName("object").item(j).getAttributes().item(0).getTextContent();
                    objectName = newElt1.getElementsByTagName("objectName").item(j).getTextContent();
                    obejectDetails = newElt1.getElementsByTagName("objectDetails").item(j).getTextContent();
                    obejectImage = newElt1.getElementsByTagName("objectImage").item(j).getTextContent();
                    
                    myListeMsg.add(attribObject);
                    myListeMsg.add(objectName);
                    myListeMsg.add(obejectDetails);
                    myListeMsg.add(obejectImage);
                }
        } 
        
        return myListeMsg;
    }

 
    public static ArrayList<String> recupSndObjet(String fichier) throws Exception {
        
        ArrayList<String> myListeMsg = new ArrayList<String>(); // le tableau contient toutes les informations sur la liste de msg
        String  attribObject;
        String objectName ;
        String obejectDetails;
        String obejectImage ;
        
        Element rootFichier = builder(fichier); // la racine du fichier
        
        Element myElt = (Element) rootFichier.getElementsByTagName("barter").item(0); // recuperation noeud liste de msg
        
        // recuperation des informations sur les objects dans la liste d'object
        NodeList myOfNodes1 = myElt.getElementsByTagName("sndObjectList");
        
        for (int i = 0; i < myOfNodes1.getLength(); i++) {
            //System.out.println(i);
            Element newElt1 = (Element) myOfNodes1.item(i);
            int nbObj = newElt1.getElementsByTagName("object").getLength();
                for (int j = 0; j < nbObj; j++) {
                    // recuperation des valeurs des object
                    attribObject = rootFichier.getElementsByTagName("object").item(j).getAttributes().item(0).getTextContent();
                    objectName = newElt1.getElementsByTagName("objectName").item(j).getTextContent();
                    obejectDetails = newElt1.getElementsByTagName("objectDetails").item(j).getTextContent();
                    obejectImage = newElt1.getElementsByTagName("objectImage").item(j).getTextContent();
                    // ajout des valeurs dans la liste 
                    myListeMsg.add(attribObject);
                    myListeMsg.add(objectName);
                    myListeMsg.add(obejectDetails);
                    myListeMsg.add(obejectImage);
                }
        } 
        
        return myListeMsg;
    }

        
    public static ArrayList<String> recupDonationObject(String fichier) throws Exception {

        ArrayList<String> myListeMsg = new ArrayList<String>(); // le tableau contient toutes les informations sur la
                                                                // liste de msg
        String attribObject;
        String objectName;
        String obejectDetails;
        String obejectImage;

        Element rootFichier = builder(fichier); // la racine du fichier

        Element myElt = (Element) rootFichier.getElementsByTagName("donation").item(0); // recuperation noeud liste de
                                                                                        // msg

        // recuperation des informations sur les objects dans la liste d'object
        NodeList myOfNodes1 = myElt.getElementsByTagName("sndObjectList");

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
                // ajout des valeurs dans la liste
                myListeMsg.add(attribObject);
                myListeMsg.add(objectName);
                myListeMsg.add(obejectDetails);
                myListeMsg.add(obejectImage);
            }
        }

        return myListeMsg;
    }

    public static ArrayList<String> recupCatObject(String fichier) throws Exception {
        ArrayList<String> myListeObject = new ArrayList<String>();

        String attribObject;
        String objectName;
        String obejectDetails;
        String obejectImage;

        Element rootFichier = builder(fichier); // la racine du fichier

        Element myElt = (Element) rootFichier.getElementsByTagName("listMsg").item(0); // recuperation noeud liste de

        // recuperation des informations sur les objects dans la liste d'object
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
                // ajout des valeurs dans la liste
                myListeObject.add(attribObject);
                myListeObject.add(objectName);
                myListeObject.add(obejectDetails);
                myListeObject.add(obejectImage);
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

        Element rootFichier = builder(fichier); // la racine du fichier

        Element myElt = (Element) rootFichier.getElementsByTagName("request").item(0); // recuperation noeud liste de
                                                                                       // msg

        // recuperation des informations sur les objects dans la liste d'object
        NodeList myOfNodes1 = myElt.getElementsByTagName("rcvObjectList");

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
                // ajout des valeurs dans la liste
                myListeObject.add(attribObject);
                myListeObject.add(objectName);
                myListeObject.add(obejectDetails);
                myListeObject.add(obejectImage);
            }
        }
        return myListeObject;
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
            }catch(NullPointerException e){
                return null;
            }   
    }

        public static String recupPrevMsgBart(String fichier) throws    Exception {
            try{
                String prevMsg;
                Element rootFichier = builder(fichier);
                prevMsg = rootFichier.getElementsByTagName("idPrevMsg").item(0).getTextContent();
                String choix = rootFichier.getElementsByTagName("idPrevMsg").item(0).getParentNode().getNodeName();
                if(choix == "barter")
                    return prevMsg;
                else
                    return null;
                }catch(NullPointerException e){
                    return null;
            }
        }
        public static String recupPrevMsgReq(String fichier) throws Exception {
            try{
                String prevMsg;
                Element rootFichier = builder(fichier);
                prevMsg = rootFichier.getElementsByTagName("idPrevMsg").item(0).getTextContent();
                String choix = rootFichier.getElementsByTagName("idPrevMsg").item(0).getParentNode().getNodeName();
                if(choix == "request")
                    return prevMsg;
                else
                    return null;
                }catch(NullPointerException e){
                    return null;
            }
        }
        public static String recupPrevMsgDonn(String fichier) throws Exception {
            try{
                String prevMsg;
                Element rootFichier = builder(fichier);
                prevMsg = rootFichier.getElementsByTagName("idPrevMsg").item(0).getTextContent();
                String choix = rootFichier.getElementsByTagName("idPrevMsg").item(0).getParentNode().getNodeName();
                if(choix == "donation")
                    return prevMsg;
                else
                    return null;
                }catch(NullPointerException e){
                    return null;
            }
        }

        public static int recupCatReqMsg(String fichier) throws Exception {
            try {
                    String Msg;
                    Element rootFichier = builder(fichier);
                    Msg = rootFichier.getElementsByTagName("idCatRequestMsg").item(0).getTextContent();
                    String choix = rootFichier.getElementsByTagName("idCatRequestMsg").item(0).getParentNode().getNodeName();
                    if(choix == "cat")
                        return Integer.parseInt(Msg);
                    else
                        return -1;
                    }catch(NullPointerException e){
                        return -1;
                }   
        }
        public static int recupNoCatReqMsg(String fichier) throws Exception {
            try{
                String Msg;
                Element rootFichier = builder(fichier);
                Msg = rootFichier.getElementsByTagName("idCatRequestMsg").item(0).getTextContent();
                String choix = rootFichier.getElementsByTagName("idCatRequestMsg").item(0).getParentNode().getNodeName();
                if(choix == "noCat")
                    return Integer.parseInt(Msg);
                else
                    return -1;
                }catch(NullPointerException e){
                    return -1;
            }
        }
        public static String recupNoCatReason(String fichier) throws Exception {
            try{
                String Msg;
                Element rootFichier = builder(fichier);
                Msg = rootFichier.getElementsByTagName("reason").item(0).getTextContent();
                String choix = rootFichier.getElementsByTagName("reason").item(0).getParentNode().getNodeName();
                if(choix == "noCat")
                    return Msg;
                else
                    return null;
                }catch(NullPointerException e){
                    return null;
            }
        }

}