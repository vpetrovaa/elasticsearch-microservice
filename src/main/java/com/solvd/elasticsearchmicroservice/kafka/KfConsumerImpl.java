package com.solvd.elasticsearchmicroservice.kafka;

import com.solvd.elasticsearchmicroservice.kafka.event.NoteEvent;
import com.solvd.elasticsearchmicroservice.kafka.handler.DeleteHandler;
import com.solvd.elasticsearchmicroservice.kafka.handler.Handler;
import com.solvd.elasticsearchmicroservice.kafka.handler.PostHandler;
import com.solvd.elasticsearchmicroservice.kafka.handler.PutHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KfConsumerImpl implements KfConsumer{

    private final PostHandler postHandler;
    private final PutHandler putHandler;
    private final DeleteHandler deleteHandler;

    private final Map<String, Handler> processors = new HashMap<>();

    @KafkaListener(topics = "${kafka.topic}", groupId = "groupId", containerFactory = "kafkaListenerContainerFactory")
    public void receive(NoteEvent event) {
        log.info("Message received -> {}", event);
        processors.put("POST", postHandler);
        processors.put("PUT", putHandler);
        processors.put("DELETE",  deleteHandler);
        processors.get(event.getType()).handle(event);
    }

}
