package com.solvd.elasticsearchmicroservice.domain.criteria;

import com.solvd.elasticsearchmicroservice.domain.criteria.field.Field;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public class NoteCriteria {

    private final List<Field> fields;

    public void apply(CriteriaQuery query) {
        fields.stream()
                .filter(Objects::nonNull)
                .forEach( field -> field.apply(query));
    }

}
