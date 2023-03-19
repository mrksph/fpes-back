package com.fpes.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "studies_temp")
public class Study extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_id")
    private Integer studyId;

    @Column(name = "type")
    private String type;

    @Column(name = "family")
    private String family;

    @Column(name = "name")
    private String name;

    @Column(name = "format")
    private String format;

    @ManyToOne
    @JoinTable(name = "center_study",
            joinColumns = @JoinColumn(name = "study_id"),
            inverseJoinColumns = @JoinColumn(name = "center_id"))
    private Center center;
}