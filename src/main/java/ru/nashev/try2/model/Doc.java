package ru.nashev.try2.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Сущность для работы с записью таблиы типов документов в БД
 * @author Nashev
 */
@Data
@Entity
public class Doc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    /** Служебное поле hibernate */
    @Version
    private Integer version;

    @Column(name = "code", nullable=false)
    private String code;

    @Column(name = "name")
    private String name;
}
