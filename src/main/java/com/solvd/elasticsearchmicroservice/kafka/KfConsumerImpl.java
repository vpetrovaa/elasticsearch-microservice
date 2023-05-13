package com.solvd.elasticsearchmicroservice.kafka;

import com.solvd.elasticsearchmicroservice.domain.Note;
import com.solvd.elasticsearchmicroservice.kafka.event.NoteEvent;
import com.solvd.elasticsearchmicroservice.service.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KfConsumerImpl implements KfConsumer{

    private final NoteService noteService;

    @KafkaListener(topics = "${kafka.topic}", groupId = "groupId", containerFactory = "kafkaListenerContainerFactory")
    public void receive(NoteEvent event) {
        log.info("Message received -> {}", event);
        switch (event.getType()) {
            case PUT -> noteService.update(toNote(event));
            case POST -> noteService.create(toNote(event));
            case DELETE -> noteService.delete(event.getId());
        }
    }

    private Note toNote(NoteEvent event){
        Note note = new Note();
        note.setId(event.getId());
        note.setTag(event.getTag());
        note.setDescription(event.getDescription());
        note.setTheme(event.getTheme());
        note.setUserId(event.getUserId());
        return note;
    }

}
