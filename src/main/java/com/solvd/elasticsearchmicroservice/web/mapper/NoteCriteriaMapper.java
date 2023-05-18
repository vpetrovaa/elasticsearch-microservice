package com.solvd.elasticsearchmicroservice.web.mapper;

import com.solvd.elasticsearchmicroservice.domain.criteria.NoteCriteria;
import com.solvd.elasticsearchmicroservice.web.dto.criteria.NoteCriteriaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteCriteriaMapper {

    NoteCriteriaDto toDto(NoteCriteria note);

    NoteCriteria toEntity(NoteCriteriaDto noteDto);

}
