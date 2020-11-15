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
@Table(name = "SndObjectList")
public class SndObjectList {
    @Id
    @Column(name = "idSndObjectList")
    @GeneratedValue(strategy = GenerationType.AUTO)
    long idSndObjectList;

    public SndObjectList() {

    }
}
