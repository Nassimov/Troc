package troc.project.troc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import troc.project.troc.model.Object;
import troc.project.troc.model.ObjectRCVList;
import troc.project.troc.model.RcvObjectList;
import troc.project.troc.repositories.ObjectRCVListRepository;
import troc.project.troc.repositories.ObjectRepository;
import troc.project.troc.repositories.RcvObjectListRepository;

@Controller
public class ObjectRCVListController {

    @Autowired
    ObjectRCVListRepository objectRCVListRepository;
    @Autowired
    ObjectRepository objectRepository;
    @Autowired
    RcvObjectListRepository rcvObjectListRepository;

    @RequestMapping(value = "/addObjectRCVList", method = RequestMethod.POST)
    public String addObjectSNDList(@RequestParam Long obj, @RequestParam Long rcvObjList, Model m) {

        RcvObjectList rcvsnd = rcvObjectListRepository.findById(rcvObjList).get();
        Object objobj = objectRepository.findById(obj).get();
        objectRCVListRepository.save(new ObjectRCVList(objobj, rcvsnd));

        return "redirect:/firstStep";
    }
}
