package com.solvd.elasticsearchmicroservice.web.dto.criteria;

import com.solvd.elasticsearchmicroservice.domain.criteria.field.KeyWord;
import com.solvd.elasticsearchmicroservice.domain.criteria.field.Tag;
import com.solvd.elasticsearchmicroservice.domain.criteria.field.Theme;
import lombok.Data;

@Data
public class NoteCriteriaDto {

    private KeyWord keyWord;
    private Theme theme;
    private Tag tag;

}
