package com.solvd.elasticsearchmicroservice.kafka;

import com.solvd.elasticsearchmicroservice.domain.Note;
import com.solvd.elasticsearchmicroservice.kafka.event.NoteEvent;

public interface KfConsumer {

    void receive(NoteEvent event);

    void create(Note note);

    void update(Note note);

    void delete(Long id);

}
