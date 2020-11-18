package troc.project.troc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import troc.project.troc.ParserXML;
import troc.project.troc.model.TrocBdd;
import troc.project.troc.repositories.TrocBddRepository;

import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.io.IOException;

import javax.inject.Inject;

@Controller
public class ReceiveController {
    
    @Inject
    TrocBddRepository bdd;

    TrocBdd t = new TrocBdd();

    public void lireHeader(String fileName) throws ParseException,NullPointerException{

        //Pour enregistrer le Header
        t.setIdF(ParserXML.recupIdF(fileName));
        t.setNbMsg(ParserXML.nombreMsg(fileName));
        t.setIdUserTrans(ParserXML.IdUserTra(fileName));
        t.setTransmitter(ParserXML.recupererTransmitter(fileName));
        t.setIdUserRec(ParserXML.IdUserRecev(fileName));
        t.setReceiver(ParserXML.recupReceiver(fileName));
        //optionel
        t.setAuthRef(ParserXML.recupAuthRef(fileName));
        t.setAuthDate(ParserXML.recupDate(fileName)); 

        //Enregistrer le body
        t.setIdMsg(ParserXML.IdMsg(fileName));
        t.setDateMsg(ParserXML.recupDateMsg(fileName));
        t.setValideDuree(ParserXML.recupDateValid(fileName));
        //optionnel
        //t.setAuthReq(ParserXML.AuthReq(fileName));
        //t.setAuthRefMsg(ParserXML.recupAuthRef(fileName));
        //t.setAuthDateMsg(ParserXML.recupDate(fileName));
        //t.setIdPropMsgAcc(ParserXML.recupIdPropMsgAcc(fileName));
        //t.setIdPropMsgDeny(ParserXML.recupIdPropMsgDeny(fileName));
        if(ParserXML.recupIdPropMsgAcc(fileName) == -1)
            t.setIdPropMsgAcc(-1);
        else
            t.setIdPropMsgAcc(ParserXML.recupIdPropMsgAcc(fileName));

        if(ParserXML.recupIdPropMsgDeny(fileName) == -1)
            t.setIdPropMsgDeny(-1);
        else
            t.setIdPropMsgDeny(ParserXML.recupIdPropMsgDeny(fileName));

        if(ParserXML.reasonDeny(fileName) == null)
            t.setRaisonDeny(null);
        else
            t.setRaisonDeny(ParserXML.reasonDeny(fileName));

        bdd.save(t);
            
    }

    @RequestMapping(value = "/ReceiveXML")
    public String receiverPage() throws Exception {
        try{
      
            lireHeader("fichierXML.xml");
			
		}catch(NullPointerException | ParseException e) {
            System.err.println("Error in xml file \n"+e);
            return "ReceiveXML.html";
        
        }
        try{
            System.err.println(ParserXML.recupSndObjBarter("fichierXML.xml"));
        }catch(ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        return null;
        } 

        return "ReceiveXML";
    }
    
}
