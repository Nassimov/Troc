package troc.project.troc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import troc.project.troc.model.NoCat;
import troc.project.troc.repositories.NoCatRepository;

@Controller
public class NoCatController {

    @Autowired
    NoCatRepository noCatRepository;

    @RequestMapping(value = "/addNoCat", method = RequestMethod.POST)
    public String addNoCat(@RequestParam String reason, @RequestParam Long idCatRequestMsg, Model m) {
        noCatRepository.save(new NoCat(idCatRequestMsg, reason));
        return "redirect:/";
    }
}
