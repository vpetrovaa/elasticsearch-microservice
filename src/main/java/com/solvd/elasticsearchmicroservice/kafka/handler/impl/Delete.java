package com.solvd.elasticsearchmicroservice.kafka.handler.impl;

import com.solvd.elasticsearchmicroservice.kafka.event.NoteEvent;
import com.solvd.elasticsearchmicroservice.kafka.handler.Handler;
import com.solvd.elasticsearchmicroservice.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("DELETE")
@RequiredArgsConstructor
public class Delete implements Handler {

    private final NoteService noteService;

    @Override
    public void handle(NoteEvent event) {
        noteService.delete(event.getId());
    }

}
