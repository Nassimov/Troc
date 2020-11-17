package troc.project.troc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import troc.project.troc.model.RcvObjectList;
import troc.project.troc.model.Request;
import troc.project.troc.repositories.RcvObjectListRepository;
import troc.project.troc.repositories.RequestRepository;

@Controller
public class RequestController {

    @Autowired
    RequestRepository requestRepository;
    @Autowired
    RcvObjectListRepository rcvObjectListRepository;

    @RequestMapping(value = "/addRequest", method = RequestMethod.POST)
    public String addRequest(@RequestParam Long idPrevMsg, @RequestParam Long rcvObjList, Model m) {
        RcvObjectList rcvrcv = rcvObjectListRepository.findById(rcvObjList).get();
        requestRepository.save(new Request(idPrevMsg, rcvrcv));
        return "redirect:/";
    }
}
