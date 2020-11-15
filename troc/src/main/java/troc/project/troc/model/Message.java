package troc.project.troc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Message")
public class Message {
    @Id
    @Column(name = "idMessage")
    @GeneratedValue(strategy = GenerationType.AUTO)
    long idMessage;
    @OneToOne
    Accept accept;
    @OneToOne
    Auth auth;
    @Column(name = "authRequest")
    Long authRequest;
    @OneToOne
    Barter barter;
    @OneToOne
    Cat cat;
    @Column(name = "catRequest")
    Long catRequest;
    @OneToOne
    Deny deny;
    @OneToOne
    Donation donation;
    @OneToOne
    ErrorMessage errorMessage;

    @OneToOne
    NoCat noCat;
    @OneToOne
    Request request;
    @Column(name = "validityDuration")
    Long validityDuration;

    public Message() {

    }

    public Message(Accept accept, Auth auth, Long authRequest, Barter barter, Cat cat, Long catRequest, Deny deny,
            Donation donation, ErrorMessage errorMessage, Long messageID, NoCat noCat, Request request,
            Long validityDuration) {
        this.accept = accept;
        this.auth = auth;
        this.authRequest = authRequest;
        this.barter = barter;
        this.cat = cat;
        this.catRequest = catRequest;
        this.deny = deny;
        this.donation = donation;
        this.errorMessage = errorMessage;
        this.noCat = noCat;
        this.request = request;
        this.validityDuration = validityDuration;
    }
}
