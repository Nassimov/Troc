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

    public CatObjects(Object obj, Cat cat) {
        this.object = obj;
        this.cat = cat;
    }
}
