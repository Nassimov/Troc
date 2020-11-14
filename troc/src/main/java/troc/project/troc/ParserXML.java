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

/**
 * @author komah Mohamed 
 * @version 1.0
 */
public class ParserXML {
    
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
            final Document document = builder.parse(new File("./dossierXML/" + fichier));
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
    * @throws SAXException
    * @throws Exception
    */
   public static int recupIdF(String fichier) throws SAXException, Exception {
        Element rootFichier = builder(fichier);
        String idFichier = rootFichier.getElementsByTagName("header").item(0).getAttributes().item(0).getTextContent();
        return Integer.parseInt(idFichier);
    }
    /**
     * 
     * @param fichier
     * @return le transmitter du fichier
     * @throws SAXException
     * @throws Exception
     */
    public static String recupererTransmitter(String fichier) throws SAXException, Exception {
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
    public static boolean standalone(String fichier) {
		
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
    public static String versionXml(String fichier) {
			
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
     * 
     * @param fichier
     * @return une chaine de carac. (recupère le nom et l'attribut en chaine de  du receveur du fichier)
     * @throws SAXException
     * @throws Exception
     */
    public static String recupReceiver(String fichier) throws SAXException, Exception {
		String nameReceiver;
		Element rootFichier = builder(fichier);
		nameReceiver = rootFichier.getElementsByTagName("receiver").item(0).getTextContent();
		String attributReceiver = rootFichier.getElementsByTagName("transmitter").item(0).getAttributes().item(0).getTextContent();
		return (nameReceiver + " son est: "+ attributReceiver);
	}
    
    /**
     * 
     * @param fichier
     * @return une chaine de caractere (la reference de l'auteur)
     * @throws SAXException
     * @throws Exception
     */
    public static String recupAuthRef(String fichier) throws SAXException, Exception {
		
		Element rootFichier = builder(fichier);
		 
		return rootFichier.getElementsByTagName("authRef").item(0).getTextContent();
	}
    /**
     * 
     * @param fichier
     * @return la date d'envoi du fichier 
     * @throws SAXException
     * @throws Exception
     */
    public static String recupDate(String fichier) throws SAXException, Exception {
		Element rootFichier = builder(fichier);
		return rootFichier.getElementsByTagName("authDate").item(0).getTextContent();
	}

    /**
     * 
     * @param fichier
     * @return le nombre de message
     * @throws SAXException
     * @throws Exception
     */
    public static int nombreMsg(String fichier) throws SAXException, Exception {
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
	public static int attributObject(String fichier) throws SAXException, Exception{
		Element rootFichier = builder(fichier);
		String attribOject = rootFichier.getElementsByTagName("object").item(0).getAttributes().item(0).getTextContent();
		return Integer.parseInt(attribOject);
	}
    
    /**
	 * 
	 * @param fichier
	 * @return un tableau de arrayList, de chaine de caractere (quand c'est un fichier de request)
	 * @throws SAXException
	 * @throws Exception
	 */
	
	public static ArrayList recupRequest(String fichier) throws SAXException, Exception {
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
		final Document dom= db.parse(new File("./dossierXML/" + fichier));
	    Element docEle = dom.getDocumentElement();
	    NodeList nl = docEle.getChildNodes();
		if (nl != null) {
	           int length = nl.getLength();
	           for (int i = 0; i < length; i++) {
	               if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
	                   Element el = (Element) nl.item(i);
	                   //System.out.println(el.getTagName());
	                   if (el.getNodeName().contains("body")) {
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
    
    /**
	 * 
	 * @return la liste de messages quand c'est un troc
	 * @throws SAXException
	 * @throws Exception
	 */
	
	public static ArrayList<ArrayList> listMsg(String fichier) throws SAXException, Exception {
		return recupRequest(fichier);
	}

}