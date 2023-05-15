package com.solvd.elasticsearchmicroservice.kafka.handler;

import com.solvd.elasticsearchmicroservice.kafka.event.NoteEvent;

public interface PutHandler extends Handler {

    void handle(NoteEvent event);

}
