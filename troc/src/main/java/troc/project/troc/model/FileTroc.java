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
@Table(name = "FileTroc")
public class FileTroc {
    @Id
    @Column(name = "idFileTroc")
    @GeneratedValue(strategy = GenerationType.AUTO)
    long idFileTroc;
    @Column(name = "filePath")
    String filePath;
    @OneToOne
    Header header;
    @OneToOne
    MsgList msgList;
    @Column(name = "idf")
    Long idf;

    public FileTroc() {

    }

    public FileTroc(Header header, MsgList msgList) {

        this.header = header;
        this.msgList = msgList;
    }

}