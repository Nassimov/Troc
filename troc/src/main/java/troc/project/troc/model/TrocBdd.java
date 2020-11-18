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

    int idObjTrocRec;
    String objectNameRec;
    String objectDetailsRec;
    String objectImageRec;

    int idObjTrocSend;
    String objectNameSend;
    String objectDetailsSend;
    String objectImageSend;

    int rcvObjectList;
    int idPrevMsgReq;

    int sndObjectList;
    int idPrevMsgDonn;

    int catRequest;

    int idCatRequestMsg;
    String catDate;
    int idObjectCat;
    String objectNameCat;
    String objectDetailsCat;
    String objectImageCat;

    int idNoCatRequestMsg;
    String raisonNoCat;

    int idMsgError;
    int idError;

    public TrocBdd(){}

    public TrocBdd(int idf){
        this.idF=idf;
    }


}