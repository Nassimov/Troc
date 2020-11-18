package troc.project.troc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import troc.project.troc.model.Header;
import troc.project.troc.repositories.AuthRepository;
import troc.project.troc.repositories.HeaderRepository;
import troc.project.troc.repositories.UserTrocRepository;

@Controller
public class HeaderController {

    @Autowired
    HeaderRepository headerRepository;
    @Autowired
    UserTrocRepository userTrocRepository;
    @Autowired
    AuthRepository authRepository;

    @RequestMapping(value = "/addHeader", method = RequestMethod.POST)
    public String addHeader(@RequestParam Long auth, @RequestParam Integer receiver, @RequestParam Integer transmitter,
            Model m) {
        headerRepository.save(
                new Header(authRepository.findById(auth).isPresent() ? authRepository.findById(auth).get() : null, null,
                        userTrocRepository.findById(receiver).get(), userTrocRepository.findById(transmitter).get()));
        return "redirect:/nextStep";
    }

}