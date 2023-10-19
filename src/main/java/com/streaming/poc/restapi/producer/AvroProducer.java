package com.streaming.poc.restapi.producer;

import com.streaming.poc.restapi.producer.config.AvroProducerConfig;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

public abstract class AvroProducer<V> {
    protected final KafkaTemplate<String, V> kafkaTemplate;

    public AvroProducer(AvroProducerConfig avroProducerConfig) {
        this.kafkaTemplate = new KafkaTemplate<>(this.getProducerFactory(avroProducerConfig));
    }

    public abstract void sendMessage(V event);

    private ProducerFactory<String, V> getProducerFactory(AvroProducerConfig avroProducerConfig) {
        return new DefaultKafkaProducerFactory<>(avroProducerConfig.getProducerConfig());
    }
}
