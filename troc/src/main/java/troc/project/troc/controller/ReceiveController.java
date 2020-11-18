package troc.project.troc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;

@Controller
public class ReceiveController {

    @RequestMapping(value = "/ReceiveXML")
    public String receiverPage() {
        try{
            ReceverXML r = new ReceverXML();
            r.lireHeader("fichierXML.xml");
			
		}catch(NullPointerException | ParseException e) {
            System.err.println("Error in xml file \n"+e);
            return "ReceiveXML.html";
        
		}
        return "ReceiveXML";
    }
    
}
