package com.solvd.elasticsearchmicroservice.kafka;

import com.solvd.elasticsearchmicroservice.kafka.event.NoteEvent;
import com.solvd.elasticsearchmicroservice.kafka.handler.Handler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KfConsumerImpl implements KfConsumer{

    private final Map<String, Handler> handlers;

    @KafkaListener(topics = "${kafka.topic}", groupId = "groupId", containerFactory = "kafkaListenerContainerFactory")
    public void receive(NoteEvent event) {
        log.info("Message received -> {}", event);
        handlers.get(event.getType().name()).handle(event);
    }

}
