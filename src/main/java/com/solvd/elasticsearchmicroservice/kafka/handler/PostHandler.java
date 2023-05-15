package com.solvd.elasticsearchmicroservice.kafka.handler;

import com.solvd.elasticsearchmicroservice.kafka.event.NoteEvent;

public interface PostHandler extends Handler{

    void handle(NoteEvent event);

}
