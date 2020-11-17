package troc.project.troc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import troc.project.troc.model.Cat;
import troc.project.troc.model.Object;
import troc.project.troc.repositories.CatRepository;
import troc.project.troc.repositories.ObjectRepository;

@Controller
public class MainPage {
    @Autowired
    CatRepository catRepositories;
    @Autowired
    ObjectRepository objectRepositories;

    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String accueil(Model m) {
        List<Cat> catList = (List<Cat>) catRepositories.findAll();
        List<Object> objList = (List<Object>) objectRepositories.findAll();
        System.out.println(catList);
        System.out.println(objList);
        m.addAttribute("catList", catList);
        m.addAttribute("objList", objList);
        return "index";
    }

}