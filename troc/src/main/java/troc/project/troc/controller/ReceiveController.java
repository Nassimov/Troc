package troc.project.troc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

//import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import troc.project.troc.ParserXML;
import troc.project.troc.model.*;
import troc.project.troc.model.Object;
import troc.project.troc.repositories.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

@Controller
public class ReceiveController {
    // 1024 bye = 1kb setting up max size 5 kb
    static final Long EXPECTED_SIZE = 1024l * 5l;
    static final int FILE_ALREADY_READIT = -9999999;
    static final int ERROR_FILE_NAME = -8888888;
    static final int ERROR_DATE_HAS_EXPIRED = -7777777;
    static final int ERROR_FILE_UN_EXPECTED_SIZE = -6666666;
    static final int ERROR_NB_MSG_UNMATCHED = -5555555;
    static final int ERROR_UNVALID_DURATION = -4444444;
    String globFileName;

    @Inject
    TrocBddRepository bdd;
    @Inject
    AcceptRepository acceptRepository;
    @Inject
    AuthRepository authRepository;
    @Inject
    BarterRepository barterRepository;
    @Inject
    CatObjectRepository catObjectRepository;
    @Inject
    CatRepository catRepository;
    @Inject
    DenyRepository denyRepository;
    @Inject
    DonationRepository donationRepository;
    @Inject
    ErrorMessageRepository errorMessageRepository;
    @Inject
    FileTrocRepository fileTrocRepository;
    @Inject
    HeaderRepository headerRepository;
    @Inject
    ListMsgRepository listMsgRepository;
    @Inject
    MessageRepository messageRepository;
    @Inject
    MsgListRepository msgListRepository;
    @Inject
    NoCatRepository noCatRepository;
    @Inject
    ObjectRCVListRepository objectRCVListRepository;
    @Inject
    ObjectRepository objectRepository;
    @Inject
    ObjectSNDListRepository objectSNDListRepository;
    @Inject
    RcvObjectListRepository rcvObjectListRepository;
    @Inject
    RequestRepository requestRepository;
    @Inject
    SndObjectListRepository sndObjectListRepository;
    @Inject
    UserTrocRepository userTrocRepository;
    @Inject
    FileService fileService;
    /* TrocBddRepository bdd; */
    Accept accept = new Accept();
    Auth auth = new Auth();
    Barter barter = new Barter();
    CatObjects catObject = new CatObjects();
    Cat cat = new Cat();
    Deny deny = new Deny();
    Donation donation = new Donation();
    ErrorMessage errorMessage = new ErrorMessage();
    FileTroc fileTroc = new FileTroc();
    Header header = new Header();
    ListMsg listMsg = new ListMsg();
    Message message = new Message();
    MsgList msgList = new MsgList();
    NoCat noCat = new NoCat();
    ObjectRCVList objectRCVList = new ObjectRCVList();
    Object object = new Object();
    ObjectSNDList objectSNDList = new ObjectSNDList();
    RcvObjectList rcvObjectList = new RcvObjectList();
    Request request = new Request();
    SndObjectList sndObjectList = new SndObjectList();
    UserTroc userTroc = new UserTroc();

    TrocBdd t = new TrocBdd();
    int idff = FILE_ALREADY_READIT;
    int idMsg = FILE_ALREADY_READIT;
    String globalFileName;
    String sDate3;

