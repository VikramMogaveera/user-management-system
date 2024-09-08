package com.example.journals.service;

import com.example.journals.model.UserEventData;

import java.util.List;

public interface UserJournalService {

    UserEventData createUserEvent(UserEventData eventData);

    List<UserEventData> getAllEvents();

}
