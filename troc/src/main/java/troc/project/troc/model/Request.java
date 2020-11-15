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
@Table(name = "Request")
public class Request {
    @Id
    @Column(name = "requestId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    long requestId;
    @Column(name = "idPrevMsg")
    Long idPrevMsg;
    @OneToOne
    RcvObjectList rcvObjectList;

    public Request() {

    }

    public Request(Long idPrevMsg, RcvObjectList rcvObjectList) {
        this.idPrevMsg = idPrevMsg;
        this.rcvObjectList = rcvObjectList;

    }
}
