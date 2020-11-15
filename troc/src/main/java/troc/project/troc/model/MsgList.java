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
@Table(name = "MsgList")
public class MsgList {
    @Id
    @Column(name = "msgListId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    long msgListId;
    @OneToOne
    FileTroc fileTroc;

    public MsgList() {

    }

    public MsgList(ListMsg listMsg, FileTroc fileTroc) {
        this.fileTroc = fileTroc;

    }
}
