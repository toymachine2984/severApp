package com.project.server.resourceServer.service;

import com.project.server.resourceServer.entity.Event;
import com.project.server.resourceServer.entity.dto.view.EventDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {

    Page<EventDto.LazyEventDto> getAllDocuments(Pageable pageable);

    Event getById(Long id);

    Event create(Event event);
}
