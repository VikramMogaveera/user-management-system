package com.example.journals.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_events")
public class UserEventData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_event")
    private String eventData;

    public UserEventData(String eventData) {
        this.eventData = eventData;
    }

}
