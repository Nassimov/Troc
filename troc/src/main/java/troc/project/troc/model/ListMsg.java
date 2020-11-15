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
@Table(name = "ListMsg")
public class ListMsg {
    @Id
    @Column(name = "idListMsg")
    @GeneratedValue(strategy = GenerationType.AUTO)
    long idListMsg;
    @OneToOne
    Message message;
    @OneToOne
    MsgList msgList;

    public ListMsg() {

    }

    public ListMsg(Message message, MsgList msgList) {
        this.message = message;
        this.msgList = msgList;
    }
}
