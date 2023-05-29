package com.solvd.elasticsearchmicroservice.web.controller;

import com.solvd.elasticsearchmicroservice.domain.Note;
import com.solvd.elasticsearchmicroservice.service.NoteService;
import com.solvd.elasticsearchmicroservice.web.dto.NoteDto;
import com.solvd.elasticsearchmicroservice.web.dto.criteria.NoteCriteriaDto;
import com.solvd.elasticsearchmicroservice.web.mapper.NoteCriteriaMapper;
import com.solvd.elasticsearchmicroservice.web.mapper.NoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/esearch")
@RequiredArgsConstructor
public class ElasticsearchController {

    private final NoteService noteService;
    private final NoteCriteriaMapper noteCriteriaMapper;
    private final NoteMapper noteMapper;

    @GetMapping("/users/{userId}")
    public List<Note> findAllByUserId(@PathVariable("userId") Long userId) {
        return noteService.findAllByUserId(userId);
    }

    @GetMapping
    public List<Note> findAll() {
        return noteService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Note> findById(@PathVariable("id") Long id) {
        Optional<Note> note = noteService.findById(id);
        return note;
    }


    @GetMapping(value = "/search")
    public List<NoteDto> findByCriteriaOrdered(@RequestParam(required = false) Integer currentPage,
                                               @RequestBody(required = false) @Validated NoteCriteriaDto criteria,
                                               @RequestParam(required = false) String orderingField) {
        List<Note> notes =  noteService.findByCriteriaOrdered(currentPage,
                noteCriteriaMapper.toEntity(criteria), orderingField);
        return noteMapper.toDtoList(notes);
    }

    @GetMapping("/exists/{id}")
    public boolean isExistById(@PathVariable Long id) {
        return noteService.isExistById(id);
    }

}
