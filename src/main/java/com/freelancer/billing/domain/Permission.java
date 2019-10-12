package com.freelancer.billing.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PERMISSIONS")
public class Permission {

    @Id
    @Column(name = "PERMISSION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PERMISSION_NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles = new ArrayList<>();

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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
