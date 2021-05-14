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
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.util.List;

/**
 * Сущность для работы с записью таблиы офисов в БД
 * @author Nashev
 */
@Data
@Entity
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    @Column(name = "name")
    private String name;

    @Column(name = "address",length = 4000)
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="org_id")
    private Organization organization;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "office",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    private List<User> users;
}
