    package troc.project.troc;

    import java.io.File;
    import java.io.IOException;
    import java.util.ArrayList;

    import javax.xml.parsers.DocumentBuilder;
    import javax.xml.parsers.DocumentBuilderFactory;
    import javax.xml.parsers.ParserConfigurationException;

    import org.w3c.dom.Document;
    import org.w3c.dom.Element;
    import org.w3c.dom.Node;
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
        
        /**
         * 
         * @return factory doument 
         */
        public static DocumentBuilderFactory racineFichier() {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            return factory;
        }

        /**
         * 
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
        * 
        * @param fichier
        * @return IdF du fichier XML passer en paramettre
        */
    public  int recupIdF(String fichier) throws ParseException,NullPointerException {
            Element rootFichier = builder(fichier);
            String idFichier = rootFichier.getElementsByTagName("header").item(0).getAttributes().item(0).getTextContent();
            return Integer.parseInt(idFichier);
        }


        /**
        * @author ALSIBAI, AYADA 
        * @param fichier
        * @return IdUser de emmitteur
        */
    public int IdUserTra(String fichier){
        Element rootFichier = builder(fichier);
        String idUe = rootFichier.getElementsByTagName("transmitter").item(0).getAttributes().item(0).getTextContent();
        return Integer.parseInt(idUe);
    }

        /**
         * 
         * @param fichier
         * @return le transmitter du fichier
         * @throws SAXException
         * @throws Exception
         */
        public  String recupererTransmitter(String fichier) throws ParseException,NullPointerException {
            String nameTransmitter;
            Element rootFichier = builder(fichier); 
            nameTransmitter = rootFichier.getElementsByTagName("transmitter").item(0).getTextContent();
            return nameTransmitter;
        }
        
        /**
         * 
         * @param fichier
         * @return le standalone du fichier soit true ou false 
         */
        public boolean standalone(String fichier) {
            
            try {
                DocumentBuilder builder = racineFichier().newDocumentBuilder();
                final Document document = builder.parse(new File("./dossierXML/" + fichier));
                boolean stDalone = document.getXmlStandalone();
                return stDalone ;
            }catch(ParserConfigurationException | IOException| SAXException e) {
                e.printStackTrace();
                return false;
            }
        }
        
        
        /**
         * 
         * @param fichier
         * @return la version du fichier XML passé en paramettre
         */
        public String versionXml(String fichier) {
                
            try {
                DocumentBuilder builder = racineFichier().newDocumentBuilder();
                final Document document = builder.parse(new File("./dossierXML/" + fichier));
                String versionXml = document.getXmlVersion();
                return versionXml ;
            }catch(ParserConfigurationException | IOException| SAXException e) {
                e.printStackTrace();
            }
            return null;
        }
        
        /**
        * @author ALSIBAI, AYADA 
        * @param fichier
        * @return IdUser de recepteur
        */
        public int IdUserRecev(String fichier) throws ParseException,NullPointerException{
            Element rootFichier = builder(fichier);
            String idUr = rootFichier.getElementsByTagName("receiver").item(0).getAttributes().item(0).getTextContent();
            return Integer.parseInt(idUr);
        }

        /**
        * @author ALSIBAI, AYADA 
        * @param fichier
        * @return IdUser de recepteur
        */
        public int IdMsg(String fichier) throws ParseException,NullPointerException{
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
        public String recupDateMsg(String fichier) throws ParseException,NullPointerException {
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
        public String recupDateValid(String fichier) throws ParseException,NullPointerException {
            String msgDateValide;
            Element rootFichier = builder(fichier);
            msgDateValide=rootFichier.getElementsByTagName("validityDuration").item(0).getTextContent();
            return msgDateValide;
        }
        
        /**
         * @author ALSIBAI, AYADA
         * @param fichier
         * @return Autorisation Request
         * @throws ParseException
         * @throws NullPointerException
         */
        public String AuthReq(String fichier) throws ParseException,NullPointerException {
            String authReq;
            Element rootFichier = builder(fichier);
            authReq=rootFichier.getElementsByTagName("authRequest").item(0).getTextContent();
            return authReq;
        }
        
        /**
        * @author ALSIBAI, AYADA 
        * @param fichier
        * @return idPropositionMsgAcc
        */
        public int recupIdPropMsgAcc(String fichier) throws ParseException,NullPointerException{
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
        public int recupIdPropMsgDeny(String fichier) throws ParseException,NullPointerException{
            try{
                    Element rootFichier = builder(fichier);
                    String propMsgDeny = rootFichier.getElementsByTagName("deny").item(0).getChildNodes().item(1).getTextContent();
                    return Integer.parseInt(propMsgDeny);
                }catch(NullPointerException e){
                    return -1;
                }
        }

        public static String reasonDeny(String fichier) throws ParseException,NullPointerException {
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
        public String recupReceiver(String fichier) throws ParseException,NullPointerException {
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
        public String recupAuthRef(String fichier) throws ParseException,NullPointerException {
            Element rootFichier = builder(fichier);
            String authRef=rootFichier.getElementsByTagName("authRef").item(0).getTextContent();
            
            return authRef;
        }
        /**
         * 
         * @param fichier
         * @return la date d'envoi du fichier 
         * @throws SAXException
         * @throws Exception
         */
        public String recupDate(String fichier) throws ParseException,NullPointerException {
            String authDate;
            Element rootFichier = builder(fichier);
            authDate=rootFichier.getElementsByTagName("authDate").item(0).getTextContent();
            return authDate;
        }

        /**
         * @author Komah Mohamed
         * @param fichier
         * @return le nombre de message
         * @throws SAXException
         * @throws Exception
         */
        public int nombreMsg(String fichier) throws ParseException,NullPointerException {
            Element rootFichier = builder(fichier);
            return Integer.parseInt(rootFichier.getElementsByTagName("nbMsg").item(0).getTextContent());
        }
        
        /**
         * 
         * @param fichier
         * @return un entier (attribut de l'object)
         * @throws SAXException
         * @throws Exception
         */
        public int attributObject(String fichier) /* throws SAXException, Exception */{
            Element rootFichier = builder(fichier);
            String attribOject = rootFichier.getElementsByTagName("object").item(0).getAttributes().item(0).getTextContent();
            return Integer.parseInt(attribOject);
        }
        
        /**
         * @author Komah Mohamed
         * @param fichier
         * @return un tableau de arrayList, de chaine de caractere (quand c'est un fichier de request)
         */
        
        public ArrayList recupRcvObjBarter(String fichier)  throws SAXException, Exception {
            // le tableau contenant des informations sur les objects selon leur position nement dans le fichier 
            ArrayList<String> myListeMsg = new ArrayList<String>();
            ArrayList allObject = new ArrayList<>();
            // les varaibles
            String objectName; 
            String objectDetails; 
            String objectImage ;
            String  attribOject;
            
            // gestion du DOM
            DocumentBuilderFactory dbf =  racineFichier();
            DocumentBuilder db = dbf.newDocumentBuilder();
            final Document dom= db.parse(new File(System.getProperty("user.dir")+"/src/main/resources/dossierXML/" + fichier));
            Element docEle = dom.getDocumentElement();
            NodeList nl = docEle.getChildNodes();
            if (nl != null) {
                int length = nl.getLength();
                for (int i = 0; i < length; i++) {
                    if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        Element el = (Element) nl.item(i);
                        //System.out.println(el.getTagName());
                        if (el.getNodeName().contains("body")) {
                            int nbObj = el.getElementsByTagName("rcvObjectList").getLength();
                            for (int j = 0; j < nbObj; j++) {

                                attribOject = dom.getElementsByTagName("object").item(j).getAttributes().item(0).getTextContent();
                                    objectName = el.getElementsByTagName("objectName").item(j).getTextContent();
                                    objectDetails = el.getElementsByTagName("objectDetails").item(j).getTextContent();
                                    objectImage = el.getElementsByTagName("objectImage").item(j).getTextContent();
                                    
                                    // insertion dans la liste
                                myListeMsg.add(attribOject);
                                myListeMsg.add(objectName);
                                myListeMsg.add(objectDetails);
                                myListeMsg.add(objectImage);
                            }
                                // un arrayList qui contient tous les objects 
                            allObject.add(myListeMsg); 
                        }
                        }
                    }
            }
            return allObject;
            
        }
        /**
         * @author komah Mohamed
         * @return la liste des objects envoyés plus generale(tous les objects dans un fichier)
         * @param  fichier 
         */
        public ArrayList recupSndObjBarter(String fichier)  throws SAXException, Exception {
            // le tableau contenant des informations sur les objects selon leur position nement dans le fichier 
            ArrayList<String> myListeMsg = new ArrayList<String>();
            ArrayList allObject = new ArrayList<>();
            // les varaibles
            String objectName; 
            String objectDetails; 
            String objectImage ;
            String  attribOject;
            
            // gestion du DOM
            DocumentBuilderFactory dbf =  racineFichier();
            DocumentBuilder db = dbf.newDocumentBuilder();
            final Document dom= db.parse(new File(System.getProperty("user.dir")+"/src/main/resources/dossierXML/" + fichier));
            Element docEle = dom.getDocumentElement();
            NodeList nl = docEle.getChildNodes();
            if (nl != null) {
                int length = nl.getLength();
                for (int i = 0; i < length; i++) {
                    if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        Element el = (Element) nl.item(i);
                        System.out.println(nl.item(i).getNodeType());
                        if (el.getNodeName().contains("body")) {
                            //System.err.println(el.getElementsByTagName("sndObjectList").item(0).getParentNode().getNodeName());
                            int nbObj = el.getElementsByTagName("object").getLength();
                            for (int j = 0; j < nbObj; j++) {
                                attribOject = dom.getElementsByTagName("object").item(j).getAttributes().item(0).getTextContent();
                                    objectName = el.getElementsByTagName("objectName").item(j).getTextContent();
                                    objectDetails = el.getElementsByTagName("objectDetails").item(j).getTextContent();
                                    objectImage = el.getElementsByTagName("objectImage").item(j).getTextContent();
                                    
                                    // insertion dans la liste
                                myListeMsg.add(attribOject);
                                myListeMsg.add(objectName);
                                myListeMsg.add(objectDetails);
                                myListeMsg.add(objectImage);
                            }
                                // un arrayList qui contient tous les objects 
                            allObject.add(myListeMsg); 
                        }
                        }
                    }
            }
            return allObject;
            
        }

    //---------------------------methodes ajouter -------------------------------------------
    /**
     * @author Komah mohamed
     * @param fichier
     * @return la liste des object reçus
     * @throws SAXException
     * @throws Exception
     */
    public ArrayList recupReceiverObjet(String fichier) throws SAXException, Exception {
            
        ArrayList<String> myListeMsg = new ArrayList<String>(); // le tableau contient toutes les informations sur la liste de msg
        String  attribObject;
        String objectName ;
        String obejectDetails;
        String obejectImage ;
        
        Element rootFichier = builder(fichier); // la racine du fichier
        
        Element myElt = (Element) rootFichier.getElementsByTagName("listMsg").item(0); // recuperation noeud liste de msg
        
        // recuperation des informations sur les objects dans la liste d'object
        NodeList myOfNodes1 = myElt.getElementsByTagName("rcvObjectList");
        
        for (int i = 0; i < myOfNodes1.getLength(); i++) {
            //System.out.println(i);
            Element newElt1 = (Element) myOfNodes1.item(i);
            int nbObj = newElt1.getElementsByTagName("object").getLength();
            System.out.println("la liste des objects reçu il y en a : " + nbObj);
            if(newElt1.getNodeName().contains("rcvObjectList")) {
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
        } 
        
        return myListeMsg;
    }
    //------------------------------------------methode senObj------------------------------------

    /**
     * @author komah Mohamed
     * @param fichier
     * @return la liste des objects envoyer 
     * @throws SAXException
     * @throws Exception
     */
    public ArrayList recupSndObjet(String fichier) throws SAXException, Exception {
        
        ArrayList<String> myListeMsg = new ArrayList<String>(); // le tableau contient toutes les informations sur la liste de msg
        String  attribObject;
        String objectName ;
        String obejectDetails;
        String obejectImage ;
        
        Element rootFichier = builder(fichier); // la racine du fichier
        
        Element myElt = (Element) rootFichier.getElementsByTagName("listMsg").item(0); // recuperation noeud liste de msg
        
        // recuperation des informations sur les objects dans la liste d'object
        NodeList myOfNodes1 = myElt.getElementsByTagName("sndObjectList");
        
        for (int i = 0; i < myOfNodes1.getLength(); i++) {
            //System.out.println(i);
            Element newElt1 = (Element) myOfNodes1.item(i);
            int nbObj = newElt1.getElementsByTagName("object").getLength();
            System.out.println("la liste des objects envoyer il y en a : " + nbObj);
            if(newElt1.getNodeName().contains("sndObjectList")) {
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
        } 
        
        return myListeMsg;
    }

    //--------------------------------donation--------------------------------------------------------------------

        /**
         * @author komah Mohamed
         * @param fichier
         * @return la liste des objects donnés
         * @throws SAXException
         * @throws Exception
         */

        public ArrayList recupDonationObject(String fichier) throws SAXException, Exception {

            ArrayList<String> myListeMsg = new ArrayList<String>(); // le tableau contient toutes les informations sur la liste de msg
            String  attribObject;
            String objectName ;
            String obejectDetails;
            String obejectImage ;

            Element rootFichier = builder(fichier); // la racine du fichier

            Element myElt = (Element) rootFichier.getElementsByTagName("listMsg").item(0); // recuperation noeud liste de msg

            // recuperation des informations sur les objects dans la liste d'object
            NodeList myOfNodes1 = myElt.getElementsByTagName("donation");

            for (int i = 0; i < myOfNodes1.getLength(); i++) {
                
                Element newElt1 = (Element) myOfNodes1.item(i);
                int nbObj = newElt1.getElementsByTagName("object").getLength();
                System.out.println("la liste des objects donnés il y en a : " + nbObj);
                System.out.println(newElt1.getNodeName());
                //if(newElt1.getNodeName().contains("sndObjectList")) {
                    
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
                //}
            } 

            return myListeMsg;
        }


        /**
         * 
         * @param fichier
         * @param nomNoeud
         * @return la liste des object(adaptable à toute autre categorie dans un fichier xml)
         * @throws SAXException
         * @throws Exception
         */
        public ArrayList recupCatObject(String fichier , String nomNoeud) throws SAXException, Exception {
            ArrayList<String>  myListeObject = new ArrayList<String>();
            
            String  attribObject;
            String objectName ;
            String obejectDetails;
            String obejectImage ;
            
            Element rootFichier = builder(fichier); // la racine du fichier
            
            Element myElt = (Element) rootFichier.getElementsByTagName("listMsg").item(0); // recuperation noeud liste de msg
            
            NodeList myOfNodes = myElt.getElementsByTagName("message");
            
            // recuperation des informations sur les objects dans la liste d'object
            NodeList myOfNodes1 = myElt.getElementsByTagName(nomNoeud);
            
            for (int i = 0; i < myOfNodes1.getLength(); i++) {
                
                Element newElt1 = (Element) myOfNodes1.item(i);
                int nbObj = newElt1.getElementsByTagName("object").getLength();
                System.out.println("la liste des objects cat il y en a : " + nbObj);
                    for (int j = 0; j < nbObj; j++) {
                        // recuperation des valeurs des object
                        attribObject = rootFichier.getElementsByTagName("object").item(j).getAttributes().item(0).getTextContent();
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


}