package com.solvd.elasticsearchmicroservice.domain.criteria;

import lombok.Data;

import java.util.List;

@Data
public class NoteCriteria {

    private String keyWord;
    private List<String> themes;
    private List<String> tags;

}
