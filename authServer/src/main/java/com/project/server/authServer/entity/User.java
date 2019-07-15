package com.project.server.authServer.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.server.authServer.util.validators.FieldMatch;
import com.project.server.authServer.util.validators.Password;

import org.hibernate.envers.NotAudited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Access(AccessType.PROPERTY)
@EntityListeners({AuditingEntityListener.class})
//@Audited
@Table(name = "user", schema = "public", catalog = "tables")
@FieldMatch(first = "password", second = "matchingPassword", message = "{validation.user.create.field.matchingPassword}")
// extends Audit
public class User implements Serializable {

    private Long id;
    private String login;
    private String password;

    @JsonBackReference
    private Set<Role> roles = new HashSet<>();
    private String firstName;
    private String lastName;
    private String matchingPassword;
    private boolean locked;
    private boolean expired;


    public User() {
        this.expired = true;
        this.locked = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    @Column(name = "login", unique = true, nullable = false)
    @NotNull
    @NotEmpty(message = "{validation.user.create.field.required}")
    @Email(message = "{validation.user.create.field.email}")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @NotNull
    @NotEmpty(message = "{validation.user.create.field.required}")
    @Size(min = 8, message = "{validation.user.create.field.min}")
    @Password(message = "{validation.user.create.field.password}")
    @Column(name = "password", nullable = false, unique = true)
    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Use NotAudited on fields when you don't want the value / relationship to be audited at all.
     * I believe you can use this on a field with or without a relationship such as OneToMany, ManyToMany, or just Column.
     * Use RelationTargetAuditMode.NOT_AUDITED on a relationship field if you want the value to be audited, but not the entity on the other side of the relationship.
     * For example you want the ID / key value audited, but not the related table.
     */

    @ManyToMany(fetch = FetchType.EAGER)
//    @Audited(targetAuditMode = NOT_AUDITED)
    @NotAudited
    @JoinTable(name = "userrole", joinColumns = {
            @JoinColumn(name = "userid", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "roleid",
                    nullable = false, updatable = false)})
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @NotNull
    @NotEmpty(message = "{validation.user.create.field.required}")
    @Column(name = "firstname")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @Column(name = "lastname")
    @NotEmpty(message = "{validation.user.create.field.required}")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotEmpty(message = "{validation.user.create.field.required}")
    @Transient
    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }


    @Column(name = "locked", nullable = false)
    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }


    @Column(name = "expired", nullable = false)
    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", matchingPassword='" + matchingPassword + '\'' +
                ", locked=" + locked +
                ", expired=" + expired +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getLogin(), user.getLogin()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getRoles(), user.getRoles());
    }

}
