package com.project.server.resourceServer.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.server.resourceServer.entity.dto.view.EventDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Access(value = AccessType.PROPERTY)
@Table(schema = "system", name = "events", catalog = "tables")
public class Event implements Serializable {

    @JsonView(value = EventDto.LazyEventDto.class)
    private Long id;

    @JsonView(value = EventDto.LazyEventDto.class)
    private String title;

    @JsonView(value = EventDto.LazyEventDto.class)
    private String description;

    @JsonView(value = EventDto.LazyEventDto.class)
    private Date startDate;

    @JsonView(value = EventDto.LazyEventDto.class)
    private Date endDate;

    @JsonView(value = EventDto.LazyEventDto.class)
    private Status status;

    @JsonView(value = EventDto.LazyEventDto.class)
    private Address address;

    @JsonView(value = EventDto.LazyEventDto.class)
    private String imageLink;

    @JsonView(value = EventDto.LazyEventDto.class)
    private String previewLink;

    @JsonView(value = EventDto.LazyEventDto.class)
    private String videoLink;

    @JsonView(value = EventDto.LazyEventDto.class)
    private Long etutoriumId;

    @JsonView(value = EventDto.LazyEventDto.class)
    private Type type;

    private List<Participant> participants;
    private List<Lecture> lectures;
    private List<Tag> tags;
    private List<Tariff> tariffs;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "startdate")
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name = "enddate")
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Status.class)
    @JoinColumn(name = "statusid", nullable = false)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "addressid", nullable = false)
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Column(name = "imagelink")
    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @Column(name = "previewlink")
    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    @Column(name = "videolink")
    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }


    @Column(name = "etutoriumid")
    public Long getEtutoriumId() {
        return etutoriumId;
    }

    public void setEtutoriumId(Long etutoriumId) {
        this.etutoriumId = etutoriumId;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Type.class)
    @JoinColumn(name = "typeid", nullable = false)
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Lecture.class)
    @JoinTable(name = "events_lectures",
            joinColumns = @JoinColumn(name = "eventid", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "lectureid", referencedColumnName = "id"))
    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Tag.class)
    @JoinTable(name = "events_tags",
            joinColumns = @JoinColumn(name = "eventid", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tagid", referencedColumnName = "id"))
    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, targetEntity = Participant.class)
    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Tariff.class)
    @JoinTable(name = "events_tariff",
            joinColumns = @JoinColumn(name = "eventid", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tariffid", referencedColumnName = "id"))
    public List<Tariff> getTariffs() {
        return tariffs;
    }

    public void setTariffs(List<Tariff> tariffs) {
        this.tariffs = tariffs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        return getId().equals(event.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
