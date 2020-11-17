package troc.project.troc.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import troc.project.troc.model.RcvObjectList;
import troc.project.troc.repositories.RcvObjectListRepository;

@Controller
public class RcvObjectListController {

    @Autowired
    RcvObjectListRepository rcvObjectListRepository;

    @RequestMapping(value = "/addRcvObjectList", method = RequestMethod.POST)
    public String addObject() {

        rcvObjectListRepository.save(new RcvObjectList());

        return "redirect:/";
    }
}
