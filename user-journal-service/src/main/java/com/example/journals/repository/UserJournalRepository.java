package com.example.journals.repository;

import com.example.journals.model.UserEventData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJournalRepository extends JpaRepository<UserEventData, Long> {
}
