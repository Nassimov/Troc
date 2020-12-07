package troc.project.troc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import troc.project.troc.ParserXML;
import troc.project.troc.model.TrocBdd;
import troc.project.troc.repositories.TrocBddRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

@Controller
public class ReceiveController {

    @Inject
    TrocBddRepository bdd;

    TrocBdd t = new TrocBdd();
    int idff = -9999999;
    int idMsg = -9999999;

    public int lireHeader(String fileName) throws Exception {

        // Pour enregistrer le Header
        t.setIdF(ParserXML.recupIdF(fileName));
        //t.setNbMsg(ParserXML.nombreMsg(fileName));
        t.setNbMsg(ParserXML.nbMsgReel(fileName).size());
        t.setIdUserTrans(ParserXML.IdUserTra(fileName));
        t.setTransmitter(ParserXML.recupererTransmitter(fileName));
        t.setIdUserRec(ParserXML.IdUserRecev(fileName));
        t.setReceiver(ParserXML.recupReceiver(fileName));
        // optionel
        t.setAuthRef(ParserXML.recupAuthRefHead(fileName));
        t.setAuthDate(ParserXML.recupDateHead(fileName));
        bdd.save(t);

        //for(int i=0 ; i < ParserXML.nbMsgReel(fileName).size(); i++)
        
            //TrocBdd t2 = new TrocBdd();
            
            // Enregistrer le body

            //t2.setIdMsg(ParserXML.idMsg(fileName));

            //t.setDateMsg(ParserXML.recupDateMsg(fileName));
            
            if (ParserXML.dateMsgList(fileName) == null) 
                t.setDateMsg(null);
            else {
                for (int i = 0; i < ParserXML.dateMsgList(fileName).size(); i+=2) {
                    TrocBdd t2 = new TrocBdd();
                    t2.setIdMsg(Integer.parseInt(ParserXML.dateMsgList(fileName).get(i)));
                    t2.setDateMsg((String) ParserXML.dateMsgList(fileName).get(i+1));
                    t2.setIdF(t.getIdF());
                    //System.err.println();
                    bdd.save(t2);
                }
            }
            if (ParserXML.ListDateValid(fileName) == null) 
                t.setValideDuree(null);
            else {
                for (int i = 0; i < ParserXML.ListDateValid(fileName).size(); i+=2) {
                    TrocBdd t2 = new TrocBdd();
                    t2.setIdMsg(Integer.parseInt(ParserXML.ListDateValid(fileName).get(i)));
                    t2.setValideDuree((String) ParserXML.ListDateValid(fileName).get(i+1));
                    t2.setIdF(t.getIdF());
                    //System.err.println();
                    bdd.save(t2);
                }
            }
            // optionnel
            if (ParserXML.ListAuthReq(fileName) == null) 
                t.setAuthReq(null);
            else {
                for (int i = 0; i < ParserXML.ListAuthReq(fileName).size(); i+=2) {
                    TrocBdd t2 = new TrocBdd();
                    t2.setIdMsg(Integer.parseInt(ParserXML.ListAuthReq(fileName).get(i)));
                    t2.setAuthReq((String) ParserXML.ListAuthReq(fileName).get(i+1));
                    t2.setIdF(t.getIdF());
                    //System.err.println();
                    bdd.save(t2);
                }
            }

            if (ParserXML.ListAuthref(fileName) == null) 
                t.setAuthRefMsg(null);
            else {
                for (int i = 0; i < ParserXML.ListAuthref(fileName).size(); i+=2) {
                    TrocBdd t2 = new TrocBdd();
                    t2.setIdMsg(Integer.parseInt(ParserXML.ListAuthref(fileName).get(i)));
                    t2.setAuthRefMsg((String) ParserXML.ListAuthref(fileName).get(i+1));
                    t2.setIdF(t.getIdF());
                    //System.err.println();
                    bdd.save(t2);
                }
            }

            if (ParserXML.ListAuthDate(fileName) == null) 
                t.setAuthDateMsg(null);
            else {
                for (int i = 0; i < ParserXML.ListAuthDate(fileName).size(); i+=2) {
                    TrocBdd t2 = new TrocBdd();
                    t2.setIdMsg(Integer.parseInt(ParserXML.ListAuthDate(fileName).get(i)));
                    t2.setAuthDateMsg((String) ParserXML.ListAuthDate(fileName).get(i+1));
                    t2.setIdF(t.getIdF());
                    //System.err.println();
                    bdd.save(t2);
                }
            }

            if (ParserXML.ListAcc(fileName) == null) 
                t.setIdPropMsgAcc(-1);
            else {
                for (int i = 0; i < ParserXML.ListAcc(fileName).size(); i+=2) {
                    TrocBdd t2 = new TrocBdd();
                    t2.setIdMsg(Integer.parseInt(ParserXML.ListAcc(fileName).get(i)));
                    t2.setIdPropMsgAcc(Integer.parseInt(ParserXML.ListAcc(fileName).get(i+1)));
                    t2.setIdF(t.getIdF());
                    //System.err.println();
                    bdd.save(t2);
                }
            }

            if (ParserXML.ListDeny(fileName) == null) 
                t.setIdPropMsgDeny(-1);
            else {
                for (int i = 0; i < ParserXML.ListDeny(fileName).size(); i+=2) {
                    TrocBdd t2 = new TrocBdd();
                    t2.setIdMsg(Integer.parseInt(ParserXML.ListDeny(fileName).get(i)));
                    t2.setIdPropMsgDeny(Integer.parseInt(ParserXML.ListDeny(fileName).get(i+1)));
                    t2.setIdF(t.getIdF());
                    bdd.save(t2);
                }
            }

            if (ParserXML.ListReasonDeny(fileName) == null) 
                t.setRaisonDeny(null);
            else {
                for (int i = 0; i < ParserXML.ListReasonDeny(fileName).size(); i+=2) {
                    TrocBdd t2 = new TrocBdd();
                    t2.setIdMsg(Integer.parseInt(ParserXML.ListReasonDeny(fileName).get(i)));
                    t2.setRaisonDeny(ParserXML.ListReasonDeny(fileName).get(i+1));
                    t2.setIdF(t.getIdF());
                    bdd.save(t2);
                }
            }

            if (ParserXML.ListPrevMsg(fileName) == null) 
                t.setIdPrevMsg(-1);
            else {
                for (int i = 0; i < ParserXML.ListPrevMsg(fileName).size(); i+=2) {
                    TrocBdd t2 = new TrocBdd();
                    t2.setIdMsg(Integer.parseInt(ParserXML.ListPrevMsg(fileName).get(i)));
                    t2.setIdPrevMsg(Integer.parseInt(ParserXML.ListPrevMsg(fileName).get(i+1)));
                    t2.setIdF(t.getIdF());
                    bdd.save(t2);
                }
            }

            if (ParserXML.ListCatReq(fileName) == null) 
                t.setCatReq(null);
            else {
                for (int i = 0; i < ParserXML.ListCatReq(fileName).size(); i+=2) {
                    TrocBdd t2 = new TrocBdd();
                    t2.setIdMsg(Integer.parseInt(ParserXML.ListCatReq(fileName).get(i)));
                    t2.setCatReq(ParserXML.ListCatReq(fileName).get(i+1));
                    t2.setIdF(t.getIdF());
                    bdd.save(t2);
                }
            }
            if (ParserXML.ListCatReqMsgETdate(fileName) == null) 
            {
                t.setIdCatReqMsg(-1);
                t.setCatDate(null);
            }
            else {
                for (int i = 0; i < ParserXML.ListCatReqMsgETdate(fileName).size(); i+=3) {
                    TrocBdd t2 = new TrocBdd();
                    t2.setIdMsg(Integer.parseInt(ParserXML.ListCatReqMsgETdate(fileName).get(i)));
                    t2.setIdCatReqMsg(Integer.parseInt(ParserXML.ListCatReqMsgETdate(fileName).get(i+1)));
                    t2.setCatDate(ParserXML.ListCatReqMsgETdate(fileName).get(i+2));
                    t2.setIdF(t.getIdF());
                    bdd.save(t2);
                }
            }
            if (ParserXML.ListNoCat(fileName) == null) 
            {
                t.setIdNoCatReqMsg(-1);
                t.setRaisonNoCat(null);
            }
            else {
                for (int i = 0; i < ParserXML.ListNoCat(fileName).size(); i+=3) {
                    TrocBdd t2 = new TrocBdd();
                    t2.setIdMsg(Integer.parseInt(ParserXML.ListNoCat(fileName).get(i)));
                    t2.setIdNoCatReqMsg(Integer.parseInt(ParserXML.ListNoCat(fileName).get(i+1)));
                    t2.setRaisonNoCat(ParserXML.ListNoCat(fileName).get(i+2));
                    t2.setIdF(t.getIdF());
                    bdd.save(t2);
                }
            }
            if (ParserXML.ListErr(fileName) == null) 
            {
                t.setIdNoCatReqMsg(-1);
                t.setRaisonNoCat(null);
            }
            else {
                for (int i = 0; i < ParserXML.ListErr(fileName).size(); i+=4) {
                    TrocBdd t2 = new TrocBdd();
                    t2.setIdMsg(Integer.parseInt(ParserXML.ListErr(fileName).get(i)));
                    t2.setIdMsgError(Integer.parseInt(ParserXML.ListErr(fileName).get(i+1)));
                    t2.setIdError(Integer.parseInt(ParserXML.ListErr(fileName).get(i+1)));
                    t2.setMsgErr(ParserXML.ListErr(fileName).get(i+2));
                    t2.setIdF(t.getIdF());
                    bdd.save(t2);
                }
            }

            // System.err.println(ParserXML.recupCatReqMsg(fileName));
            //t.setIdF(t.getIdF());
            //t.setIdMsg(ParserXML.nbMsgReel(fileName).get(i));
            //bdd.save(t);
        
        

        /************************** Troc ********************/

        if (ParserXML.listObjRcvBarter(fileName) == null) {
            // TrocBdd t2 = new TrocBdd();
            t.setIdObjBartRec(null);
            t.setObjBartNameRec(null);
            t.setObjBartDetailsRec(null);
            t.setObjBartImageRec(null);
        } else {
            for (int i = 0; i < ParserXML.listObjRcvBarter(fileName).size(); i += 5) {
                TrocBdd t2 = new TrocBdd();
                t2.setIdObjBartRec((String) ParserXML.listObjRcvBarter(fileName).get(i));
                t2.setObjBartNameRec((String) ParserXML.listObjRcvBarter(fileName).get(i + 1));
                t2.setObjBartDetailsRec((String) ParserXML.listObjRcvBarter(fileName).get(i + 2));
                t2.setObjBartImageRec((String) ParserXML.listObjRcvBarter(fileName).get(i + 3));
                t2.setIdF(t.getIdF());
                t2.setIdMsg(Integer.parseInt(ParserXML.listObjRcvBarter(fileName).get(i + 4)));
                bdd.save(t2);
            }
        }

        if (ParserXML.listObjSndBarter(fileName) == null) {
            // TrocBdd t2 = new TrocBdd();
            t.setIdObjBartSend(null);
            t.setObjBartNameSend(null);
            t.setObjBartDetailsSend(null);
            t.setObjBartImageSend(null);
        } else {
            for (int i = 0; i < ParserXML.listObjSndBarter(fileName).size(); i += 5) {
                TrocBdd t2 = new TrocBdd();
                t2.setIdObjBartSend((String) ParserXML.listObjSndBarter(fileName).get(i));
                t2.setObjBartNameSend((String) ParserXML.listObjSndBarter(fileName).get(i + 1));
                t2.setObjBartDetailsSend((String) ParserXML.listObjSndBarter(fileName).get(i + 2));
                t2.setObjBartImageSend((String) ParserXML.listObjSndBarter(fileName).get(i + 3));
                t2.setIdF(t.getIdF());
                t2.setIdMsg(Integer.parseInt(ParserXML.listObjSndBarter(fileName).get(i + 4)));
                bdd.save(t2);
            }
        }

        /************************** Request ********************/

        if (ParserXML.listObjRcvReq(fileName) == null) {
            // TrocBdd t2 = new TrocBdd();
            t.setIdObjReq(null);
            t.setObjNameReqRcv(null);
            t.setObjDetailsReqRcv(null);
            t.setObjImageReqRcv(null);
        } else {
            for (int i = 0; i < ParserXML.listObjRcvReq(fileName).size(); i += 5) {
                TrocBdd t2 = new TrocBdd();
                t2.setIdObjReq((String) ParserXML.listObjRcvReq(fileName).get(i));
                t2.setObjNameReqRcv((String) ParserXML.listObjRcvReq(fileName).get(i + 1));
                t2.setObjDetailsReqRcv((String) ParserXML.listObjRcvReq(fileName).get(i + 2));
                t2.setObjImageReqRcv((String) ParserXML.listObjRcvReq(fileName).get(i + 3));
                t2.setIdF(t.getIdF());
                t2.setIdMsg(Integer.parseInt(ParserXML.listObjRcvReq(fileName).get(i + 4)));
                bdd.save(t2);
            }
        }

        /************************** Donnation ********************/

        if (ParserXML.listObjSndDonn(fileName) == null) {
            // TrocBdd t2 = new TrocBdd();
            t.setIdObjDonnSend(null);
            t.setObjDonnNameSend(null);
            t.setObjDonnDetailsSend(null);
            t.setObjDonnImageSend(null);
        } else {
            for (int i = 0; i < ParserXML.listObjSndDonn(fileName).size(); i += 5) {
                TrocBdd t2 = new TrocBdd();
                t2.setIdObjDonnSend((String) ParserXML.listObjSndDonn(fileName).get(i));
                t2.setObjDonnNameSend((String) ParserXML.listObjSndDonn(fileName).get(i + 1));
                t2.setObjDonnDetailsSend((String) ParserXML.listObjSndDonn(fileName).get(i + 2));
                t2.setObjDonnImageSend((String) ParserXML.listObjSndDonn(fileName).get(i + 3));
                t2.setIdF(t.getIdF());
                t2.setIdMsg(Integer.parseInt(ParserXML.listObjSndDonn(fileName).get(i + 4)));
                bdd.save(t2);
            }
        }

        /************************** Cat ********************/

        if (ParserXML.listObjCat(fileName) == null) {
            // TrocBdd t2 = new TrocBdd();
            t.setIdObjCat(null);
            t.setObjNameCat(null);
            t.setObjDetailsCat(null);
            t.setObjImageCat(null);
        } else {
            for (int i = 0; i < ParserXML.listObjCat(fileName).size(); i += 5) {
                TrocBdd t2 = new TrocBdd();
                t2.setIdObjCat((String) ParserXML.listObjCat(fileName).get(i));
                t2.setObjNameCat((String) ParserXML.listObjCat(fileName).get(i + 1));
                t2.setObjDetailsCat((String) ParserXML.listObjCat(fileName).get(i + 2));
                t2.setObjImageCat((String) ParserXML.listObjCat(fileName).get(i + 3));
                t2.setIdF(t.getIdF());
                t2.setIdMsg(Integer.parseInt(ParserXML.listObjCat(fileName).get(i + 4)));
                bdd.save(t2);
            }
        }

        return t.getIdF();

    }

