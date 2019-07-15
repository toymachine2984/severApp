package com.project.server.resourceServer.controller;

import com.project.server.resourceServer.entity.Participant;
import com.project.server.resourceServer.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "participants")
public class ParticipantController {

    private ParticipantService participantService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<Participant> getOrders() {
        return participantService.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Participant addOrder(@RequestBody Participant participant) {
        return participantService.save(participant);
    }

    @Autowired
    public void setParticipantService(ParticipantService participantService) {
        this.participantService = participantService;
    }
}
