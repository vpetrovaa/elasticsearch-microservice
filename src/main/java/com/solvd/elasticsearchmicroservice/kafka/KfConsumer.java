package com.solvd.elasticsearchmicroservice.kafka;

import com.solvd.elasticsearchmicroservice.kafka.event.NoteEvent;

public interface KfConsumer {

    void receive(NoteEvent event);

}
