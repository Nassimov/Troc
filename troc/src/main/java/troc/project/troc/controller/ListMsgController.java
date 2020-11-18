package troc.project.troc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import troc.project.troc.model.ListMsg;
import troc.project.troc.repositories.ListMsgRepository;
import troc.project.troc.repositories.MessageRepository;
import troc.project.troc.repositories.MsgListRepository;

@Controller
public class ListMsgController {

    @Autowired
    ListMsgRepository listMsgRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    MsgListRepository msgListRepository;

    @RequestMapping(value = "/associateMsgList", method = RequestMethod.POST)
    public String associateMsgList(@RequestParam Long msg, @RequestParam Long msgList, Model m) {
        listMsgRepository
                .save(new ListMsg(messageRepository.findById(msg).get(), msgListRepository.findById(msgList).get()));
        return "redirect:/nextStep";
    }

}