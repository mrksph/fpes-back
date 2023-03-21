package com.fpes.dto.center;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CenterRes {
    private Long id;
    private String name;
    private String genericName;
    private String type;
    private String code;
    private String phoneNumber;
    private String naturalType;
    private String email;
    private String address;
    private String region;
    private String province;
    private String website;
    private String locality;
    private Set<CenterCommentRes> comments;
    private Set<StudyRes> studies;
}
