package com.marcin.pawlicki.streaming.poc.restapi;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAvroApplication {
    @Value("${kafka.topic.mobile-click.name}")
    private String topicName;

    @Value("${kafka.topic.mobile-click.partitions-num}")
    private Integer partitions;

    @Value("${kafka.topic.mobile-click.replication-factor}")
    private short replicationFactor;

    @Bean
    NewTopic mobileClickTopic() {
        // TODO Get rid of auto creation
        return new NewTopic(topicName, partitions, replicationFactor);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringAvroApplication.class, args);
    }
}
