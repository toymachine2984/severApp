package com.project.server.resourceServer.repository;

import com.project.server.resourceServer.entity.Event;
import com.project.server.resourceServer.entity.dto.view.EventDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

    Page<EventDto.LazyEventDto> findAllBy(Pageable pageable);

    Event getById(Long id);


}
