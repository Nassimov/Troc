package troc.project.troc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import troc.project.troc.model.MsgList;
import troc.project.troc.repositories.MsgListRepository;

@Controller
public class MsgListController {

    @Autowired
    MsgListRepository msgListRepository;

    @RequestMapping(value = "/addListOfMessages", method = RequestMethod.POST)
    public String addListOfMessages() {
        msgListRepository.save(new MsgList());
        return "redirect:/nextStep";
    }
}
