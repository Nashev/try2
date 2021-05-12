package ru.nashev.try2.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import java.util.Date;

@Data
@Entity
public class UserDoc {
    @Id
    @Column(name = "Id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    @Column(name = "doc_name")
    private String docName;

    @Column(name = "doc_number")
    private String docNumber;

    @Column(name = "doc_date")
    private Date docDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_id")
    private Doc doc;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn (name="id")
    private User user;
}
