package com.solvd.elasticsearchmicroservice.domain.criteria.field;

import lombok.Data;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;

import java.util.List;

@Data
public class Tag implements Field {

    List<String> tags;

    @Override
    public void apply(CriteriaQuery query) {
        if (tags != null && !tags.isEmpty()) {
            Criteria criteria = new Criteria("tag");
            criteria.in(tags);
            query.addCriteria(criteria);
        }
    }

}
