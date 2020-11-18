package troc.project.troc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import troc.project.troc.model.FileTroc;
import troc.project.troc.model.Header;
import troc.project.troc.repositories.FileTrocRepository;
import troc.project.troc.repositories.HeaderRepository;
import troc.project.troc.repositories.ListMsgRepository;
import troc.project.troc.repositories.MsgListRepository;

@Controller
public class FileTrocController {

    @Autowired
    HeaderRepository headerRepository;
    @Autowired
    MsgListRepository msgListRepository;
    @Autowired
    FileTrocRepository fileTrocRepository;
    @Autowired
    ListMsgRepository listMsgRepository;

    @RequestMapping(value = "/addFile", method = RequestMethod.POST)
    public String addHeader(@RequestParam Long header, @RequestParam Long msgList) {
        fileTrocRepository
                .save(new FileTroc(headerRepository.findById(header).get(), msgListRepository.findById(msgList).get()));
        Integer numberMessage = listMsgRepository.findAllByMsgList(msgListRepository.findById(msgList).get()).size();
        Header currentHeader = headerRepository.findById(header).get();
        currentHeader.setNbrMsg(numberMessage);
        headerRepository.save(currentHeader);
        return "redirect:/generateXML";
    }

}