package com.project.server.resourceServer.entity;

import javax.persistence.*;

@Entity
@Access(value = AccessType.PROPERTY)
@Table(schema = "system", name = "statuses", catalog = "tables")
public class Status {

    private Long id;
    private String value;
    private String description;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Status status = (Status) o;

        return id.equals(status.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
