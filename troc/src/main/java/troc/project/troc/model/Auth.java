package troc.project.troc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Auth")
public class Auth {
    @Id
    @Column(name = "idAuth")
    @GeneratedValue(strategy = GenerationType.AUTO)
    long idAuth;
    @Column(name = "authDate")
    Date authDate;
    @Column(name = "authRef")
    String authRef;

    public Auth() {

    }

    public Auth(Date authDate, String authRef) {
        this.authDate = authDate;
        this.authRef = authRef;
    }
}
