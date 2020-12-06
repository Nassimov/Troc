package troc.project.troc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import troc.project.troc.model.Deny;
import troc.project.troc.repositories.DenyRepository;

@Controller
public class DenyController {

    @Autowired
    DenyRepository denyRepositories;

    @RequestMapping(value = "/addDenyResponse", method = RequestMethod.POST)
    public String addDenyResponse(@RequestParam Integer idPropositionMsg, @RequestParam String reason, Model m) {

        denyRepositories.save(new Deny(idPropositionMsg, reason));
        return "redirect:/firstStep";
    }

}