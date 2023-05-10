package com.solvd.elasticsearchmicroservice.service.impl;

import com.solvd.elasticsearchmicroservice.domain.Note;
import com.solvd.elasticsearchmicroservice.repository.NoteRepository;
import com.solvd.elasticsearchmicroservice.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    @Override
    public Note create(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public Note update(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public void delete(Long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public List<Note> findAllByUserId(Long userId) {
        Iterable<Note> iterable = noteRepository.findAllByUserId(userId);
        List<Note> notes = new ArrayList<>();
        iterable.forEach(notes::add);
        return notes;
    }

    @Override
    public boolean isExistById(Long id) {
        return noteRepository.existsById(id);
    }

    @Override
    public List<Note> findAll() {
        Iterable<Note> iterable = noteRepository.findAll();
        List<Note> notes = new ArrayList<>();
        iterable.forEach(notes::add);
        return notes;
    }

    @Override
    public Optional<Note> findById(Long id) {
        return noteRepository.findById(id);
    }

}
