package troc.project.troc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import troc.project.troc.model.UserTroc;
import troc.project.troc.repositories.UserTrocRepository;

@Controller
public class UserController {

    @Autowired
    UserTrocRepository userRepositories;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@RequestParam String name, @RequestParam String lastName, Model m) {

        UserTroc user = new UserTroc(name, lastName);
        userRepositories.save(user);
        return "redirect:/";
    }

}