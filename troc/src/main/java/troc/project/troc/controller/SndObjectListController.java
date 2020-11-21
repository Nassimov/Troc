package troc.project.troc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import troc.project.troc.model.SndObjectList;
import troc.project.troc.repositories.SndObjectListRepository;

@Controller
public class SndObjectListController {

    @Autowired
    SndObjectListRepository sndObjectListRepository;

    @RequestMapping(value = "/addSndObjectList", method = RequestMethod.POST)
    public String addObject() {

        sndObjectListRepository.save(new SndObjectList());

        return "redirect:/";
    }
}
