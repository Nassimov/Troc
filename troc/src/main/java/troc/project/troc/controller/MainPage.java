package troc.project.troc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import troc.project.troc.ParserXML;
import troc.project.troc.model.Accept;
import troc.project.troc.model.Auth;
import troc.project.troc.model.Barter;
import troc.project.troc.model.Cat;
import troc.project.troc.model.Deny;
import troc.project.troc.model.Donation;
import troc.project.troc.model.ErrorMessage;
import troc.project.troc.model.NoCat;
import troc.project.troc.model.Object;
import troc.project.troc.model.RcvObjectList;
import troc.project.troc.model.Request;
import troc.project.troc.model.SndObjectList;
import troc.project.troc.repositories.AcceptRepository;
import troc.project.troc.repositories.AuthRepository;
import troc.project.troc.repositories.BarterRepository;
import troc.project.troc.repositories.CatRepository;
import troc.project.troc.repositories.DenyRepository;
import troc.project.troc.repositories.DonationRepository;
import troc.project.troc.repositories.ErrorMessageRepository;
import troc.project.troc.repositories.NoCatRepository;
import troc.project.troc.repositories.ObjectRepository;
import troc.project.troc.repositories.RcvObjectListRepository;
import troc.project.troc.repositories.RequestRepository;
import troc.project.troc.repositories.SndObjectListRepository;
import java.text.ParseException;

@Controller
public class MainPage {
    @Autowired
    CatRepository catRepositories;
    @Autowired
    ObjectRepository objectRepositories;
    @Autowired
    SndObjectListRepository sndObjectListRepository;
    @Autowired
    RcvObjectListRepository rcvObjectListRepository;
    @Autowired
    AcceptRepository acceptRepository;
    @Autowired
    AuthRepository authRepository;
    @Autowired
    BarterRepository barterRepository;
    @Autowired
    DenyRepository denyRepository;
    @Autowired
    DonationRepository donationRepository;
    @Autowired
    ErrorMessageRepository errorMessageRepository;
    @Autowired
    NoCatRepository noCatRepository;
    @Autowired
    RequestRepository requestRepository;

    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String accueil(Model m) {
        List<Cat> catList = (List<Cat>) catRepositories.findAll();
        List<Object> objList = (List<Object>) objectRepositories.findAll();
        List<SndObjectList> sndObjectList = (List<SndObjectList>) sndObjectListRepository.findAll();
        List<RcvObjectList> rcvObjectList = (List<RcvObjectList>) rcvObjectListRepository.findAll();

        List<Request> requestList = (List<Request>) requestRepository.findAll();
        List<NoCat> notCatList = (List<NoCat>) noCatRepository.findAll();
        List<ErrorMessage> errorMessageList = (List<ErrorMessage>) errorMessageRepository.findAll();
        List<Donation> donationList = (List<Donation>) donationRepository.findAll();

        List<Deny> denyList = (List<Deny>) denyRepository.findAll();
        List<Barter> barterList = (List<Barter>) barterRepository.findAll();
        List<Auth> authList = (List<Auth>) authRepository.findAll();
        List<Accept> acceptList = (List<Accept>) acceptRepository.findAll();

        m.addAttribute("catList", catList);
        m.addAttribute("objList", objList);
        m.addAttribute("sndObjectList", sndObjectList);
        m.addAttribute("rcvObjectList", rcvObjectList);
        m.addAttribute("acceptList", acceptList);
        m.addAttribute("authList", authList);
        m.addAttribute("barterList", barterList);
        m.addAttribute("denyList", denyList);
        m.addAttribute("donationList", donationList);
        m.addAttribute("errorMessageList", errorMessageList);
        m.addAttribute("notCatList", notCatList);
        m.addAttribute("requestList", requestList);

        return "index";
    }

}