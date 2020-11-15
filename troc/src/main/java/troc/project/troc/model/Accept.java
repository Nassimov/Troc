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
@Table(name = "Accept")
public class Accept {
    @Id
    @Column(name = "idAccept")
    @GeneratedValue(strategy = GenerationType.AUTO)
    long idAccept;
    @Column(name = "idPropositionMsg")
    long idPropositionMsg;

    public Accept() {

    }

    public Accept(long idPropositionMsg) {
        this.idPropositionMsg = idPropositionMsg;
    }
}
