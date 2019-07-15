package com.project.server.resourceServer.entity.dto.view;

import com.project.server.resourceServer.entity.Address;
import com.project.server.resourceServer.entity.Status;
import com.project.server.resourceServer.entity.Type;
import com.project.server.resourceServer.entity.dto.IEvent;

import java.util.Date;

public class EventDto {

    public interface LazyEventDto extends IEvent {

        Long getId();

        String getTitle();

        String getDescription();

        Date getStartDate();

        Date getEndDate();

        Status getStatus();

        Address getAddress();

        String getImageLink();

        String getPreviewLink();

        String getVideoLink();

        Long getEtutoriumId();

        Type getType();
    }
}
