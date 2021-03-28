package com.fpes.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @Column(name = "center_code")
    private String code;
    @Column(name = "center_name")
    private String name;
    @Column(name = "center_gen_name")
    private String genericName;
    @Column(name = "center_type")
    private String type;
    @Column(name = "phone_num")
    private String phoneNumber;
    private String address;
    private String province;
    private String locality;

    @OneToMany(mappedBy = "center")
    private Set<CenterComment> comments;
}
