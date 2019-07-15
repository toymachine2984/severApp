package com.project.server.resourceServer.entity;


import com.fasterxml.jackson.annotation.JsonView;
import com.project.server.resourceServer.entity.dto.view.EventDto;
import com.project.server.resourceServer.entity.dto.view.TypeDto;

import javax.persistence.*;
import java.util.List;

@Entity
@Access(value = AccessType.PROPERTY)
@Table(schema = "system", name = "types", catalog = "tables")
public class Type {

    @JsonView(value = TypeDto.LazyTypeDto.class)
    private Long id;
    @JsonView(value = TypeDto.LazyTypeDto.class)
    private String value;
    @JsonView(value = TypeDto.LazyTypeDto.class)
    private String description;
    private List<Event> event;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "type",fetch = FetchType.LAZY,targetEntity = Event.class)
    public List<Event> getEvent() {
        return event;
    }

    public void setEvent(List<Event> event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Type type = (Type) o;

        return getId().equals(type.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
