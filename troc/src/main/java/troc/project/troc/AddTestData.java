package troc.project.troc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import troc.project.troc.model.Accept;
import troc.project.troc.model.Auth;
import troc.project.troc.model.Barter;
import troc.project.troc.model.Cat;
import troc.project.troc.model.CatObjects;
import troc.project.troc.model.Deny;
import troc.project.troc.model.Donation;
import troc.project.troc.model.ErrorMessage;
import troc.project.troc.model.Message;
import troc.project.troc.model.MsgList;
import troc.project.troc.model.NoCat;
import troc.project.troc.model.Object;
import troc.project.troc.model.ObjectRCVList;
import troc.project.troc.model.ObjectSNDList;
import troc.project.troc.model.RcvObjectList;
import troc.project.troc.model.Request;
import troc.project.troc.model.SndObjectList;
import troc.project.troc.model.UserTroc;
import troc.project.troc.repositories.AcceptRepository;
import troc.project.troc.repositories.AuthRepository;
import troc.project.troc.repositories.BarterRepository;
import troc.project.troc.repositories.CatObjectRepository;
import troc.project.troc.repositories.CatRepository;
import troc.project.troc.repositories.DenyRepository;
import troc.project.troc.repositories.DonationRepository;
import troc.project.troc.repositories.ErrorMessageRepository;
import troc.project.troc.repositories.MessageRepository;
import troc.project.troc.repositories.MsgListRepository;
import troc.project.troc.repositories.NoCatRepository;
import troc.project.troc.repositories.ObjectRCVListRepository;
import troc.project.troc.repositories.ObjectRepository;
import troc.project.troc.repositories.ObjectSNDListRepository;
import troc.project.troc.repositories.RcvObjectListRepository;
import troc.project.troc.repositories.RequestRepository;
import troc.project.troc.repositories.SndObjectListRepository;
import troc.project.troc.repositories.UserTrocRepository;

@Service
public class AddTestData {
    @Autowired
    private AcceptRepository acceptRepo;
    @Autowired
    private AuthRepository authRepo;
    @Autowired
    private RcvObjectListRepository rcvObjListRepo;
    @Autowired
    private UserTrocRepository userTrocRepo;
    @Autowired
    private SndObjectListRepository sndObjRepo;
    @Autowired
    private RequestRepository requestRepo;
    @Autowired
    private ObjectRepository objRepo;
    @Autowired
    private ObjectSNDListRepository objectSNDListRepository;
    @Autowired
    private ObjectRCVListRepository objectRCVListRepository;
    @Autowired
    private NoCatRepository noCatRepository;
    @Autowired
    private MsgListRepository msgListRepository;
    @Autowired
    private ErrorMessageRepository errorMessageRepository;
    @Autowired
    private BarterRepository barterRepository;
    @Autowired
    private CatRepository catRepository;
    @Autowired
    private CatObjectRepository catObjectRepository;
    @Autowired
    private DenyRepository denyRepository;
    @Autowired
    private DonationRepository donationRepository;
    @Autowired
    private MessageRepository messageRepository;

    interface RandomElement {
        <T> T gen(List<T> l);
    }

    public void generateTestData() {
        if ((int) messageRepository.count() != 0) {
            return;
        }
        Random r = new Random();
        RandomElement re = new RandomElement() {
            @Override
            public <T> T gen(List<T> l) {
                return l.get(r.nextInt(l.size()));
            }
        };

        List<Accept> acceptList = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            acceptList.add(acceptRepo.save(new Accept(r.nextInt(40) + 1)));
        }

