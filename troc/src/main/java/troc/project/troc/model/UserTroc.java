package troc.project.troc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "UserTroc")
public class UserTroc {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idUser")
    private int idUser;

    @Column(name = "NAME")
    private String name;
    @Column(name = "LAST_NAME")
    private String lastName;

    public UserTroc(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;

    }
}