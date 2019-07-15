package com.project.server.resourceServer.entity;


import javax.persistence.*;

@Entity
@Access(value = AccessType.PROPERTY)
@Table(schema = "system", name = "participants", catalog = "tables")
public class Participant {

    private Long id;
    private String name;
    private Order order;
    private Event event;
    private Address address;
    private Status status;
    private String type;


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

//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "participant", targetEntity = Order.class)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderid")
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Event.class)
    @JoinColumn(name = "eventid", nullable = false)
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Address.class)
    @JoinColumn(name = "addresid", nullable = false)
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Status.class)
    @JoinColumn(name = "statusid", nullable = false)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Participant that = (Participant) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
