package com.fpes.dto.center;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SearchCenterReq {
    private String searchTerm;
    private List<String> type;
    private List<String> rating;
    private List<String> mode;
    private List<String> level;
    private String region;
    private String province;
}
