package com.solvd.elasticsearchmicroservice.domain.criteria.field;

import lombok.Data;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;

@Data
public class KeyWord implements Field {

    private String keyWord;

    @Override
    public void apply(CriteriaQuery query) {
        if (keyWord != null) {
            Criteria criteria = new Criteria("description");
            criteria.contains(keyWord);
            query.addCriteria(criteria);
        }
    }

}
