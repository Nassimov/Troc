package troc.project.troc.controller;

import org.springframework.beans.factory.annotation.Autowired;

import troc.project.troc.model.Header;
import troc.project.troc.ParserXML;
import java.text.ParseException;

public class ReceverXML {

    @Autowired
    Header headRepo;

    public ReceverXML(){

    }

    public void lireHeader(String fileName) throws ParseException,NullPointerException{
		int idF = ParserXML.recupIdF(fileName);
        System.err.println(idF);
        
        int nbMsg= ParserXML.nombreMsg(fileName);
        System.err.println(nbMsg);

        //headRepo.save(new Header());
            
    }
    
    
}
