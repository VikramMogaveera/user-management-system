package com.example.journals.service.kafka;

import com.example.journals.model.UserEventData;
import com.example.journals.service.UserJournalService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserJournalConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserJournalConsumer.class);

    private UserJournalService userJournalService;
    @KafkaListener(topics = "user-events", groupId = "userGroup")
    public void consume(String event) {

        LOGGER.info(String.format("User Events -> %s", event));

        UserEventData eventData = new UserEventData(event);

        UserEventData data = userJournalService.createUserEvent(eventData);

        LOGGER.info(String.format("User Event saved -> %s", data.toString()));

    }
}
