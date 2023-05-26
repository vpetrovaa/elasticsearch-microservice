package com.solvd.elasticsearchmicroservice.kafka;

public interface KfConsumer<T> {

    void receive(T message);

}
