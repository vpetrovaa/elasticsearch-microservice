package com.solvd.elasticsearchmicroservice.service;

import com.solvd.elasticsearchmicroservice.domain.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {

    Note create(Note note);

    Note update(Note note);

    void delete(Long id);

    List<Note> findAllByUserId(Long userId);

    boolean isExistById(Long id);

    List<Note> findAll();

    Optional<Note> findById(Long id);

}
