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
@Table(name = "Deny")
public class Deny {
    @Id
    @Column(name = "idDeny")
    @GeneratedValue(strategy = GenerationType.AUTO)
    long idDeny;
    @Column(name = "idPropositionMsg")
    Integer idPropositionMsg;
    @Column(name = "reason")
    String reason;

    public Deny() {

    }

    public Deny(Integer idPropositionMsg, String reason) {
        this.idPropositionMsg = idPropositionMsg;
        this.reason = reason;
    }
}
