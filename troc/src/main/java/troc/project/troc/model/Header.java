package troc.project.troc.model;

import java.util.Date;

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
@Table(name = "Header")
public class Header {
    @Id
    @Column(name = "idHeader")
    @GeneratedValue(strategy = GenerationType.AUTO)
    long idHeader;
    @Column(name = "authDate")
    Date authDate;
    @Column(name = "authRef")
    String authRef;
    @Column(name = "nbrMsg")
    Long nbrMsg;
    @OneToOne
    User receiver;

    @OneToOne
    User transmitter;

    public Header() {

    }

    public Header(Date authDate, String authRef, Long nbrMsg, User receiver, User transmitter) {
        this.authDate = authDate;
        this.authRef = authRef;
        this.nbrMsg = nbrMsg;
        this.receiver = receiver;
        this.transmitter = transmitter;
    }

}
