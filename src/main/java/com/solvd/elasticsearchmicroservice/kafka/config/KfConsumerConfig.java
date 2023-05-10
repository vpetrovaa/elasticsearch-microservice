package com.solvd.elasticsearchmicroservice.kafka.config;

import com.solvd.elasticsearchmicroservice.kafka.event.NoteEvent;
import com.solvd.elasticsearchmicroservice.kafka.property.KfProperties;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KfConsumerConfig {

    private final KfProperties kfProperties;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kfProperties.getPort());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kfProperties.getGroup());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, generateDeserializer());
        return props;
    }

    @Bean
    public ConsumerFactory<String, NoteEvent> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
                generateDeserializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, NoteEvent> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, NoteEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }

    private JsonDeserializer<NoteEvent> generateDeserializer(){
        JsonDeserializer<NoteEvent> deserializer = new JsonDeserializer<>(NoteEvent.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        return deserializer;
    }

}
