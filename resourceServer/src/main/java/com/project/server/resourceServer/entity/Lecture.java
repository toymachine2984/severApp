package com.project.server.resourceServer.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Access(value = AccessType.PROPERTY)
@Table(schema = "system", name = "lecturers", catalog = "tables")
public class Lecture {

    private Long id;
    private String name;
    private String position;
    private String description;
    private String photoLink;
    private List<Event> events;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "photolink")
    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Event.class, mappedBy = "lectures")
    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lecture lecture = (Lecture) o;

        return id.equals(lecture.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
