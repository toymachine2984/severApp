package com.project.server.resourceServer.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Access(value = AccessType.PROPERTY)
@Table(schema = "system", name = "tariffs", catalog = "tables")
public class Tariff {

    private Long id;
    private String name;
    private int discount;
    private double cost;
    private Date endDate;
    private List<Order> orders;

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

    @Column(name = "discount")
    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Column(name = "cost")
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Column(name = "enddate")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @OneToMany(mappedBy = "tariff",targetEntity = Order.class,fetch = FetchType.LAZY)
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tariff tariff = (Tariff) o;

        return id.equals(tariff.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
