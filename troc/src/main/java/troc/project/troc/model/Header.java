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
    @OneToOne(optional = true)
    Auth auth;
    @Column(name = "nbrMsg")
    Long nbrMsg;
    @OneToOne
    UserTroc receiver;

    @OneToOne
    UserTroc transmitter;

    public Header(Auth auth, Long nbrMsg, UserTroc receiver, UserTroc transmitter) {

        this.auth = auth;
        this.nbrMsg = nbrMsg;
        this.receiver = receiver;
        this.transmitter = transmitter;
    }

}
