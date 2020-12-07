package troc.project.troc.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SuccessFileGenerationController {

    @RequestMapping(value = "/successGeneration", method = RequestMethod.GET)
    public String successGenerationFile() {
        return "successGeneration";
    }
}
