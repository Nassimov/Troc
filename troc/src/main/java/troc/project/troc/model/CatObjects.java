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
@Table(name = "CatObjects")
public class CatObjects {
    @Id
    @Column(name = "idCatObjects")
    @GeneratedValue(strategy = GenerationType.AUTO)
    long idCatObjects;
    @OneToOne
    Object object;

    @OneToOne
    Cat cat;

    public CatObjects() {

    }

    public CatObjects(Object object, Cat cat) {
        this.object = object;
        this.cat = cat;
    }
}
