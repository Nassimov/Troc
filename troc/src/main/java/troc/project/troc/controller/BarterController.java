package troc.project.troc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import troc.project.troc.model.Barter;
import troc.project.troc.repositories.BarterRepository;
import troc.project.troc.repositories.RcvObjectListRepository;
import troc.project.troc.repositories.SndObjectListRepository;

@Controller
public class BarterController {

    @Autowired
    BarterRepository barterRepository;
    @Autowired
    RcvObjectListRepository rcvObjectListRepository;
    @Autowired
    SndObjectListRepository sndObjectListRepository;

    @RequestMapping(value = "/addBarter", method = RequestMethod.POST)
    public String addBarter(@RequestParam Long idPrevMsg, @RequestParam Long rcvObjList, @RequestParam Long sndObjList,
            Model m) {

        barterRepository.save(new Barter(idPrevMsg, rcvObjectListRepository.findById(rcvObjList).get(),
                sndObjectListRepository.findById(sndObjList).get()));
        return "redirect:/";
    }

}