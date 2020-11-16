package troc.project.troc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainPage {

    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String accueil(Model m) {

        return "index";
    }

}