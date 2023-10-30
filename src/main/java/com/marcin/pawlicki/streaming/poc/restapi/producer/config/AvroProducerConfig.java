package com.marcin.pawlicki.streaming.poc.restapi.producer.config;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AvroProducerConfig {
    @Value("${kafka.bootstrap-servers}")
    private String kafkaBoostrapServers;

    @Value("${kafka.schema-registry.url}")
    private String schemaRegistryUrl;

    public Map<String, Object> getProducerConfig() {
        Map<String, Object> propsMap = new HashMap<>();

        propsMap.put("schema.registry.url", this.schemaRegistryUrl);
        propsMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.kafkaBoostrapServers);
        propsMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        propsMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);

        return propsMap;
    }
}
