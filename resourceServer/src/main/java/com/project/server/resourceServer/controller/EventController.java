package com.project.server.resourceServer.controller;


import com.project.server.resourceServer.entity.Event;
import com.project.server.resourceServer.entity.dto.IEvent;
import com.project.server.resourceServer.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "events")
public class EventController {

    private EventService eventService;


//    @PreAuthorize("#oauth2.hasScope('foo') and #oauth2.hasScope('read')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Page<? extends IEvent> getEvents(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                                            @RequestParam(value = "length", required = false, defaultValue = "10") int length) {
        return eventService.getAllDocuments(PageRequest.of(start, length));
    }

    @GetMapping(value = {"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Event getEvent(@PathVariable("id") Long id) {
        return eventService.getById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Event create(@RequestBody Event event) {
        return eventService.create(event);
    }


    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }
}
