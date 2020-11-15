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
@Table(name = "Cat")
public class Cat {
    @Id
    @Column(name = "idCat")
    @GeneratedValue(strategy = GenerationType.AUTO)
    long idCat;
    @Column(name = "catDate")
    Date catDate;

    public Cat() {

    }

    public Cat(Date catDate) {
        this.catDate = catDate;
    }
}
