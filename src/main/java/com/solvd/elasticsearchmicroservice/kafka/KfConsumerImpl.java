package com.solvd.elasticsearchmicroservice.kafka;

import com.solvd.elasticsearchmicroservice.kafka.event.NoteEvent;
import com.solvd.elasticsearchmicroservice.kafka.handler.Handler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KfConsumerImpl implements KfConsumer{

    private final ApplicationContext context;

    @Bean
    public Map<String, Handler> handlers(ApplicationContext context) {
        return context.getBeansOfType(Handler.class);
    }

    @KafkaListener(topics = "${kafka.topic}", groupId = "groupId", containerFactory = "kafkaListenerContainerFactory")
    public void receive(NoteEvent event) {
        log.info("Message received -> {}", event);
        handlers(context).get(event.getType().name()).handle(event);
    }

}
