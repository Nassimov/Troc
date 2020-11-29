package troc.project.troc.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import troc.project.troc.model.Message;
import troc.project.troc.repositories.AcceptRepository;
import troc.project.troc.repositories.AuthRepository;
import troc.project.troc.repositories.BarterRepository;
import troc.project.troc.repositories.CatRepository;
import troc.project.troc.repositories.DenyRepository;
import troc.project.troc.repositories.DonationRepository;
import troc.project.troc.repositories.ErrorMessageRepository;
import troc.project.troc.repositories.MessageRepository;
import troc.project.troc.repositories.NoCatRepository;
import troc.project.troc.repositories.RequestRepository;

@Controller
public class MessageController {

        @Autowired
        MessageRepository messageRepository;
        @Autowired
        AcceptRepository acceptRepository;
        @Autowired
        AuthRepository authRepository;
        @Autowired
        BarterRepository barterRepository;
        @Autowired
        CatRepository catRepository;
        @Autowired
        DenyRepository denyRepository;
        @Autowired
        DonationRepository donationRepository;
        @Autowired
        ErrorMessageRepository errorMessageRepository;
        @Autowired
        NoCatRepository noCatRepository;
        @Autowired
        RequestRepository requestRepository;

        @RequestMapping(value = "/addMessage", method = RequestMethod.POST)

        public String addMessage(@RequestParam Long accept, @RequestParam Long auth, @RequestParam Long authRequest,
                        @RequestParam Long barter, @RequestParam Long cat, @RequestParam Long catRequest,
                        @RequestParam Long deny, @RequestParam Long donation, @RequestParam Long errorMessage,
                        @RequestParam Long noCat, @RequestParam Long request, @RequestParam Long validityDuration,
                        @RequestParam String messageDate, @RequestParam String messageTime, Model m) {
                int year = Integer.parseInt(messageDate.split("-")[0]);
                int goodYear = year - 1900;
                int month = Integer.parseInt(messageDate.split("-")[1]);
                int date = Integer.parseInt(messageDate.split("-")[2]);
                int h1 = Integer.parseInt(messageTime.split(":")[0]);
                int m1 = Integer.parseInt(messageTime.split(":")[1]);
                Date newDate = new Date(goodYear, month, date, h1, m1);

                Message newMessage = new Message(
                                acceptRepository.findById(accept).isPresent() ? acceptRepository.findById(accept).get()
                                                : null,
                                authRepository.findById(auth).isPresent() ? authRepository.findById(auth).get() : null,
                                authRequest,
                                barterRepository.findById(barter).isPresent() ? barterRepository.findById(barter).get()
                                                : null,
                                catRepository.findById(cat).isPresent() ? catRepository.findById(cat).get() : null,
                                catRequest != null ? catRequest : null,
                                denyRepository.findById(deny).isPresent() ? denyRepository.findById(deny).get() : null,
                                donationRepository.findById(donation).isPresent()
                                                ? donationRepository.findById(donation).get()
                                                : null,
                                errorMessageRepository.findById(errorMessage).isPresent()
                                                ? errorMessageRepository.findById(errorMessage).get()
                                                : null,
                                noCatRepository.findById(noCat).isPresent() ? noCatRepository.findById(noCat).get()
                                                : null,
                                requestRepository.findById(request).isPresent()
                                                ? requestRepository.findById(request).get()
                                                : null,
                                validityDuration, newDate); // juste pour

                messageRepository.save(newMessage);
                return "redirect:/nextStep";
        }
}
