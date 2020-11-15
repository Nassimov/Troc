package troc.project.troc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "NoCat")
public class NoCat {
    @Id
    @Column(name = "noCatId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    long noCatId;
    @Column(name = "idCatRequestMsg")
    Long idCatRequestMsg;
    @Column(name = "reason")
    String reason;

    public NoCat() {

    }

    public NoCat(Long idCatRequestMsg, String reason) {
        this.idCatRequestMsg = idCatRequestMsg;
        this.reason = reason;

    }
}
