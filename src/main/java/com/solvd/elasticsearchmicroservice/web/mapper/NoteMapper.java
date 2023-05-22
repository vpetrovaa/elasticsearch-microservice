package com.solvd.elasticsearchmicroservice.web.mapper;

import com.solvd.elasticsearchmicroservice.domain.Note;
import com.solvd.elasticsearchmicroservice.web.dto.NoteDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    NoteDto toDto(Note note);

    Note toEntity(NoteDto noteDto);

    List<NoteDto> toDtoList(List<Note> notes);

}