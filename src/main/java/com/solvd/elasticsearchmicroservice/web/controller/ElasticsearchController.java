package com.solvd.elasticsearchmicroservice.web.controller;

import com.solvd.elasticsearchmicroservice.domain.Note;
import com.solvd.elasticsearchmicroservice.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/esearch")
@RequiredArgsConstructor
public class ElasticsearchController {

    private final NoteService noteService;

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

    @GetMapping("/exists/{id}")
    public boolean isExistById(@PathVariable Long id) {
        return noteService.isExistById(id);
    }

}
