package troc.project.troc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import troc.project.troc.model.NoCat;
import troc.project.troc.model.Object;
import troc.project.troc.model.ObjectSNDList;
import troc.project.troc.model.SndObjectList;
import troc.project.troc.repositories.NoCatRepository;
import troc.project.troc.repositories.ObjectRepository;
import troc.project.troc.repositories.ObjectSNDListRepository;
import troc.project.troc.repositories.SndObjectListRepository;

@Controller
public class ObjectSNDListController {

    @Autowired
    ObjectSNDListRepository objectSNDListRepository;
    @Autowired
    ObjectRepository objectRepository;
    @Autowired
    SndObjectListRepository sndObjectListRepository;

    @RequestMapping(value = "/addObjectSNDList", method = RequestMethod.POST)
    public String addObjectSNDList(@RequestParam Long obj, @RequestParam Long sndObjList, Model m) {
        SndObjectList sndsnd = sndObjectListRepository.findById(sndObjList).get();
        Object objobj = objectRepository.findById(obj).get();
        objectSNDListRepository.save(new ObjectSNDList(objobj, sndsnd));
        return "redirect:/";
    }
}
