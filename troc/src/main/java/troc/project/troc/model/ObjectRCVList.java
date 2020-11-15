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
@Table(name = "ObjetRCVList")
public class ObjectRCVList {
    @Id
    @Column(name = "objetRCVListId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    long objetRCVListId;

    @OneToOne
    Object object;

    @OneToOne
    RcvObjectList rcvObjectList;

    public ObjectRCVList() {

    }

    public ObjectRCVList(Object object, RcvObjectList rcvObjectList) {
        this.object = object;
        this.rcvObjectList = rcvObjectList;

    }
}
