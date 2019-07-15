package com.project.server.resourceServer.service;

import com.project.server.resourceServer.entity.Participant;
import com.project.server.resourceServer.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("participantService")
@Transactional("dataTransactionManager")
public class ParticipantServiceImpl implements ParticipantService {

    private ParticipantRepository participantRepository;

    @Override
    public Iterable<Participant> getAll() {
        return participantRepository.findAll();
    }

    @Override
    public Participant save(Participant participant) {
        return participantRepository.save(participant);
    }

    @Autowired
    public void setParticipantRepository(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }
}
