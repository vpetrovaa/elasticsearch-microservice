package com.solvd.elasticsearchmicroservice.kafka.handler.impl;

import com.solvd.elasticsearchmicroservice.domain.Note;
import com.solvd.elasticsearchmicroservice.kafka.event.NoteEvent;
import com.solvd.elasticsearchmicroservice.kafka.handler.PutHandler;
import com.solvd.elasticsearchmicroservice.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PutHandlerImpl implements PutHandler {

    private final NoteService noteService;

    @Override
    public void handle(NoteEvent event) {
        Note note = new Note();
        note.setId(event.getId());
        note.setTag(event.getTag());
        note.setDescription(event.getDescription());
        note.setTheme(event.getTheme());
        note.setUserId(event.getUserId());
        noteService.update(note);
    }

}
