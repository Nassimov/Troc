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
@Table(name = "Donation")
public class Donation {
    @Id
    @Column(name = "idDonation")
    @GeneratedValue(strategy = GenerationType.AUTO)
    long idDonation;
    @Column(name = "idPrevMsg")
    Long idPrevMsg;
    @OneToOne
    SndObjectList sndObjectList;

    public Donation() {

    }

    public Donation(Long idPrevMsg, SndObjectList sndObjectList) {
        this.idPrevMsg = idPrevMsg;
        this.sndObjectList = sndObjectList;
    }
}
