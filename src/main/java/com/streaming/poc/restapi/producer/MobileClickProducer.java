package com.streaming.poc.restapi.producer;

import com.streaming.poc.events.MobileClick;
import com.streaming.poc.restapi.producer.config.AvroProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MobileClickProducer extends AvroProducer<MobileClick> {
    private final Logger LOGGER = LoggerFactory.getLogger(MobileClickProducer.class);

    @Autowired
    public MobileClickProducer(AvroProducerConfig avroProducerConfig) {
        super(avroProducerConfig);
    }

    @Value("${kafka.topic.mobile-click.name}")
    private String topic;

    @Override
    public void sendMessage(MobileClick mobileClick) {
        this.kafkaTemplate.send(this.topic, Long.toString(mobileClick.getId()), mobileClick);
        this.LOGGER.info(String.format("Produced MobileClick -> %s", mobileClick));
    }
}
