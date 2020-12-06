package troc.project.troc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import troc.project.troc.model.Cat;
import troc.project.troc.model.CatObjects;
import troc.project.troc.model.Object;
import troc.project.troc.repositories.CatObjectRepository;
import troc.project.troc.repositories.CatRepository;
import troc.project.troc.repositories.ObjectRepository;

@Controller
public class CatObjectController {

    @Autowired
    CatObjectRepository catObjectRepositories;
    @Autowired
    ObjectRepository objectRepositories;
    @Autowired
    CatRepository catRepositories;

    @RequestMapping(value = "/catObject", method = RequestMethod.POST)
    public String catObject(@RequestParam Long obj, @RequestParam Long cat, Model m) {
        System.out.println("Nassim");
        System.out.println(obj);
        System.out.println(cat);
        Object objectInstance = objectRepositories.findById(obj).get();
        Cat catInstance = catRepositories.findById(cat).get();
        CatObjects catObject = new CatObjects(objectInstance, catInstance);
        catObjectRepositories.save(catObject);
        return "redirect:/firstStep";
    }

}