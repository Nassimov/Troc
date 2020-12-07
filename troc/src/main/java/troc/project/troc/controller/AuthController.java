package troc.project.troc.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import troc.project.troc.model.Auth;

import troc.project.troc.repositories.AuthRepository;

@Controller
public class AuthController {

    @Autowired
    AuthRepository authRepositories;

    @RequestMapping(value = "/addAuth", method = RequestMethod.POST)
    public String addAuth(@RequestParam String authDate, @RequestParam String authTime, @RequestParam String authRef,
            Model m) {

        int year = Integer.parseInt(authDate.split("-")[0]);
        int goodYear = year - 1900;
        int month = Integer.parseInt(authDate.split("-")[1]);
        int date = Integer.parseInt(authDate.split("-")[2]);
        int h1 = Integer.parseInt(authTime.split(":")[0]);
        int m1 = Integer.parseInt(authTime.split(":")[1]);
        Date newDate = new Date(goodYear, month, date, h1, m1);
        authRepositories.save(new Auth(newDate, authRef));
        return "redirect:/firstStep";
    }

}