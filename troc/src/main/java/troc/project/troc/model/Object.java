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
@Table(name = "Object")
public class Object {
    @Id
    @Column(name = "objectId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    long objectId;
    @Column(name = "objectName")
    String objectName;
    @Column(name = "objectDetails")
    String objectDetails;
    @Column(name = "objectImage")
    String objectImage;

    public Object() {

    }

    public Object(String name, String details, String image) {
        this.objectName = name;
        this.objectDetails = details;
        this.objectImage = image;

    }
}
