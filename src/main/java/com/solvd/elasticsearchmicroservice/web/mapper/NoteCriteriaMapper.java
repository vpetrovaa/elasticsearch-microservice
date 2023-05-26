package com.solvd.elasticsearchmicroservice.web.mapper;

import com.solvd.elasticsearchmicroservice.domain.criteria.NoteCriteria;
import com.solvd.elasticsearchmicroservice.domain.criteria.field.Field;
import com.solvd.elasticsearchmicroservice.web.dto.criteria.NoteCriteriaDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface NoteCriteriaMapper {

    NoteCriteriaDto toDto(NoteCriteria criteria);

    @Mapping(target = "fields",
            expression = "java(toFields(criteria))")
    NoteCriteria toEntity(NoteCriteriaDto criteria);

    default List<Field> toFields(NoteCriteriaDto criteria) {
        List<Field> fields = new ArrayList<>();
        fields.add(criteria.getTheme());
        fields.add(criteria.getTag());
        fields.add(criteria.getKeyWord());
        return fields;
    }

}
