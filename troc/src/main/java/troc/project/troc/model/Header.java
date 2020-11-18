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
@Table(name = "Header")
public class Header {
    @Id
    @Column(name = "idHeader")
    @GeneratedValue(strategy = GenerationType.AUTO)
    long idHeader;
    @OneToOne
    Auth auth;
    @Column(name = "nbrMsg")
    Integer nbrMsg;
    @OneToOne
    UserTroc receiver;

    @OneToOne
    UserTroc transmitter;

    public Header() {

    }

    public Header(Auth auth, Integer nbrMsg, UserTroc receiver, UserTroc transmitter) {

        this.auth = auth;
        this.nbrMsg = nbrMsg;
        this.receiver = receiver;
        this.transmitter = transmitter;
    }

}