    @RequestMapping(value = "/ReceiveXML")
    public String receiverPage(Model m) throws Exception {

        List<TrocBdd> lb = new ArrayList<>();
        HashMap<List<String>, Integer> hmRcvBart = new HashMap<>();
        HashMap<List<String>, Integer> hmSndBart = new HashMap<>();
        HashMap<List<String>, Integer> hmObjReq = new HashMap<>();
        HashMap<List<String>, Integer> hmObjDonn = new HashMap<>();
        HashMap<List<String>, Integer> hmObjCat = new HashMap<>();
        HashMap<List<String>, Integer> hmDateMsg = new HashMap<>();
        HashMap<List<String>, Integer> hmValDu = new HashMap<>();
        HashMap<List<String>, Integer> hmAuthReq = new HashMap<>();
        HashMap<List<String>, Integer> hmAuthRefMsg = new HashMap<>();
        HashMap<List<String>, Integer> hmAuthDatMsg = new HashMap<>();
        HashMap<List<Integer>, Integer> hmIdAcc = new HashMap<>();
        HashMap<List<Integer>, Integer> hmIdDeny = new HashMap<>();
        HashMap<List<String>, Integer> hmReasonDeny = new HashMap<>();
        HashMap<List<String>, Integer> hmCatReq = new HashMap<>();
        HashMap<List<Integer>, Integer> hmIdNoCat = new HashMap<>();
        HashMap<List<String>, Integer> hmReasonNoCat = new HashMap<>();
        HashMap<List<Integer>, Integer> hmIdMsgError = new HashMap<>();
        HashMap<List<Integer>, Integer> hmIdErr = new HashMap<>();
        HashMap<List<String>, Integer> hmMsgErr = new HashMap<>();
        HashMap<List<Integer>, Integer> hmPrevMsg = new HashMap<>();
        HashMap<List<String>, Integer> hmCatDateETreqMsg = new HashMap<>();
        HashMap<List<String>, Integer> hmNoCat = new HashMap<>();
        HashMap<List<String>, Integer> hmErr = new HashMap<>();

        try {
            if (idff == -9999999) 
                idff = lireHeader("xml4.xml");
            
            lb = bdd.findAllByIdF(idff);

            for (int i = 0; i < lb.size(); i++) {
                
                if (lb.get(i).getIdObjBartRec() != null) {
                    List<String> listeRcvBarter = new ArrayList<>();
                    listeRcvBarter.add(lb.get(i).getIdObjBartRec());
                    listeRcvBarter.add(lb.get(i).getObjBartNameRec());
                    listeRcvBarter.add(lb.get(i).getObjBartDetailsRec());
                    listeRcvBarter.add(lb.get(i).getObjBartImageRec());
                    
                    hmRcvBart.put(listeRcvBarter,lb.get(i).getIdMsg());
                }
                if (lb.get(i).getIdPrevMsg() != -1 && lb.get(i).getIdPrevMsg() != 0) 
                {
                    List<Integer> listeIdPrevMsg = new ArrayList<>();
                    listeIdPrevMsg.add(lb.get(i).getIdPrevMsg());
                    hmPrevMsg.put(listeIdPrevMsg,lb.get(i).getIdMsg());
                }
                if (lb.get(i).getIdObjBartSend() != null) {
                    List<String> listeSndBarter = new ArrayList<>();
                    listeSndBarter.add(lb.get(i).getIdObjBartSend());
                    listeSndBarter.add(lb.get(i).getObjBartNameSend());
                    listeSndBarter.add(lb.get(i).getObjBartDetailsSend());
                    listeSndBarter.add(lb.get(i).getObjBartImageSend());

                    hmSndBart.put(listeSndBarter,lb.get(i).getIdMsg());
                }
                if (lb.get(i).getIdObjReq() != null) {
                    List<String> listeObjReq = new ArrayList<>();
                    listeObjReq.add(lb.get(i).getIdObjReq());
                    listeObjReq.add(lb.get(i).getObjNameReqRcv());
                    listeObjReq.add(lb.get(i).getObjDetailsReqRcv());
                    listeObjReq.add(lb.get(i).getObjImageReqRcv());
                    hmObjReq.put(listeObjReq,lb.get(i).getIdMsg());
                    System.err.println(hmObjReq);
                }
                if (lb.get(i).getIdObjDonnSend() != null) {
                    List<String> listeObjDonn = new ArrayList<>();
                    listeObjDonn.add(lb.get(i).getIdObjDonnSend());
                    listeObjDonn.add(lb.get(i).getObjDonnNameSend());
                    listeObjDonn.add(lb.get(i).getObjDonnDetailsSend());
                    listeObjDonn.add(lb.get(i).getObjDonnImageSend());
                    hmObjDonn.put(listeObjDonn,lb.get(i).getIdMsg());
                }
                if (lb.get(i).getIdObjCat() != null) {
                    List<String> listeObjCat = new ArrayList<>();
                    listeObjCat.add(lb.get(i).getIdObjCat());
                    listeObjCat.add(lb.get(i).getObjNameCat());
                    listeObjCat.add(lb.get(i).getObjDetailsCat());
                    listeObjCat.add(lb.get(i).getObjImageCat());
                    hmObjCat.put(listeObjCat,lb.get(i).getIdMsg());
                }
                if (lb.get(i).getDateMsg() != null) 
                {
                    List<String> listeDateMsg = new ArrayList<>();
                    listeDateMsg.add(lb.get(i).getDateMsg());
                    hmDateMsg.put(listeDateMsg,lb.get(i).getIdMsg());
                }

                if (lb.get(i).getValideDuree() != null)
                { 
                    List<String> listeValDu = new ArrayList<>();
                    listeValDu.add(lb.get(i).getValideDuree());
                    hmValDu.put(listeValDu,lb.get(i).getIdMsg());
                }

                if (lb.get(i).getAuthReq() != null)
                { 
                    List<String> listeAuthReq = new ArrayList<>();
                    listeAuthReq.add(lb.get(i).getAuthReq());
                    hmAuthReq.put(listeAuthReq,lb.get(i).getIdMsg());
                }

                if (lb.get(i).getAuthRefMsg() != null) 
                {
                    List<String> listeAuthRefMsg = new ArrayList<>();
                    listeAuthRefMsg.add(lb.get(i).getAuthRefMsg());
                    hmAuthRefMsg.put(listeAuthRefMsg,lb.get(i).getIdMsg());
                }

                if (lb.get(i).getAuthDateMsg() != null) 
                {
                    List<String> listeAuthDatMsg = new ArrayList<>();
                    listeAuthDatMsg.add(lb.get(i).getAuthDateMsg());
                    hmAuthDatMsg.put(listeAuthDatMsg,lb.get(i).getIdMsg());
                }

                if (lb.get(i).getIdPropMsgAcc() != -1 || lb.get(i).getIdPropMsgAcc() != 0) 
                {
                    List<Integer> listeIdAcc = new ArrayList<>();
                    listeIdAcc.add(lb.get(i).getIdPropMsgAcc());
                    hmIdAcc.put(listeIdAcc,lb.get(i).getIdMsg());
                }

                if (lb.get(i).getIdPropMsgDeny() != -1 || lb.get(i).getIdPropMsgDeny() != 0) 
                {
                    List<Integer> listeIdDeny = new ArrayList<>();
                    listeIdDeny.add(lb.get(i).getIdPropMsgDeny());
                    hmIdDeny.put(listeIdDeny,lb.get(i).getIdMsg());
                }

                if (lb.get(i).getRaisonDeny() != null) 
                {
                    List<String> listeReasonDeny = new ArrayList<>();
                    listeReasonDeny.add(lb.get(i).getRaisonDeny());
                    hmReasonDeny.put(listeReasonDeny,lb.get(i).getIdMsg());
                }

                if (lb.get(i).getCatReq() != null) 
                {
                    List<String> listeCatReq = new ArrayList<>();
                    listeCatReq.add(lb.get(i).getCatReq());
                    hmCatReq.put(listeCatReq,lb.get(i).getIdMsg());
                }

                if (lb.get(i).getCatDate() != null && lb.get(i).getIdCatReqMsg() != -1 && lb.get(i).getIdCatReqMsg() != 0) 
                {
                    List<String> listeCat = new ArrayList<>();
                    listeCat.add(Integer.toString(lb.get(i).getIdCatReqMsg()));
                    listeCat.add(lb.get(i).getCatDate());
                    hmCatDateETreqMsg.put(listeCat,lb.get(i).getIdMsg());
                }

                if (lb.get(i).getCatDate() != null && lb.get(i).getIdNoCatReqMsg() != -1 && lb.get(i).getIdNoCatReqMsg() != 0) 
                {
                    List<String> listeNoCat = new ArrayList<>();
                    listeNoCat.add(Integer.toString(lb.get(i).getIdNoCatReqMsg()));
                    listeNoCat.add(lb.get(i).getRaisonNoCat());
                    hmNoCat.put(listeNoCat,lb.get(i).getIdMsg());
                }

                if (lb.get(i).getMsgErr() != null && lb.get(i).getIdMsgError() != -1 && lb.get(i).getIdMsgError() != 0 && lb.get(i).getIdError() != -1 && lb.get(i).getIdError() != 0) 
                {
                    List<String> listeErr = new ArrayList<>();
                    listeErr.add(Integer.toString(lb.get(i).getIdMsgError()));
                    listeErr.add(Integer.toString(lb.get(i).getIdError()));
                    listeErr.add(lb.get(i).getMsgErr());
                    hmErr.put(listeErr,lb.get(i).getIdMsg());
                }

            }
            if (hmRcvBart.size() != 0)
                m.addAttribute("listeRcvBarter", hmRcvBart);
            if (hmSndBart.size() != 0)
                m.addAttribute("listeSndBarter", hmSndBart);
            if (hmPrevMsg.size() != 0)
                m.addAttribute("idPrevMsg", hmPrevMsg);
            if (hmCatDateETreqMsg.size() != 0)
                m.addAttribute("restCat", hmCatDateETreqMsg);
            if (hmErr.size() != 0)
                m.addAttribute("err", hmErr);
            if (hmObjCat.size() != 0)
            {
                //m.addAttribute("idCatReqMsg", lb.get(0).getIdCatReqMsg());
                //m.addAttribute("catDate", lb.get(0).getCatDate());
                m.addAttribute("listeObjCat", hmObjCat);
            }
            if (hmNoCat.size() != 0)
                m.addAttribute("noCat", hmNoCat);
            if (hmDateMsg.size() != 0)
                m.addAttribute("listeDateMsg", hmDateMsg);

            if (hmValDu.size() != 0)
                m.addAttribute("listeValDu", hmValDu);

            if (hmAuthReq.size() != 0)
                m.addAttribute("listeAuthReq", hmAuthReq);

            if (hmAuthDatMsg.size() != 0)
            {
                m.addAttribute("listeAuthDatMsg", hmAuthDatMsg);
            }
            if (hmAuthDatMsg.size() != 0)
            {    
                m.addAttribute("listeAuthDatMsg", hmAuthDatMsg);
            }
            if (hmIdAcc.size() != 0)
            {
                m.addAttribute("listeIdAcc", hmIdAcc);
            }
            if (hmIdDeny.size() != 0)
            {
                m.addAttribute("listeIdDeny", hmIdDeny);
            }
            if (hmReasonDeny.size() != 0)
            {   
                m.addAttribute("listeReasonDeny", hmReasonDeny);
            }
            if (hmCatReq.size() != 0)
            {   
                m.addAttribute("listeCatReq", hmCatReq);
            }
            if (hmIdNoCat.size() != 0)
            {
                m.addAttribute("listeIdNoCat", hmIdNoCat);
            }
            if (hmReasonNoCat.size() != 0)
            {   
                m.addAttribute("listeReasonNoCat", hmReasonNoCat);
            }
            if (hmIdErr.size() != 0)
            {
                m.addAttribute("listeIdErr", hmIdErr);
            }
            if (hmIdErr.size() != 0)
            {   
                m.addAttribute("listeIdErr", hmIdErr);
            }
            if (hmMsgErr.size() != 0)
            { 
                m.addAttribute("listeMsgErr", hmMsgErr);   
            }
            /****************Objets obligatoires a inserer*************/
            m.addAttribute("idf", idff);
            m.addAttribute("nbMsg", lb.get(0).getNbMsg());
            m.addAttribute("idUserTrans", lb.get(0).getIdUserTrans());
            m.addAttribute("transmitter", lb.get(0).getTransmitter());
            m.addAttribute("idUserRec", lb.get(0).getIdUserRec());
            m.addAttribute("receiver", lb.get(0).getReceiver());
            m.addAttribute("idMsg", lb.get(0).getIdMsg());

            /****************Objets optionnelles*************/
            m.addAttribute("authRef", lb.get(0).getAuthRef());
            m.addAttribute("authDate", lb.get(0).getAuthDate());

            

            m.addAttribute("listeNbrsMsg", ParserXML.nbMsgReel("xml4.xml"));
            //System.err.println("Barter id : "+ParserXML.SavoirIdMsg("fichierXML.xml", "barter"));
            //System.err.println("Req id : "+ParserXML.SavoirIdMsg("fichierXML.xml", "request"));
            //System.err.println("list : "+ParserXML.recupCatDateETreqMsg("fichierXML.xml"));
            //System.err.println("list : "+ParserXML.recupDateMsg("fichierXML.xml"));

        } catch (Exception e) {
            System.err.println("Error in xml file \n" + e);
            return "ReceiveXML.html";

        }
        
        return "ReceiveXML";
    }

}
