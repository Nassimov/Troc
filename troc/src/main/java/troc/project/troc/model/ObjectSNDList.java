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
@Table(name = "ObjectSNDList")
public class ObjectSNDList {
    @Id
    @Column(name = "objetSNDListId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    long objetSNDListId;

    @OneToOne
    Object object;
    @OneToOne
    SndObjectList SndObjectList;

    public ObjectSNDList() {

    }

    public ObjectSNDList(Object object, SndObjectList SndObjectList) {
        this.object = object;
        this.SndObjectList = SndObjectList;

    }
}
