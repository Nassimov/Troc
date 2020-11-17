package troc.project.troc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import troc.project.troc.model.Accept;
import troc.project.troc.repositories.AcceptRepository;

@Controller
public class AcceptController {

    @Autowired
    AcceptRepository acceptRepository;

    @RequestMapping(value = "/addAccept", method = RequestMethod.POST)
    public String addAccept(@RequestParam Long idPropositionMsg, Model m) {
        acceptRepository.save(new Accept(idPropositionMsg));
        return "redirect:/";
    }

}