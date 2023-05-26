package com.solvd.elasticsearchmicroservice.domain.criteria.field;

import lombok.Data;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;

import java.util.List;

@Data
public class Theme implements Field {

    private List<String> themes;

    @Override
    public void apply(CriteriaQuery query) {
        if (themes != null && !themes.isEmpty()) {
            Criteria criteria = new Criteria("theme");
            criteria.in(themes);
            query.addCriteria(criteria);
        }
    }

}
