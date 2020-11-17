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

import troc.project.troc.model.Object;
import troc.project.troc.repositories.ObjectRepository;

@Controller
public class ObjectController {

    @Autowired
    FileService fileService;

    @Autowired
    ObjectRepository objectRep;

    @RequestMapping(value = "/addObject", method = RequestMethod.POST)
    public String addObject(@RequestParam String objectName, @RequestParam String objectDetail,
            @RequestParam("objectImage") MultipartFile objectImage, Model m) throws IOException {

        String fileName = StringUtils.cleanPath(objectImage.getOriginalFilename());

        fileService.uploadFile(objectImage);

        objectRep.save(new Object(objectName, objectDetail, "\\sndFiles\\" + fileName));
        return "redirect:/";
    }
}
