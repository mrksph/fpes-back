package com.fpes.dto.center;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SearchCenterReq {
    private String searchTerm;
    private List<String> type = new ArrayList<>();
    private List<String> rating = new ArrayList<>();
    private List<String> studyFormat = new ArrayList<>();
    private List<String> level = new ArrayList<>();
    private String region;
    private String province;

}
