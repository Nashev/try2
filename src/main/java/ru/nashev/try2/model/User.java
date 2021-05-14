package ru.nashev.try2.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

/**
 * Сущность для работы с записью таблиы пользователей в БД
 * @author Nashev
 */
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    /** Служебное поле hibernate */
    @Version
    private Integer version;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "position")
    private String position;

    @Column(name = "phone")
    private String phone;

    @Column(name = "is_identified")
    private Boolean isIdentified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="office_id")
    private Office office;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="citizenship_id")
    private Country citizenship;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    @JoinColumn(name="id")
    private UserDoc userDoc;

    public void setUserDoc(UserDoc userDoc) {
        this.userDoc = userDoc;
        userDoc.setUser(this);
    }
}
