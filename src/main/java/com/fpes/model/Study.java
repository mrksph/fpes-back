package com.fpes.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "studies")
public class Study extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_id")
    private Integer studyId;

    @Column(name = "generic_den")
    private String genericDen;

    @Column(name = "family")
    private String family;

    @Column(name = "name")
    private String name;

    @Column(name = "format")
    private String format;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;
}