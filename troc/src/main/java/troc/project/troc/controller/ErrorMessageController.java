package troc.project.troc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import troc.project.troc.model.ErrorMessage;
import troc.project.troc.repositories.ErrorMessageRepository;

@Controller
public class ErrorMessageController {

    @Autowired
    ErrorMessageRepository errorMessageRepository;

    @RequestMapping(value = "/addErrorMessage", method = RequestMethod.POST)
    public String addErrorMessage() {
        errorMessageRepository.save(new ErrorMessage());
        return "redirect:/";
    }

}