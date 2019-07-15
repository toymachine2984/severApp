package com.project.server.resourceServer.service;

import com.project.server.resourceServer.entity.Event;
import com.project.server.resourceServer.entity.dto.view.EventDto;
import com.project.server.resourceServer.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("eventService")
@Transactional("dataTransactionManager")
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;

    @Override
    public Page<EventDto.LazyEventDto> getAllDocuments(Pageable pageable) {
        return eventRepository.findAllBy(pageable);
    }

    @Override
    public Event getById(Long id) {
        return eventRepository.getById(id);
    }

    @Override
    public Event create(Event event) {
        return eventRepository.save(event);
    }

    @Autowired
    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
}
