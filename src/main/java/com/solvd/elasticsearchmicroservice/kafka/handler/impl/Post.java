package com.solvd.elasticsearchmicroservice.kafka.handler.impl;

import com.solvd.elasticsearchmicroservice.domain.Note;
import com.solvd.elasticsearchmicroservice.kafka.event.NoteEvent;
import com.solvd.elasticsearchmicroservice.kafka.handler.Handler;
import com.solvd.elasticsearchmicroservice.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("POST")
@RequiredArgsConstructor
public class Post implements Handler {

    private final NoteService noteService;

    @Override
    public void handle(NoteEvent event) {
        Note note = new Note();
        note.setId(event.getId());
        note.setTag(event.getTag());
        note.setDescription(event.getDescription());
        note.setTheme(event.getTheme());
        note.setUserId(event.getUserId());
        noteService.create(note);
    }

}
