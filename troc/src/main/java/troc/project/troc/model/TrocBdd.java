package troc.project.troc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class TrocBdd {
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    int idF;
    int nbMsg;
    int idUserTrans;
    String transmitter;
    int idUserRec;
    String receiver;
    String authRef;
    String authDate;

    int idMsg;
    String dateMsg;
    String valideDuree;
    String authReq;
    String authRefMsg;
    String authDateMsg;

    int idPropMsgAcc;
    int idPropMsgDeny;
    String raisonDeny;

    String idObjBartRec;
    String objBartNameRec;
    String objBartDetailsRec;
    String objBartImageRec;

    String idObjBartSend;
    String objBartNameSend;
    String objBartDetailsSend;
    String objBartImageSend;

    int idPrevMsg;

    String idObjReq;
    String objNameReqRcv;
    String objDetailsReqRcv;
    String objImageReqRcv;

    String idObjDonnSend;
    String objDonnNameSend;
    String objDonnDetailsSend;
    String objDonnImageSend;

    String CatReq;

    int idCatReqMsg;
    String catDate;
    String idObjCat;
    String objNameCat;
    String objDetailsCat;
    String objImageCat;

    int idNoCatReqMsg;
    String raisonNoCat;

    int idMsgError;
    int idError;
    String msgErr;

    public TrocBdd(){}

    public TrocBdd(int idf){
        this.idF=idf;
    }


}