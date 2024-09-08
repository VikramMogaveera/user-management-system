package com.example.journals.service.userjournalserviceimpl;

import com.example.journals.model.UserEventData;
import com.example.journals.repository.UserJournalRepository;
import com.example.journals.service.UserJournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserJournalServiceImpl implements UserJournalService {

    @Autowired
    private UserJournalRepository userJournalRepository;

    @Override
    public UserEventData createUserEvent(UserEventData eventData) {
        return userJournalRepository.save(eventData);
    }

    @Override
    public List<UserEventData> getAllEvents() {
        return userJournalRepository.findAll();
    }
}
