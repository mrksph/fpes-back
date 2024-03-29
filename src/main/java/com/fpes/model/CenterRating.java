package com.fpes.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CenterRating extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "rating_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;

    private Double rating;

}
