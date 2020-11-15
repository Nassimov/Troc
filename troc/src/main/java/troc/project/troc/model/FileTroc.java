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
    @Column(name = "fileID")
    long fileID;
    @Column(name = "filePath")
    String filePath;
    @OneToOne
    Header header;

    public FileTroc() {

    }

    public FileTroc(long idFileTroc, long fileID, String filePath, Header header) {
        this.idFileTroc = idFileTroc;
        this.fileID = fileID;
        this.filePath = filePath;
        this.header = header;
    }

}