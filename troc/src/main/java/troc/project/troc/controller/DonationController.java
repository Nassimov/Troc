package troc.project.troc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import troc.project.troc.model.Donation;
import troc.project.troc.repositories.DonationRepository;
import troc.project.troc.repositories.SndObjectListRepository;

@Controller
public class DonationController {

    @Autowired
    DonationRepository donationRepositories;
    @Autowired
    SndObjectListRepository sndRepositories;

    @RequestMapping(value = "/addDonation", method = RequestMethod.POST)
    public String addDonation(@RequestParam Long idPrevMsg, @RequestParam Long sndObjList, Model m) {
        donationRepositories.save(new Donation(idPrevMsg, sndRepositories.findById(sndObjList).get()));
        return "redirect:/";
    }

}