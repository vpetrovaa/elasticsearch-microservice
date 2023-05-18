package com.solvd.elasticsearchmicroservice.web.dto.criteria;

import lombok.Data;

import java.util.List;

@Data
public class NoteCriteriaDto {

    private String keyWord;
    private List<String> themes;
    private List<String> tags;

}
