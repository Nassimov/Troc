package troc.project.troc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import troc.project.troc.ParserXML;
import troc.project.troc.model.TrocBdd;
import troc.project.troc.repositories.TrocBddRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@Controller
public class ReceiveController {

    @Inject
    TrocBddRepository bdd;

    TrocBdd t = new TrocBdd();
    int idff = -9999999;

    public int lireHeader(String fileName) throws Exception {

        // Pour enregistrer le Header
        t.setIdF(ParserXML.recupIdF(fileName));
        t.setNbMsg(ParserXML.nombreMsg(fileName));
        t.setIdUserTrans(ParserXML.IdUserTra(fileName));
        t.setTransmitter(ParserXML.recupererTransmitter(fileName));
        t.setIdUserRec(ParserXML.IdUserRecev(fileName));
        t.setReceiver(ParserXML.recupReceiver(fileName));
        // optionel
        t.setAuthRef(ParserXML.recupAuthRefHead(fileName));
        t.setAuthDate(ParserXML.recupDateHead(fileName));
        bdd.save(t);

        for(int i=0 ; i < ParserXML.nbMsgReel(fileName).size(); i++)
        {
            TrocBdd t2 = new TrocBdd();
            
            // Enregistrer le body
            //t2.setIdMsg(ParserXML.idMsg(fileName));

            t2.setDateMsg(ParserXML.recupDateMsg(fileName));
            t2.setValideDuree(ParserXML.recupDateValid(fileName));

            // optionnel
            t2.setAuthReq(ParserXML.AuthReq(fileName));
            t2.setAuthRefMsg(ParserXML.recupAuthRef(fileName));
            t2.setAuthDateMsg(ParserXML.recupDate(fileName));
            t2.setIdPropMsgAcc(ParserXML.recupIdPropMsgAcc(fileName));
            t2.setIdPropMsgDeny(ParserXML.recupIdPropMsgDeny(fileName));
            t2.setCatReq(ParserXML.recupCatReq(fileName));
            t2.setCatDate(ParserXML.recupCatDate(fileName));
            t2.setIdMsgError(ParserXML.idMsgErr(fileName));
            t2.setIdError(ParserXML.idErr(fileName));
            t2.setMsgErr(ParserXML.errMsg(fileName));

            if (ParserXML.recupIdPropMsgAcc(fileName) == -1)
                t.setIdPropMsgAcc(-1);
            else
                t.setIdPropMsgAcc(ParserXML.recupIdPropMsgAcc(fileName));

            if (ParserXML.recupIdPropMsgDeny(fileName) == -1)
                t.setIdPropMsgDeny(-1);
            else
                t.setIdPropMsgDeny(ParserXML.recupIdPropMsgDeny(fileName));

            if (ParserXML.reasonDeny(fileName) == null)
                t.setRaisonDeny(null);
            else
                t.setRaisonDeny(ParserXML.reasonDeny(fileName));

            if (ParserXML.recupPrevMsgBart(fileName) == null)
                t.setIdPrevMsgBarter(null);
            else
                t.setIdPrevMsgBarter(ParserXML.recupPrevMsgBart(fileName));

            if (ParserXML.recupPrevMsgReq(fileName) == null)
                t.setIdPrevMsgReq(null);
            else
                t.setIdPrevMsgReq(ParserXML.recupPrevMsgReq(fileName));

            if (ParserXML.recupPrevMsgDonn(fileName) == null)
                t.setIdPrevMsgDonn(null);
            else
                t.setIdPrevMsgDonn(ParserXML.recupPrevMsgDonn(fileName));

            if (ParserXML.recupCatReqMsg(fileName) == -1)
                t.setIdCatReqMsg(-1);
            else
                t.setIdCatReqMsg(ParserXML.recupCatReqMsg(fileName));

            if (ParserXML.recupNoCatReqMsg(fileName) == -1)
                t.setIdNoCatReqMsg(-1);
            else
                t.setIdNoCatReqMsg(ParserXML.recupNoCatReqMsg(fileName));

            if (ParserXML.recupNoCatReason(fileName) == null)
                t.setRaisonNoCat(null);
            else
                t.setRaisonNoCat(ParserXML.recupNoCatReason(fileName));

            // System.err.println(ParserXML.recupCatReqMsg(fileName));
            t2.setIdF(t.getIdF());
            t2.setIdMsg(ParserXML.nbMsgReel(fileName).get(i));
            bdd.save(t2);
        }
        

        /************************** Troc ********************/

        if (ParserXML.listObjRcvBarter(fileName) == null) {
            // TrocBdd t2 = new TrocBdd();
            t.setIdObjBartRec(null);
            t.setObjBartNameRec(null);
            t.setObjBartDetailsRec(null);
            t.setObjBartImageRec(null);
        } else {
            for (int i = 0; i < ParserXML.listObjRcvBarter(fileName).size(); i += 4) {
                TrocBdd t2 = new TrocBdd();
                t2.setIdObjBartRec((String) ParserXML.listObjRcvBarter(fileName).get(i));
                t2.setObjBartNameRec((String) ParserXML.listObjRcvBarter(fileName).get(i + 1));
                t2.setObjBartDetailsRec((String) ParserXML.listObjRcvBarter(fileName).get(i + 2));
                t2.setObjBartImageRec((String) ParserXML.listObjRcvBarter(fileName).get(i + 3));
                t2.setIdF(t.getIdF());
                t2.setIdMsg(ParserXML.SavoirIdMsg(fileName, "barter"));
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
            for (int i = 0; i < ParserXML.listObjSndBarter(fileName).size(); i += 4) {
                TrocBdd t2 = new TrocBdd();
                t2.setIdObjBartSend((String) ParserXML.listObjSndBarter(fileName).get(i));
                t2.setObjBartNameSend((String) ParserXML.listObjSndBarter(fileName).get(i + 1));
                t2.setObjBartDetailsSend((String) ParserXML.listObjSndBarter(fileName).get(i + 2));
                t2.setObjBartImageSend((String) ParserXML.listObjSndBarter(fileName).get(i + 3));
                t2.setIdF(t.getIdF());
                t2.setIdMsg(ParserXML.SavoirIdMsg(fileName, "barter"));
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
            for (int i = 0; i < ParserXML.listObjRcvReq(fileName).size(); i += 4) {
                TrocBdd t2 = new TrocBdd();
                t2.setIdObjReq((String) ParserXML.listObjRcvReq(fileName).get(i));
                t2.setObjNameReqRcv((String) ParserXML.listObjRcvReq(fileName).get(i + 1));
                t2.setObjDetailsReqRcv((String) ParserXML.listObjRcvReq(fileName).get(i + 2));
                t2.setObjImageReqRcv((String) ParserXML.listObjRcvReq(fileName).get(i + 3));
                t2.setIdF(t.getIdF());
                t2.setIdMsg(ParserXML.SavoirIdMsg(fileName, "request"));
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
            for (int i = 0; i < ParserXML.listObjSndDonn(fileName).size(); i += 4) {
                TrocBdd t2 = new TrocBdd();
                t2.setIdObjDonnSend((String) ParserXML.listObjSndDonn(fileName).get(i));
                t2.setObjDonnNameSend((String) ParserXML.listObjSndDonn(fileName).get(i + 1));
                t2.setObjDonnDetailsSend((String) ParserXML.listObjSndDonn(fileName).get(i + 2));
                t2.setObjDonnImageSend((String) ParserXML.listObjSndDonn(fileName).get(i + 3));
                t2.setIdF(t.getIdF());
                t2.setIdMsg(ParserXML.SavoirIdMsg(fileName, "donation"));
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
            for (int i = 0; i < ParserXML.listObjCat(fileName).size(); i += 4) {
                TrocBdd t2 = new TrocBdd();
                t2.setIdObjCat((String) ParserXML.listObjCat(fileName).get(i));
                t2.setObjNameCat((String) ParserXML.listObjCat(fileName).get(i + 1));
                t2.setObjDetailsCat((String) ParserXML.listObjCat(fileName).get(i + 2));
                t2.setObjImageCat((String) ParserXML.listObjCat(fileName).get(i + 3));
                t2.setIdF(t.getIdF());
                t2.setIdMsg(ParserXML.SavoirIdMsg(fileName, "cat"));
                bdd.save(t2);
            }
        }

        return t.getIdF();

    }

    @RequestMapping(value = "/ReceiveXML")
    public String receiverPage(Model m) throws Exception {

        List<TrocBdd> lb = new ArrayList<>();
        List<String> listeRcvBarter = new ArrayList<>();
        List<String> listeSndBarter = new ArrayList<>();
        List<String> listeObjReq = new ArrayList<>();
        List<String> listeObjDonn = new ArrayList<>();
        List<String> listeObjCat = new ArrayList<>();
        try {
            if (idff == -9999999) 
                idff = lireHeader("fichierXML.xml");
            
            lb = bdd.findAllByIdF(idff);

            for (int i = 0; i < lb.size(); i++) {
                if (lb.get(i).getIdObjBartRec() != null) {
                    listeRcvBarter.add(lb.get(i).getIdObjBartRec());
                    listeRcvBarter.add(lb.get(i).getObjBartNameRec());
                    listeRcvBarter.add(lb.get(i).getObjBartDetailsRec());
                    listeRcvBarter.add(lb.get(i).getObjBartImageRec());
                }
                if (lb.get(i).getIdObjBartSend() != null) {
                    listeSndBarter.add(lb.get(i).getIdObjBartSend());
                    listeSndBarter.add(lb.get(i).getObjBartNameSend());
                    listeSndBarter.add(lb.get(i).getObjBartDetailsSend());
                    listeSndBarter.add(lb.get(i).getObjBartImageSend());
                }
                if (lb.get(i).getIdObjReq() != null) {
                    listeObjReq.add(lb.get(i).getIdObjReq());
                    listeObjReq.add(lb.get(i).getObjNameReqRcv());
                    listeObjReq.add(lb.get(i).getObjDetailsReqRcv());
                    listeObjReq.add(lb.get(i).getObjImageReqRcv());
                }
                if (lb.get(i).getIdObjDonnSend() != null) {
                    listeObjDonn.add(lb.get(i).getIdObjDonnSend());
                    listeObjDonn.add(lb.get(i).getObjDonnNameSend());
                    listeObjDonn.add(lb.get(i).getObjDonnDetailsSend());
                    listeObjDonn.add(lb.get(i).getObjDonnImageSend());
                }
                if (lb.get(i).getIdObjCat() != null) {
                    listeObjCat.add(lb.get(i).getIdObjCat());
                    listeObjCat.add(lb.get(i).getObjNameCat());
                    listeObjCat.add(lb.get(i).getObjDetailsCat());
                    listeObjCat.add(lb.get(i).getObjImageCat());
                }
            }
            if (listeRcvBarter.size() != 0)
            {
                m.addAttribute("idPrevMsgBarter", lb.get(0).getIdPrevMsgBarter());
                m.addAttribute("listeRcvBarter", listeRcvBarter);
            }
            if (listeSndBarter.size() != 0)
            {
                m.addAttribute("idPrevMsgBarter", lb.get(0).getIdPrevMsgBarter());
                m.addAttribute("listeSndBarter", listeSndBarter);
            }
            if (listeObjReq.size() != 0)
            {
                m.addAttribute("idPrevMsgReq", lb.get(0).getIdPrevMsgReq());
                m.addAttribute("listeObjReq", listeObjReq);
            }
            if (listeObjDonn.size() != 0)
            {
                m.addAttribute("idPrevMsgDonn", lb.get(0).getIdPrevMsgDonn());
                m.addAttribute("listeObjDonn", listeObjDonn);
            }
            if (listeObjCat.size() != 0)
            {
                m.addAttribute("idCatReqMsg", lb.get(0).getIdCatReqMsg());
                m.addAttribute("catDate", lb.get(0).getCatDate());
                m.addAttribute("listeObjCat", listeObjCat);
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

            m.addAttribute("dateMsg", lb.get(0).getDateMsg());
            m.addAttribute("valideDuree", lb.get(0).getValideDuree());

            m.addAttribute("authReq", lb.get(0).getAuthReq());
            m.addAttribute("authRefMsg", lb.get(0).getAuthRefMsg());
            m.addAttribute("authDateMsg", lb.get(0).getAuthDateMsg());

            m.addAttribute("idPropMsgAcc", lb.get(0).getIdPropMsgAcc());

            m.addAttribute("idPropMsgDeny", lb.get(0).getIdPropMsgDeny());
            m.addAttribute("raisonDeny", lb.get(0).getRaisonDeny());
            
            m.addAttribute("catReq", lb.get(0).getCatReq());

            m.addAttribute("idNoCatReqMsg", lb.get(0).getIdNoCatReqMsg());
            m.addAttribute("raisonNoCat", lb.get(0).getRaisonNoCat());

            m.addAttribute("idMsgError", lb.get(0).getIdMsgError());
            m.addAttribute("idError", lb.get(0).getIdError());
            m.addAttribute("msgErr", lb.get(0).getMsgErr());
            //System.err.println("Barter id : "+ParserXML.SavoirIdMsg("fichierXML.xml", "barter"));
            //System.err.println("Req id : "+ParserXML.SavoirIdMsg("fichierXML.xml", "request"));
            
            //System.err.println("Recever : "+ParserXML.nbMsgReel("fichierXML.xml"));

        } catch (Exception e) {
            System.err.println("Error in xml file \n" + e);
            return "ReceiveXML.html";

        }

        return "ReceiveXML";
    }

}
