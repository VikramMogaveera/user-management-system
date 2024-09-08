package com.example.journals.controller;

import com.example.journals.model.UserEventData;
import com.example.journals.service.UserJournalService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user-events")
public class UserEventController {

    private UserJournalService userJournalService;

    @Operation(summary = "Get All User Journal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<List<UserEventData>> getAllEvents() {
        List<UserEventData> events = userJournalService.getAllEvents();

        return ResponseEntity.ok(events);
    }
}
