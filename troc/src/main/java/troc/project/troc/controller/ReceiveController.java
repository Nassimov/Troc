package troc.project.troc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import troc.project.troc.ParserXML;
import troc.project.troc.model.TrocBdd;
import troc.project.troc.repositories.TrocBddRepository;

import java.io.IOException;
import java.text.ParseException;

import javax.inject.Inject;

@Controller
public class ReceiveController {
    
    @Inject
    TrocBddRepository bdd;

    TrocBdd t = new TrocBdd();

    public void lireHeader(String fileName) throws Exception {

        //Pour enregistrer le Header
        t.setIdF(ParserXML.recupIdF(fileName));
        t.setNbMsg(ParserXML.nombreMsg(fileName));
        t.setIdUserTrans(ParserXML.IdUserTra(fileName));
        t.setTransmitter(ParserXML.recupererTransmitter(fileName));
        t.setIdUserRec(ParserXML.IdUserRecev(fileName));
        t.setReceiver(ParserXML.recupReceiver(fileName));
        //optionel
        t.setAuthRef(ParserXML.recupAuthRefHead(fileName));
        t.setAuthDate(ParserXML.recupDateHead(fileName)); 

        //Enregistrer le body
        t.setIdMsg(ParserXML.IdMsg(fileName));
        t.setDateMsg(ParserXML.recupDateMsg(fileName));
        t.setValideDuree(ParserXML.recupDateValid(fileName));
        //optionnel
        t.setAuthReq(ParserXML.AuthReq(fileName));
        t.setAuthRefMsg(ParserXML.recupAuthRef(fileName));
        t.setAuthDateMsg(ParserXML.recupDate(fileName));
        t.setIdPropMsgAcc(ParserXML.recupIdPropMsgAcc(fileName));
        t.setIdPropMsgDeny(ParserXML.recupIdPropMsgDeny(fileName));

        t.setCatReq(ParserXML.recupCatReq(fileName));

        t.setCatDate(ParserXML.recupCatDate(fileName));

        t.setIdMsgError(ParserXML.idMsgErr(fileName));
        t.setIdError(ParserXML.idErr(fileName));
        t.setMsgErr(ParserXML.errMsg(fileName));

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

        if(ParserXML.recupPrevMsgBart(fileName) == null)
            t.setIdPrevMsgBarter(null);
        else
            t.setIdPrevMsgBarter(ParserXML.recupPrevMsgBart(fileName));

        if(ParserXML.recupPrevMsgReq(fileName) == null)
            t.setIdPrevMsgReq(null);
        else
            t.setIdPrevMsgReq(ParserXML.recupPrevMsgReq(fileName));

        if(ParserXML.recupPrevMsgDonn(fileName) == null)
            t.setIdPrevMsgDonn(null);
        else
            t.setIdPrevMsgDonn(ParserXML.recupPrevMsgDonn(fileName));

        if(ParserXML.recupCatReqMsg(fileName) == -1)
            t.setIdCatReqMsg(-1);
        else
            t.setIdCatReqMsg(ParserXML.recupCatReqMsg(fileName));

        if(ParserXML.recupNoCatReqMsg(fileName) == -1)
            t.setIdNoCatReqMsg(-1);
        else
            t.setIdNoCatReqMsg(ParserXML.recupNoCatReqMsg(fileName));
        
        if(ParserXML.recupNoCatReason(fileName) == null)
            t.setRaisonNoCat(null);
        else
            t.setRaisonNoCat(ParserXML.recupNoCatReason(fileName));
            
        //System.err.println(ParserXML.recupCatReqMsg(fileName));
        
        bdd.save(t);

        /**************************Troc********************/
        
        if(ParserXML.listObjRcvBarter(fileName) == null)
        {
            //TrocBdd t2 = new TrocBdd();
            t.setIdObjBartRec(null);
            t.setObjBartNameRec(null);
            t.setObjBartDetailsRec(null);
            t.setObjBartImageRec(null);
        }
        else
        {
            for(int i = 0; i < ParserXML.listObjRcvBarter(fileName).size() ; i+=4)
            {
                TrocBdd t2 = new TrocBdd();
                t2.setIdObjBartRec((String) ParserXML.listObjRcvBarter(fileName).get(i));
                t2.setObjBartNameRec((String) ParserXML.listObjRcvBarter(fileName).get(i+1));
                t2.setObjBartDetailsRec((String) ParserXML.listObjRcvBarter(fileName).get(i+2));
                t2.setObjBartImageRec((String) ParserXML.listObjRcvBarter(fileName).get(i+3));
                t2.setIdF(t.getIdF());
                bdd.save(t2);
            }
        }

        if(ParserXML.listObjSndBarter(fileName) == null)
        {
            //TrocBdd t2 = new TrocBdd();
            t.setIdObjBartSend(null);
            t.setObjBartNameSend(null);
            t.setObjBartDetailsSend(null);
            t.setObjBartImageSend(null);
        }
        else
        {
            for(int i = 0; i < ParserXML.listObjSndBarter(fileName).size() ; i+=4)
            {
                TrocBdd t2 = new TrocBdd();
                t2.setIdObjBartSend((String) ParserXML.listObjSndBarter(fileName).get(i));
                t2.setObjBartNameSend((String) ParserXML.listObjSndBarter(fileName).get(i+1));
                t2.setObjBartDetailsSend((String) ParserXML.listObjSndBarter(fileName).get(i+2));
                t2.setObjBartImageSend((String) ParserXML.listObjSndBarter(fileName).get(i+3));
                t2.setIdF(t.getIdF());
                bdd.save(t2);
            }
        } 

        /**************************Request********************/
        
        if(ParserXML.listObjRcvReq(fileName) == null)
        {
            //TrocBdd t2 = new TrocBdd();
            t.setIdObjReq(null);
            t.setObjNameReqRcv(null);
            t.setObjDetailsReqRcv(null);
            t.setObjImageReqRcv(null);
        }
        else
        {
            for(int i = 0; i < ParserXML.listObjRcvReq(fileName).size() ; i+=4)
            {
                TrocBdd t2 = new TrocBdd();
                t2.setIdObjReq((String) ParserXML.listObjRcvReq(fileName).get(i));
                t2.setObjNameReqRcv((String) ParserXML.listObjRcvReq(fileName).get(i+1));
                t2.setObjDetailsReqRcv((String) ParserXML.listObjRcvReq(fileName).get(i+2));
                t2.setObjImageReqRcv((String) ParserXML.listObjRcvReq(fileName).get(i+3));
                t2.setIdF(t.getIdF());
                bdd.save(t2);
            }
        }
        
        /**************************Donnation********************/
        
        if(ParserXML.listObjSndDonn(fileName) == null)
        {
            //TrocBdd t2 = new TrocBdd();
            t.setIdObjDonnSend(null);
            t.setObjDonnNameSend(null);
            t.setObjDonnDetailsSend(null);
            t.setObjDonnImageSend(null);
        }
        else
        {
            for(int i = 0; i < ParserXML.listObjSndDonn(fileName).size() ; i+=4)
            {
                TrocBdd t2 = new TrocBdd();
                t2.setIdObjDonnSend((String) ParserXML.listObjSndDonn(fileName).get(i));
                t2.setObjDonnNameSend((String) ParserXML.listObjSndDonn(fileName).get(i+1));
                t2.setObjDonnDetailsSend((String) ParserXML.listObjSndDonn(fileName).get(i+2));
                t2.setObjDonnImageSend((String) ParserXML.listObjSndDonn(fileName).get(i+3));
                t2.setIdF(t.getIdF());
                bdd.save(t2);
            }
        }
        
        /**************************Cat********************/
        
        if(ParserXML.listObjCat(fileName) == null)
        {
            //TrocBdd t2 = new TrocBdd();
            t.setIdObjCat(null);
            t.setObjNameCat(null);
            t.setObjDetailsCat(null);
            t.setObjImageCat(null);
        }
        else
        {
            for(int i = 0; i < ParserXML.listObjCat(fileName).size() ; i+=4)
            {
                TrocBdd t2 = new TrocBdd();
                t2.setIdObjCat((String) ParserXML.listObjCat(fileName).get(i));
                t2.setObjNameCat((String) ParserXML.listObjCat(fileName).get(i+1));
                t2.setObjDetailsCat((String) ParserXML.listObjCat(fileName).get(i+2));
                t2.setObjImageCat((String) ParserXML.listObjCat(fileName).get(i+3));
                t2.setIdF(t.getIdF());
                bdd.save(t2);
            }
        }
        
    }

    @RequestMapping(value = "/ReceiveXML")
    public String receiverPage() throws Exception {
        try{
      
            lireHeader("fichierXML.xml");
			
		}catch(Exception e) {
            System.err.println("Error in xml file \n"+e);
            return "ReceiveXML.html";
        
        }

        return "ReceiveXML";
    }
    
}
