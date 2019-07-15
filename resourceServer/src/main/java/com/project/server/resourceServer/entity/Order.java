package com.project.server.resourceServer.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Access(value = AccessType.PROPERTY)
@Table(schema = "system", name = "orders", catalog = "tables")
public class Order {

    private Long id;
    private Double payment;
    private Status status;
    private Tariff tariff;
    private Date expiredDate;
    private Participant participant;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "payment")
    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    @ManyToOne(targetEntity = Status.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "statusid", nullable = false)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @ManyToOne(targetEntity = Tariff.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tariffid", nullable = false)
    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    @Column(name = "expireddate")
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Participant.class, cascade = CascadeType.ALL, mappedBy = "order")
    @JoinColumn(name = "participantid")
    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
