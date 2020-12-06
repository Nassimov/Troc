package troc.project.troc.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import troc.project.troc.model.Cat;

import troc.project.troc.repositories.CatRepository;

@Controller
public class CatController {

    @Autowired
    CatRepository catRepositories;

    @RequestMapping(value = "/addCat", method = RequestMethod.POST)
    public String addCat(@RequestParam String catDate, @RequestParam String time, Model m) {

        int year = Integer.parseInt(catDate.split("-")[0]);
        int goodYear = year - 1900;
        int month = Integer.parseInt(catDate.split("-")[1]);
        int date = Integer.parseInt(catDate.split("-")[2]);
        int h1 = Integer.parseInt(time.split(":")[0]);
        int m1 = Integer.parseInt(time.split(":")[1]);
        Date newDate = new Date(goodYear, month, date, h1, m1);
        catRepositories.save(new Cat(newDate));
        return "redirect:/firstStep";
    }

}