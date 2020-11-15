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
@Table(name = "ErrorMessage")
public class ErrorMessage {
    @Id
    @Column(name = "idErrorMessage")
    @GeneratedValue(strategy = GenerationType.AUTO)
    long idErrorMessage;

    public ErrorMessage() {

    }

}
