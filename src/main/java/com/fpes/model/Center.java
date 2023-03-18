package com.fpes.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "centers")
@Getter
@Setter
@NoArgsConstructor
public class Center extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "center_id")
    private Long id;

    @Column(name = "center_name")
    private String name;
    @Column(name = "center_code")
    private String code;
    @Column(name = "natural_type")
    private String naturalType;
    @Column(name = "center_type")
    private String type;
    @Column(name = "is_private_subtype")
    private String concert;
    @Column(name = "generic_name")
    private String genericName;
    @Column(name = "phone")
    private String phoneNumber;
    @Column(name = "fax")
    private String faxNumber;
    private String email;
    private String website;
    private String address;
    private String region;
    private String province;
    private String locality;
    private String municipality;
    private String zipcode;

    @OneToMany(mappedBy = "center")
    private Set<CenterComment> comments;

    @OneToMany(mappedBy = "center", cascade = CascadeType.ALL)
    private List<Study> studies;
}
