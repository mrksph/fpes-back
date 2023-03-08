package com.fpes.dto.center;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudyRes {
    private Integer id;
    private String name;
    private String format;
    private String family;
    private String genericDen;
}