        List<Auth> authList = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            Calendar cal = Calendar.getInstance();
            cal.set(2020, r.nextInt(11) + 1, r.nextInt(30), r.nextInt(23), r.nextInt(59) + 1, r.nextInt(59));
            Date authD = cal.getTime();
            authList.add(authRepo.save(new Auth(authD, "authRef" + i + 1)));
        }

        List<RcvObjectList> rcvObjList = new ArrayList<>();

        for (int i = 1; i < 10; i++) {

            rcvObjList.add(rcvObjListRepo.save(new RcvObjectList()));
        }

        List<UserTroc> userTrocList = new ArrayList<>();

        for (int i = 1; i < 7; i++) {

            userTrocList.add(userTrocRepo.save(new UserTroc("Projet", i + "")));
        }

        List<SndObjectList> sndObjList = new ArrayList<>();

        for (int i = 1; i < 10; i++) {

            sndObjList.add(sndObjRepo.save(new SndObjectList()));
        }

        List<Request> requestList = new ArrayList<>();

        for (int i = 1; i < 10; i++) {

            requestList.add(requestRepo.save(new Request(r.nextLong(), re.gen(rcvObjList))));
        }

        List<Object> objList = new ArrayList<>();

        for (int i = 1; i < 10; i++) {

            objList.add(objRepo.save(new Object("Object_" + i, "Obejct_Detail_" + i, "Object_Image_" + i)));
        }

        List<ObjectRCVList> objrcvList = new ArrayList<>();

        for (int i = 1; i < 10; i++) {

            objrcvList.add(objectRCVListRepository.save(new ObjectRCVList(re.gen(objList), re.gen(rcvObjList))));
        }

        List<ObjectSNDList> objsndList = new ArrayList<>();

        for (int i = 1; i < 10; i++) {

            objsndList.add(objectSNDListRepository.save(new ObjectSNDList(re.gen(objList), re.gen(sndObjList))));
        }

        List<NoCat> noCatList = new ArrayList<>();

        for (int i = 1; i < 10; i++) {

            noCatList.add(noCatRepository.save(new NoCat(r.nextLong(), "Reason" + i)));
        }
        List<MsgList> msgList = new ArrayList<>();

        for (int i = 1; i < 10; i++) {

            msgList.add(msgListRepository.save(new MsgList()));
        }

        List<ErrorMessage> errorMsgList = new ArrayList<>();

        for (int i = 1; i < 10; i++) {

            errorMsgList.add(errorMessageRepository.save(new ErrorMessage()));
        }
        List<Barter> barterList = new ArrayList<>();

        for (int i = 1; i < 10; i++) {

            barterList.add(barterRepository.save(new Barter(r.nextLong(), re.gen(rcvObjList), re.gen(sndObjList))));
        }
        List<Cat> catList = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            Calendar cal2 = Calendar.getInstance();
            cal2.set(2020, r.nextInt(11) + 1, r.nextInt(30), r.nextInt(23), r.nextInt(59) + 1, r.nextInt(59));
            Date catDate = cal2.getTime();
            catList.add(catRepository.save(new Cat(catDate)));
        }
        List<CatObjects> catobjList = new ArrayList<>();

        for (int i = 1; i < 10; i++) {

            catobjList.add(catObjectRepository.save(new CatObjects(re.gen(objList), re.gen(catList))));
        }
        List<Deny> denyList = new ArrayList<>();

        for (int i = 1; i < 10; i++) {

            denyList.add(denyRepository.save(new Deny(r.nextInt(12) + 1, "Reason_Deny_" + i)));
        }
        List<Donation> donationList = new ArrayList<>();

        for (int i = 1; i < 10; i++) {

            donationList.add(donationRepository.save(new Donation(r.nextLong(), re.gen(sndObjList))));
        }

        List<Message> messageList = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.set(2020, r.nextInt(11) + 1, r.nextInt(30), r.nextInt(23), r.nextInt(59) + 1, r.nextInt(59));
        Date msgDate = cal.getTime();

        messageList.add(messageRepository.save(new Message(re.gen(acceptList), null, null, re.gen(barterList),
                re.gen(catList), r.nextLong(), re.gen(denyList), null, null, null, null, r.nextLong(), msgDate)));
        cal.set(2020, r.nextInt(11) + 1, r.nextInt(30), r.nextInt(23), r.nextInt(59) + 1, r.nextInt(59));
        Date msgDate1 = cal.getTime();
        messageList.add(messageRepository.save(new Message(null, re.gen(authList), r.nextLong(), re.gen(barterList),
                re.gen(catList), r.nextLong(), re.gen(denyList), re.gen(donationList), re.gen(errorMsgList), null, null,
                r.nextLong(), msgDate1)));
        cal.set(2020, r.nextInt(11) + 1, r.nextInt(30), r.nextInt(23), r.nextInt(59) + 1, r.nextInt(59));
        Date msgDate3 = cal.getTime();
        messageList.add(messageRepository.save(new Message(re.gen(acceptList), re.gen(authList), r.nextLong(),
                re.gen(barterList), re.gen(catList), r.nextLong(), re.gen(denyList), null, null, re.gen(noCatList),
                re.gen(requestList), r.nextLong(), msgDate3)));
        cal.set(2020, r.nextInt(11) + 1, r.nextInt(30), r.nextInt(23), r.nextInt(59) + 1, r.nextInt(59));
        Date msgDate4 = cal.getTime();
        messageList.add(messageRepository.save(new Message(null, re.gen(authList), r.nextLong(), re.gen(barterList),
                re.gen(catList), r.nextLong(), re.gen(denyList), null, re.gen(errorMsgList), re.gen(noCatList), null,
                r.nextLong(), msgDate4)));
        cal.set(2020, r.nextInt(11) + 1, r.nextInt(30), r.nextInt(23), r.nextInt(59) + 1, r.nextInt(59));
        Date msgDate5 = cal.getTime();
        messageList.add(messageRepository.save(new Message(re.gen(acceptList), null, r.nextLong(), null,
                re.gen(catList), r.nextLong(), re.gen(denyList), null, re.gen(errorMsgList), re.gen(noCatList),
                re.gen(requestList), r.nextLong(), msgDate5)));

    }

}