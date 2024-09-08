package com.example.users.service;

import com.example.common.entities.User;
import com.example.users.entity.UserEventData;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserEventPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserEventPublisher.class);
    private static final String topic = "user-events";

    private KafkaTemplate<String, String> kafkaTemplate;
    public void publishUserCreatedEvent(User user) {

        UserEventData eventData = new UserEventData(
                "CREATED_USER",
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.getRoles(),
                user.getCreatedAt()
        );
        LOGGER.info(String.format("event data -> %s", eventData.toString()));

        Message<String> message = MessageBuilder
                .withPayload(eventData.toString())
                        .setHeader(KafkaHeaders.TOPIC, topic).build();

        kafkaTemplate.send(message);
    }

    public void publishUserDeletedEvent(User user) {
        UserEventData eventData = new UserEventData(
                "DELETE_USER",
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.getRoles(),
                user.getCreatedAt()
        );

        Message<String> message = MessageBuilder
                .withPayload(eventData.toString())
                .setHeader(KafkaHeaders.TOPIC, topic).build();

        kafkaTemplate.send(message);
    }

    public void publishUserUpdatedEventByUser(User user) {
        UserEventData eventData = new UserEventData(
                "UPDATE_USER",
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.getRoles(),
                user.getCreatedAt()
        );

        Message<String> message = MessageBuilder
                .withPayload(eventData.toString())
                .setHeader(KafkaHeaders.TOPIC, topic).build();

        kafkaTemplate.send(message);
    }

    public void publishUserUpdatedEventByAdmin(User user) {
        UserEventData eventData = new UserEventData(
                "UPDATE_USER_BY_ADMIN",
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.getRoles(),
                user.getCreatedAt()
        );

        Message<String> message = MessageBuilder
                .withPayload(eventData.toString())
                .setHeader(KafkaHeaders.TOPIC, topic).build();

        kafkaTemplate.send(message);
    }
}
