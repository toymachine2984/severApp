package com.project.server.resourceServer.service;

import com.project.server.resourceServer.entity.Participant;

public interface ParticipantService {

    Iterable<Participant> getAll();

    Participant save(Participant participant);
}