    public int lireHeader(String fileName) throws Exception {
        /*
         * First befor we start doing any thing if file received greater than 5
         * kilobytes then we rejected
         */
        globalFileName = fileName;
        File file = new File(fileName);

        String path = file.getAbsolutePath();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Pour enregistrer le Header Ahmad & Ramez DataBase
        t.setIdF(ParserXML.recupIdF(fileName));

        // pour deuxime data base, Initialization params
        fileTroc = new FileTroc();

        msgList = new MsgList();
        msgListRepository.save(msgList);
        // getting vlaue of IfF
        Long idF = Long.valueOf(ParserXML.recupIdF(fileName));

        // set Header NBr message dans le header dans la deuxieme BD de M...
        int nbrMsg = ParserXML.nbMsgReel(fileName).size();

        // set authRef inside DB de M...
        auth = new Auth();
        auth.setAuthRef(ParserXML.recupAuthRefHead(fileName));
        String sDate1 = ParserXML.recupDateHead(fileName);
        Date authDate = format.parse(sDate1);
        auth.setAuthDate(authDate);
        authRepository.save(auth);
        /** End of auth setting up to data base de M.... **/

        // set auth ref dans Ahmad And Ramez DB
        t.setAuthRef(ParserXML.recupAuthRefHead(fileName));

        // * setting up reveiver and transmeter */

        UserTroc receiver = new UserTroc();
        UserTroc transmitter = new UserTroc();

        // set Header NBr message dans le header Ahmad & Ramez Data base
        // t.setNbMsg(ParserXML.nombreMsg(fileName));
        int nbrsMsg = ParserXML.recupNbrMsg(fileName);

        if (nbrsMsg != nbrMsg) {
            errorMessage = new ErrorMessage();
            errorMessageRepository.save(errorMessage);
            message = new Message();
            message.setErrorMessage(errorMessage);
            messageRepository.save(message);
            listMsg = new ListMsg(message, msgList);
            listMsgRepository.save(listMsg);

            return ERROR_NB_MSG_UNMATCHED;
        }

        t.setNbMsg(ParserXML.nbMsgReel(fileName).size());
        t.setIdUserTrans(ParserXML.IdUserTra(fileName));
        t.setTransmitter(ParserXML.recupererTransmitter(fileName));
        t.setIdUserRec(ParserXML.IdUserRecev(fileName));
        t.setReceiver(ParserXML.recupReceiver(fileName));
        // optionel
        t.setAuthRef(ParserXML.recupAuthRefHead(fileName));
        t.setAuthDate(ParserXML.recupDateHead(fileName));
        bdd.save(t);

        /** starting inserting data to list Message (body of troc) */
        message = new Message();

        receiver.setName(ParserXML.recupReceiver(fileName));
        receiver.setLastName("There are no last name of recever :O");
        transmitter.setName(ParserXML.recupererTransmitter(fileName));
        transmitter.setLastName("There are no last name of transmitter :O");
        userTrocRepository.save(receiver);
        userTrocRepository.save(transmitter);
        header = new Header(auth, nbrMsg, receiver, transmitter);
        headerRepository.save(header);
        fileTroc.setIdf(idF);
        fileTroc.setFilePath(path);
        fileTroc.setMsgList(msgList);
        fileTroc.setHeader(header);
        fileTrocRepository.save(fileTroc);

        /** End of userTroc */
        /** fin of header */
        Long fileSize = file.length();
        if (fileSize > EXPECTED_SIZE) {
            System.err.println("**\n\n!! ERR :  File received of size = " + (file.length() / 1024l)
                    + "KB is Bigger than 5 KB\n\n**");
            errorMessage = new ErrorMessage();
            errorMessageRepository.save(errorMessage);
            message = new Message();
            message.setErrorMessage(errorMessage);
            messageRepository.save(message);
            listMsg = new ListMsg(message, msgList);
            listMsgRepository.save(listMsg);

            return ERROR_FILE_UN_EXPECTED_SIZE; // stop programing and exit
            // Eles if every things oki we continu our testing and charging database
        }

        if (ParserXML.dateMsgList(fileName) == null) {
            t.setDateMsg(null);

        } else {
            for (int i = 0; i < ParserXML.dateMsgList(fileName).size(); i += 2) {
                TrocBdd t2 = new TrocBdd();
                t2.setIdMsg(Integer.parseInt(ParserXML.dateMsgList(fileName).get(i)));
                t2.setDateMsg((String) ParserXML.dateMsgList(fileName).get(i + 1));

                t2.setIdF(t.getIdF());
                // System.err.println();
                bdd.save(t2);

                String sDate2 = ParserXML.dateMsgList(fileName).get(i + 1);
                Date dateMessaage = format.parse(sDate2);

                // we setting up a date before 3 month ago ago from current date
                LocalDate beforeThreeMonth = LocalDate.now().minusMonths(3);
                LocalDate dateTest = dateMessaage.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                if (dateTest.isBefore(beforeThreeMonth)) {
                    System.err.println("**\n\n!! ERR : Message date date '" + dateTest + "'Is befor 3 motn ago : "
                            + beforeThreeMonth + "**\n\n");
                    errorMessage = new ErrorMessage();
                    errorMessageRepository.save(errorMessage);
                    message.setErrorMessage(errorMessage);
                    messageRepository.save(message);
                    listMsg = new ListMsg(message, msgList);
                    listMsgRepository.save(listMsg);

                    return ERROR_DATE_HAS_EXPIRED;
                }
                System.err.println("\n\nDate of message : " + dateTest + " is valide\n\n");

                message = new Message();
                message.setIdMsg(Long.valueOf(ParserXML.dateMsgList(fileName).get(i)));
                message.setMessageDate(dateMessaage);
                messageRepository.save(message);
                listMsg.setMessage(message);

            }
        }
        if (ParserXML.ListDateValid(fileName) == null)
            t.setValideDuree(null);
        else {
            for (int i = 0; i < ParserXML.ListDateValid(fileName).size(); i += 2) {
                TrocBdd t2 = new TrocBdd();
                t2.setIdMsg(Integer.parseInt(ParserXML.ListDateValid(fileName).get(i)));
                t2.setValideDuree((String) ParserXML.ListDateValid(fileName).get(i + 1));
                t2.setIdF(t.getIdF());
                // System.err.println();
                bdd.save(t2);

                sDate1 = ParserXML.dateMsgList(fileName).get(i + 1);
                Date dateMessaage = format.parse(sDate1);
                // we setting up a date before 3 month ago ago from current date
                LocalDate beforeThreeMonth = LocalDate.now().minusMonths(3);
                LocalDate dateTest = dateMessaage.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if (dateTest.isBefore(beforeThreeMonth)) {
                    System.err.println("**\n\n!! ERR : Message date date '" + dateTest + "'Is befor 3 motn ago : "
                            + beforeThreeMonth + "**\n\n");
                    errorMessage = new ErrorMessage();
                    errorMessageRepository.save(errorMessage);
                    message.setErrorMessage(errorMessage);
                    messageRepository.save(message);

                    return ERROR_DATE_HAS_EXPIRED;
                }
                String valideDuree = ParserXML.ListDateValid(fileName).get(i + 1);
                message = messageRepository.findByIdMsg(Long.valueOf(ParserXML.dateMsgList(fileName).get(i)));
                message.setIdMsg(Long.valueOf(ParserXML.dateMsgList(fileName).get(i)));
                if (valideDuree.length() != 0 && valideDuree != null && !valideDuree.equals("null")) {
                    if (valideDuree.matches("-?\\d+(\\.\\d+)?")) {
                        message.setValidityDuration(Long.valueOf(valideDuree));
                        messageRepository.save(message);
                        listMsg.setMessage(message);
                        listMsg = new ListMsg(message, msgList);
                        listMsgRepository.save(listMsg);

                    } else {
                        System.err.println(" valideDuree is not a number (in hours)! we can't understand what you mean "
                                + valideDuree);

                        return ERROR_UNVALID_DURATION;
                    }

                }

            }
        }
        // optionnel
        if (ParserXML.ListAuthReq(fileName) == null)
            t.setAuthReq(null);

        else {
            for (int i = 0; i < ParserXML.ListAuthReq(fileName).size(); i += 2) {
                TrocBdd t2 = new TrocBdd();
                t2.setIdMsg(Integer.parseInt(ParserXML.ListAuthReq(fileName).get(i)));
                t2.setAuthReq((String) ParserXML.ListAuthReq(fileName).get(i + 1));

                t2.setIdF(t.getIdF());
                // System.err.println();
                bdd.save(t2);

                message = messageRepository.findByIdMsg(Long.valueOf(ParserXML.dateMsgList(fileName).get(i)));
                auth = new Auth();
                authRepository.save(auth);
                message.setAuth(auth);

                System.err.println("\n*ListAuthReq \n " + Integer.parseInt(ParserXML.ListAuthReq(fileName).get(i))
                        + "*\n" + "  \n ** \n" + (String) ParserXML.ListAuthReq(fileName).get(i + 1));

            }
        }

        if (ParserXML.ListAuthref(fileName) == null)
            t.setAuthRefMsg(null);
        else {
            for (int i = 0; i < ParserXML.ListAuthref(fileName).size(); i += 2) {
                TrocBdd t2 = new TrocBdd();
                t2.setIdMsg(Integer.parseInt(ParserXML.ListAuthref(fileName).get(i)));
                t2.setAuthRefMsg((String) ParserXML.ListAuthref(fileName).get(i + 1));
                t2.setIdF(t.getIdF());
                // System.err.println();
                bdd.save(t2);
            }
        }

        if (ParserXML.ListAuthDate(fileName) == null)
            t.setAuthDateMsg(null);
        else {
            for (int i = 0; i < ParserXML.ListAuthDate(fileName).size(); i += 2) {
                TrocBdd t2 = new TrocBdd();
                t2.setIdMsg(Integer.parseInt(ParserXML.ListAuthDate(fileName).get(i)));
                t2.setAuthDateMsg((String) ParserXML.ListAuthDate(fileName).get(i + 1));
                t2.setIdF(t.getIdF());
                // System.err.println();
                bdd.save(t2);
            }
        }

        if (ParserXML.ListAcc(fileName) == null)
            t.setIdPropMsgAcc(-1);
        else {
            for (int i = 0; i < ParserXML.ListAcc(fileName).size(); i += 2) {
                TrocBdd t2 = new TrocBdd();
                t2.setIdMsg(Integer.parseInt(ParserXML.ListAcc(fileName).get(i)));
                t2.setIdPropMsgAcc(Integer.parseInt(ParserXML.ListAcc(fileName).get(i + 1)));
                t2.setIdF(t.getIdF());
                // System.err.println();
                bdd.save(t2);
            }
        }

        if (ParserXML.ListDeny(fileName) == null)
            t.setIdPropMsgDeny(-1);
        else {
            for (int i = 0; i < ParserXML.ListDeny(fileName).size(); i += 2) {
                TrocBdd t2 = new TrocBdd();
                t2.setIdMsg(Integer.parseInt(ParserXML.ListDeny(fileName).get(i)));
                t2.setIdPropMsgDeny(Integer.parseInt(ParserXML.ListDeny(fileName).get(i + 1)));
                t2.setIdF(t.getIdF());
                bdd.save(t2);
            }
        }

        if (ParserXML.ListReasonDeny(fileName) == null)
            t.setRaisonDeny(null);
        else {
            for (int i = 0; i < ParserXML.ListReasonDeny(fileName).size(); i += 2) {
                TrocBdd t2 = new TrocBdd();
                t2.setIdMsg(Integer.parseInt(ParserXML.ListReasonDeny(fileName).get(i)));
                t2.setRaisonDeny(ParserXML.ListReasonDeny(fileName).get(i + 1));
                t2.setIdF(t.getIdF());
                bdd.save(t2);
            }
        }

        if (ParserXML.ListPrevMsg(fileName) == null)
            t.setIdPrevMsg(-1);
        else {
            for (int i = 0; i < ParserXML.ListPrevMsg(fileName).size(); i += 2) {
                TrocBdd t2 = new TrocBdd();
                t2.setIdMsg(Integer.parseInt(ParserXML.ListPrevMsg(fileName).get(i)));
                t2.setIdPrevMsg(Integer.parseInt(ParserXML.ListPrevMsg(fileName).get(i + 1)));
                t2.setIdF(t.getIdF());
                bdd.save(t2);
            }
        }

        if (ParserXML.ListCatReq(fileName) == null)
            t.setCatReq(null);
        else {
            for (int i = 0; i < ParserXML.ListCatReq(fileName).size(); i += 2) {
                TrocBdd t2 = new TrocBdd();
                t2.setIdMsg(Integer.parseInt(ParserXML.ListCatReq(fileName).get(i)));
                t2.setCatReq(ParserXML.ListCatReq(fileName).get(i + 1));
                t2.setIdF(t.getIdF());
                bdd.save(t2);
            }
        }
        if (ParserXML.ListCatReqMsgETdate(fileName) == null) {
            t.setIdCatReqMsg(-1);
            t.setCatDate(null);
        } else {
            for (int i = 0; i < ParserXML.ListCatReqMsgETdate(fileName).size(); i += 3) {
                TrocBdd t2 = new TrocBdd();
                t2.setIdMsg(Integer.parseInt(ParserXML.ListCatReqMsgETdate(fileName).get(i)));
                t2.setIdCatReqMsg(Integer.parseInt(ParserXML.ListCatReqMsgETdate(fileName).get(i + 1)));
                t2.setCatDate(ParserXML.ListCatReqMsgETdate(fileName).get(i + 2));
                t2.setIdF(t.getIdF());
                bdd.save(t2);

                sDate3 = ParserXML.ListCatReqMsgETdate(fileName).get(i + 2);

                
                System.err.println("sdate 3 = " + sDate3); Date datCat =
                format.parse(sDate3); Long idMsg2 = Long.valueOf(t2.getIdMsg());
                message.setIdMsg(idMsg2); message = messageRepository.findByIdMsg(idMsg2);
                cat = new Cat(datCat); catRepository.save(cat); message.setCat(cat);
                messageRepository.save(message);
                 
            }
        }
        if (ParserXML.ListNoCat(fileName) == null) {
            t.setIdNoCatReqMsg(-1);
            t.setRaisonNoCat(null);
        } else {
            for (int i = 0; i < ParserXML.ListNoCat(fileName).size(); i += 3) {
                TrocBdd t2 = new TrocBdd();
                t2.setIdMsg(Integer.parseInt(ParserXML.ListNoCat(fileName).get(i)));
                t2.setIdNoCatReqMsg(Integer.parseInt(ParserXML.ListNoCat(fileName).get(i + 1)));
                t2.setRaisonNoCat(ParserXML.ListNoCat(fileName).get(i + 2));
                t2.setIdF(t.getIdF());
                bdd.save(t2);
            }
        }
        if (ParserXML.ListErr(fileName) == null) {
            t.setIdNoCatReqMsg(-1);
            t.setRaisonNoCat(null);
        } else {
            for (int i = 0; i < ParserXML.ListErr(fileName).size(); i += 4) {
                TrocBdd t2 = new TrocBdd();
                t2.setIdMsg(Integer.parseInt(ParserXML.ListErr(fileName).get(i)));
                t2.setIdMsgError(Integer.parseInt(ParserXML.ListErr(fileName).get(i + 1)));
                t2.setIdError(Integer.parseInt(ParserXML.ListErr(fileName).get(i + 1)));
                t2.setMsgErr(ParserXML.ListErr(fileName).get(i + 2));
                t2.setIdF(t.getIdF());
                bdd.save(t2);
            }
        }

        // System.err.println(ParserXML.recupCatReqMsg(fileName));
        // t.setIdF(t.getIdF());
        // t.setIdMsg(ParserXML.nbMsgReel(fileName).get(i));
        // bdd.save(t);

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

                Long idMsg2 = Long.valueOf(t2.getIdMsg());
                Long idPrevMsg = Long.valueOf(t2.getIdPrevMsg());
                message = new Message();
                message.setIdMsg(idMsg2);
                message = messageRepository.findByIdMsg(idMsg2);
                accept = acceptRepository.findByIdPropositionMsg(idMsg2);
                if(accept == null){
                    accept = new Accept(idMsg2);
                }
                acceptRepository.save(accept);
                message.setAccept(accept);
                barter = new Barter();
                barter.setIdPrevMsg(idMsg2);
                barterRepository.save(barter);
                message.setBarter(barter);
                messageRepository.save(message);
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
            System.err.println("heeeerreeeee  "+ParserXML.listObjRcvReq(fileName));
            for (int i = 0; i < ParserXML.listObjRcvReq(fileName).size(); i += 5) {
                TrocBdd t2 = new TrocBdd();
                t2.setIdObjReq((String) ParserXML.listObjRcvReq(fileName).get(i));
                t2.setObjNameReqRcv((String) ParserXML.listObjRcvReq(fileName).get(i + 1));
                t2.setObjDetailsReqRcv((String) ParserXML.listObjRcvReq(fileName).get(i + 2));
                t2.setObjImageReqRcv((String) ParserXML.listObjRcvReq(fileName).get(i + 3));
                t2.setIdF(t.getIdF());
                t2.setIdMsg(Integer.parseInt(ParserXML.listObjRcvReq(fileName).get(i + 4)));
                bdd.save(t2);

                System.err.println("objet  " + ParserXML.listObjRcvReq(fileName).get(i + 1));

                Long idMsg2 = Long.valueOf(t2.getIdMsg());
                Long idPrevMsg = Long.valueOf(t2.getIdPrevMsg());
                message = new Message();
                message.setIdMsg(idMsg2);
                message = messageRepository.findByIdMsg(idMsg2);
                accept = acceptRepository.findByIdPropositionMsg(idMsg2);
                if(accept == null){
                    accept = new Accept(idMsg2);
                }
                acceptRepository.save(accept);
                message.setAccept(accept);
                request = new Request();
                request.setIdPrevMsg(idMsg2);
                requestRepository.save(request);
                message.setRequest(request);
                messageRepository.save(message);
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

                Long idMsg2 = Long.valueOf(t2.getIdMsg());
                Long idPrevMsg = Long.valueOf(t2.getIdPrevMsg());
                message = new Message();
                message.setIdMsg(idMsg2);
                message = messageRepository.findByIdMsg(idMsg2);
                accept = acceptRepository.findByIdPropositionMsg(idMsg2);
                if(accept == null){
                    accept = new Accept(idMsg2);
                }
                acceptRepository.save(accept);
                message.setAccept(accept);
                donation = new Donation();
                donation.setIdPrevMsg(idMsg2);
                donationRepository.save(donation);
                message.setDonation(donation);
                messageRepository.save(message);
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

                Date catDate = format.parse(sDate3);
                Long idMsg2 = Long.valueOf(t2.getIdMsg());
                message = new Message();
                message.setIdMsg(idMsg2);
                message = messageRepository.findByIdMsg(idMsg2);
                accept = acceptRepository.findByIdPropositionMsg(idMsg2);
                if(accept == null){
                    accept = new Accept(idMsg2);
                }
                acceptRepository.save(accept);
                message.setAccept(accept);
                System.err.println("catDate  is " + catDate);
                cat.setCatDate(catDate);
                cat = catRepository.findByCatDate(catDate);
                if(cat == null){
                    cat = new Cat(catDate);
                }
                //cat = new Cat();
                catRepository.save(cat);
                
                message.setCat(cat);
                messageRepository.save(message);

            }
        }
        listMsg = new ListMsg(message, msgList);
        listMsgRepository.save(listMsg);
        return t.getIdF();

    }

    @RequestMapping(value = "/ReceiveXML", params = "myFileXmlName", method = RequestMethod.GET)
    public String receiverPage(Model m, @RequestParam String myFileXmlName) throws Exception {

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
        HashMap<List<Integer>, Integer> hmIdErr = new HashMap<>();
        HashMap<List<String>, Integer> hmMsgErr = new HashMap<>();
        HashMap<List<Integer>, Integer> hmPrevMsg = new HashMap<>();
        HashMap<List<String>, Integer> hmCatDateETreqMsg = new HashMap<>();
        HashMap<List<String>, Integer> hmNoCat = new HashMap<>();
        HashMap<List<String>, Integer> hmErr = new HashMap<>();

        try {
            if (idff == FILE_ALREADY_READIT)
                idff = lireHeader(myFileXmlName);
            if (idff == ERROR_FILE_UN_EXPECTED_SIZE)
                m.addAttribute("catchExceptions", "File is greater than 5 kb");
            if (idff == ERROR_NB_MSG_UNMATCHED)
                m.addAttribute("catchExceptions", "Number message unmatched");
            if (idff == ERROR_DATE_HAS_EXPIRED)
                m.addAttribute("catchExceptions", "ERROR : DATE has Been Expierd!");
            if (idff == ERROR_UNVALID_DURATION)
                m.addAttribute("catchExceptions", "Pleas Check ur Duration Message, Must Be Long type");

            lb = bdd.findAllByIdF(idff);

            for (int i = 0; i < lb.size(); i++) {

                if (lb.get(i).getIdObjBartRec() != null) {
                    List<String> listeRcvBarter = new ArrayList<>();
                    listeRcvBarter.add(lb.get(i).getIdObjBartRec());
                    listeRcvBarter.add(lb.get(i).getObjBartNameRec());
                    listeRcvBarter.add(lb.get(i).getObjBartDetailsRec());
                    listeRcvBarter.add(lb.get(i).getObjBartImageRec());

                    hmRcvBart.put(listeRcvBarter, lb.get(i).getIdMsg());
                }
                if (lb.get(i).getIdPrevMsg() != -1 && lb.get(i).getIdPrevMsg() != 0) {
                    List<Integer> listeIdPrevMsg = new ArrayList<>();
                    listeIdPrevMsg.add(lb.get(i).getIdPrevMsg());
                    hmPrevMsg.put(listeIdPrevMsg, lb.get(i).getIdMsg());
                }
                if (lb.get(i).getIdObjBartSend() != null) {
                    List<String> listeSndBarter = new ArrayList<>();
                    listeSndBarter.add(lb.get(i).getIdObjBartSend());
                    listeSndBarter.add(lb.get(i).getObjBartNameSend());
                    listeSndBarter.add(lb.get(i).getObjBartDetailsSend());
                    listeSndBarter.add(lb.get(i).getObjBartImageSend());

                    hmSndBart.put(listeSndBarter, lb.get(i).getIdMsg());
                }
                if (lb.get(i).getIdObjReq() != null) {
                    List<String> listeObjReq = new ArrayList<>();
                    listeObjReq.add(lb.get(i).getIdObjReq());
                    listeObjReq.add(lb.get(i).getObjNameReqRcv());
                    listeObjReq.add(lb.get(i).getObjDetailsReqRcv());
                    listeObjReq.add(lb.get(i).getObjImageReqRcv());
                    hmObjReq.put(listeObjReq, lb.get(i).getIdMsg());
                    //System.err.println("yaaaaaaaaaaa "+hmObjReq);
                }
                if (lb.get(i).getIdObjDonnSend() != null) {
                    List<String> listeObjDonn = new ArrayList<>();
                    listeObjDonn.add(lb.get(i).getIdObjDonnSend());
                    listeObjDonn.add(lb.get(i).getObjDonnNameSend());
                    listeObjDonn.add(lb.get(i).getObjDonnDetailsSend());
                    listeObjDonn.add(lb.get(i).getObjDonnImageSend());
                    hmObjDonn.put(listeObjDonn, lb.get(i).getIdMsg());
                }
                if (lb.get(i).getIdObjCat() != null) {
                    List<String> listeObjCat = new ArrayList<>();
                    listeObjCat.add(lb.get(i).getIdObjCat());
                    listeObjCat.add(lb.get(i).getObjNameCat());
                    listeObjCat.add(lb.get(i).getObjDetailsCat());
                    listeObjCat.add(lb.get(i).getObjImageCat());
                    hmObjCat.put(listeObjCat, lb.get(i).getIdMsg());
                }
                if (lb.get(i).getDateMsg() != null) {
                    List<String> listeDateMsg = new ArrayList<>();
                    listeDateMsg.add(lb.get(i).getDateMsg());
                    hmDateMsg.put(listeDateMsg, lb.get(i).getIdMsg());
                }

                if (lb.get(i).getValideDuree() != null) {
                    List<String> listeValDu = new ArrayList<>();
                    listeValDu.add(lb.get(i).getValideDuree());
                    hmValDu.put(listeValDu, lb.get(i).getIdMsg());
                }

                if (lb.get(i).getAuthReq() != null) {
                    List<String> listeAuthReq = new ArrayList<>();
                    listeAuthReq.add(lb.get(i).getAuthReq());
                    hmAuthReq.put(listeAuthReq, lb.get(i).getIdMsg());
                }

                if (lb.get(i).getAuthRefMsg() != null) {
                    List<String> listeAuthRefMsg = new ArrayList<>();
                    listeAuthRefMsg.add(lb.get(i).getAuthRefMsg());
                    hmAuthRefMsg.put(listeAuthRefMsg, lb.get(i).getIdMsg());
                }

                if (lb.get(i).getAuthDateMsg() != null) {
                    List<String> listeAuthDatMsg = new ArrayList<>();
                    listeAuthDatMsg.add(lb.get(i).getAuthDateMsg());
                    hmAuthDatMsg.put(listeAuthDatMsg, lb.get(i).getIdMsg());
                }

                if (lb.get(i).getIdPropMsgAcc() != -1 || lb.get(i).getIdPropMsgAcc() != 0) {
                    List<Integer> listeIdAcc = new ArrayList<>();
                    listeIdAcc.add(lb.get(i).getIdPropMsgAcc());
                    hmIdAcc.put(listeIdAcc, lb.get(i).getIdMsg());
                }

                if (lb.get(i).getIdPropMsgDeny() != -1 || lb.get(i).getIdPropMsgDeny() != 0) {
                    List<Integer> listeIdDeny = new ArrayList<>();
                    listeIdDeny.add(lb.get(i).getIdPropMsgDeny());
                    hmIdDeny.put(listeIdDeny, lb.get(i).getIdMsg());
                }

                if (lb.get(i).getRaisonDeny() != null) {
                    List<String> listeReasonDeny = new ArrayList<>();
                    listeReasonDeny.add(lb.get(i).getRaisonDeny());
                    hmReasonDeny.put(listeReasonDeny, lb.get(i).getIdMsg());
                }

                if (lb.get(i).getCatReq() != null) {
                    List<String> listeCatReq = new ArrayList<>();
                    listeCatReq.add(lb.get(i).getCatReq());
                    hmCatReq.put(listeCatReq, lb.get(i).getIdMsg());
                }

                if (lb.get(i).getCatDate() != null && lb.get(i).getIdCatReqMsg() != -1
                        && lb.get(i).getIdCatReqMsg() != 0) {
                    List<String> listeCat = new ArrayList<>();
                    listeCat.add(Integer.toString(lb.get(i).getIdCatReqMsg()));
                    listeCat.add(lb.get(i).getCatDate());
                    hmCatDateETreqMsg.put(listeCat, lb.get(i).getIdMsg());
                }

                if (lb.get(i).getCatDate() != null && lb.get(i).getIdNoCatReqMsg() != -1
                        && lb.get(i).getIdNoCatReqMsg() != 0) {
                    List<String> listeNoCat = new ArrayList<>();
                    listeNoCat.add(Integer.toString(lb.get(i).getIdNoCatReqMsg()));
                    listeNoCat.add(lb.get(i).getRaisonNoCat());
                    hmNoCat.put(listeNoCat, lb.get(i).getIdMsg());
                }

                if (lb.get(i).getMsgErr() != null && lb.get(i).getIdMsgError() != -1 && lb.get(i).getIdMsgError() != 0
                        && lb.get(i).getIdError() != -1 && lb.get(i).getIdError() != 0) {
                    List<String> listeErr = new ArrayList<>();
                    listeErr.add(Integer.toString(lb.get(i).getIdMsgError()));
                    listeErr.add(Integer.toString(lb.get(i).getIdError()));
                    listeErr.add(lb.get(i).getMsgErr());
                    hmErr.put(listeErr, lb.get(i).getIdMsg());
                }

            }
            if (hmRcvBart.size() != 0)
                m.addAttribute("listeRcvBarter", hmRcvBart);
            if (hmSndBart.size() != 0)
                m.addAttribute("listeSndBarter", hmSndBart);
            if (hmRcvBart.size() != 0)
                m.addAttribute("listeRcvReq", hmObjReq);
            if (hmRcvBart.size() != 0)
                m.addAttribute("listeObjDonn", hmObjDonn);
            if (hmPrevMsg.size() != 0)
                m.addAttribute("idPrevMsg", hmPrevMsg);
            if (hmCatDateETreqMsg.size() != 0)
                m.addAttribute("restCat", hmCatDateETreqMsg);
            if (hmErr.size() != 0)
                m.addAttribute("err", hmErr);
            if (hmObjCat.size() != 0) {
                // m.addAttribute("idCatReqMsg", lb.get(0).getIdCatReqMsg());
                // m.addAttribute("catDate", lb.get(0).getCatDate());
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

            if (hmAuthDatMsg.size() != 0) {
                m.addAttribute("listeAuthDatMsg", hmAuthDatMsg);
            }
            if (hmAuthDatMsg.size() != 0) {
                m.addAttribute("listeAuthDatMsg", hmAuthDatMsg);
            }
            if (hmIdAcc.size() != 0) {
                m.addAttribute("listeIdAcc", hmIdAcc);
            }
            if (hmIdDeny.size() != 0) {
                m.addAttribute("listeIdDeny", hmIdDeny);
            }
            if (hmReasonDeny.size() != 0) {
                m.addAttribute("listeReasonDeny", hmReasonDeny);
            }
            if (hmCatReq.size() != 0) {
                m.addAttribute("listeCatReq", hmCatReq);
            }
            if (hmIdNoCat.size() != 0) {
                m.addAttribute("listeIdNoCat", hmIdNoCat);
            }
            if (hmReasonNoCat.size() != 0) {
                m.addAttribute("listeReasonNoCat", hmReasonNoCat);
            }
            if (hmIdErr.size() != 0) {
                m.addAttribute("listeIdErr", hmIdErr);
            }
            if (hmIdErr.size() != 0) {
                m.addAttribute("listeIdErr", hmIdErr);
            }
            if (hmMsgErr.size() != 0) {
                m.addAttribute("listeMsgErr", hmMsgErr);
            }
            /**************** Objets obligatoires a inserer *************/
            m.addAttribute("idf", idff);
            m.addAttribute("nbMsg", lb.get(0).getNbMsg());
            m.addAttribute("idUserTrans", lb.get(0).getIdUserTrans());
            m.addAttribute("transmitter", lb.get(0).getTransmitter());
            m.addAttribute("idUserRec", lb.get(0).getIdUserRec());
            m.addAttribute("receiver", lb.get(0).getReceiver());
            m.addAttribute("idMsg", lb.get(0).getIdMsg());

            /**************** Objets optionnelles *************/
            m.addAttribute("authRef", lb.get(0).getAuthRef());
            m.addAttribute("authDate", lb.get(0).getAuthDate());

            m.addAttribute("listeNbrsMsg", ParserXML.nbMsgReel(myFileXmlName));

        } catch (Exception e) {
            System.err.println("Error in xml file \n" + e);
            m.addAttribute("catchException", " Syntaxe ERROR in xml file \n code: \n" + e);
            return "/uploadFile";

        }

        return "/ReceiveXML";
    }

    /** Methode answer message */
    @RequestMapping(value = "/accpetMessage", method = RequestMethod.POST)
    public String acceptMessage(@RequestParam String answerQ, @RequestParam String answerMessage,
            @RequestParam int idMsg) {
        if (answerQ.equalsIgnoreCase("yes")) {

            System.err.println(" message Accepted : " + answerQ);
        } else {

            System.err.println(" message Not Accepted : " + answerQ);
            System.err.println("raison " + answerMessage);

            deny = new Deny();
            if (idff != FILE_ALREADY_READIT) {
                message = messageRepository.findByIdMsg(Long.valueOf(idMsg));
                if (message != null) {
                    System.err.println("message is not null  idMsg = " + idMsg);
                    deny.setIdPropositionMsg(idMsg);
                    if (answerMessage.length() == 0) {
                        answerMessage = "User give No Raison ...! ";
                        deny.setReason(answerMessage);
                        denyRepository.save(deny);
                    } else {
                        deny.setReason(answerMessage);
                        denyRepository.save(deny);
                    }
                    message.setAccept(null);
                    accept = acceptRepository.findByIdPropositionMsg((long) idMsg);
                    
                    message.setDeny(deny);
                    messageRepository.save(message);
                    acceptRepository.delete(accept);
                    listMsg = new ListMsg(message, msgList);
                    listMsgRepository.save(listMsg);
                } else {
                    message = new Message();
                    System.err.println("message is null ");
                }
            }
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/uploadFile")
    public String uploadFile(Model m) {
        return "uploadFile";
    }

    @RequestMapping(value = "/uploadXML", method = RequestMethod.POST)
    public String uploadXmlFile(@RequestParam("chooseFile") MultipartFile chooseFile) {

        String fileName = StringUtils.cleanPath(chooseFile.getOriginalFilename());
        /** Pour Windows Utilisateur */
        //String myPath = "\\src\\main\\resources\\dossierXML\\";
        /** Pour linux Utilisateur */
        String myPath = "/src/main/resources/dossierXML/";

        System.err.println("directory is : " + System.getProperty("user.dir") + myPath + "\n");
        String uploadDir = System.getProperty("user.dir") + myPath;

        try {
            Path copyLocation = Paths.get(uploadDir + File.separator + fileName);
            Files.copy(chooseFile.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!");
        }
        globFileName = fileName;
        
        System.err.println("file uplad named " + fileName);
        return "redirect:/ReceiveXML?myFileXmlName=" + fileName;

    }

}
