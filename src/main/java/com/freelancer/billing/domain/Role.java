package com.freelancer.billing.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Jorge L. Morla
 * @version 1.0
 * @since 1.0
 *
 * Role Entity maps ROLES table
 * */
@Entity
@Table(name = "ROLES")
public class Role {

    @Id
    @Column(name = "ROLE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ROLE_NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "ROLE_PERMISSION",
            joinColumns = @JoinColumn(name = "ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PERMISSION_ID")
    )
    private List<Permission> permissions = new ArrayList<>();

    @OneToMany(mappedBy = "role")
    private Set<User> users = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
