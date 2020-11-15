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
@Table(name = "Barter")
public class Barter {
    @Id
    @Column(name = "idBarter")
    @GeneratedValue(strategy = GenerationType.AUTO)
    long idBarter;
    @Column(name = "idPrevMsg")
    Long idPrevMsg;
    @OneToOne
    RcvObjectList rcvObjectList;
    @OneToOne
    SndObjectList sndObjectList;

    public Barter() {

    }

    public Barter(Long idPrevMsg, RcvObjectList rcvObjectList, SndObjectList sndObjectList) {
        this.idPrevMsg = idPrevMsg;
        this.rcvObjectList = rcvObjectList;
        this.sndObjectList = sndObjectList;
    }
}
