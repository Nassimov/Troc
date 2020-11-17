package troc.project.troc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import troc.project.troc.model.Cat;
import troc.project.troc.model.Object;
import troc.project.troc.model.RcvObjectList;
import troc.project.troc.model.SndObjectList;
import troc.project.troc.repositories.CatRepository;
import troc.project.troc.repositories.ObjectRepository;
import troc.project.troc.repositories.RcvObjectListRepository;
import troc.project.troc.repositories.SndObjectListRepository;

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

    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String accueil(Model m) {
        List<Cat> catList = (List<Cat>) catRepositories.findAll();
        List<Object> objList = (List<Object>) objectRepositories.findAll();
        List<SndObjectList> sndObjectList = (List<SndObjectList>) sndObjectListRepository.findAll();
        List<RcvObjectList> rcvObjectList = (List<RcvObjectList>) rcvObjectListRepository.findAll();

        m.addAttribute("catList", catList);
        m.addAttribute("objList", objList);
        m.addAttribute("sndObjectList", sndObjectList);
        m.addAttribute("rcvObjectList", rcvObjectList);
        return "index";
    }

}