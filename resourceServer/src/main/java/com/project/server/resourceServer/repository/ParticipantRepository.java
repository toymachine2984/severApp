package com.project.server.resourceServer.repository;

import com.project.server.resourceServer.entity.Participant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends CrudRepository<Participant, Long> {
}
