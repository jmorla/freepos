package com.freelancer.billing.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD_HASH")
    private String passwordHash;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ENABLED")
    private boolean enabled;

    @Column(name = "ACCOUNT_NO_EXPIRED")
    private boolean accountNonExpired;

    @Column(name = "PASSWORD_NO_EXPIRED")
    private boolean passwordNonExpired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isPasswordNonExpired() {
        return passwordNonExpired;
    }

    public void setPasswordNonExpired(boolean passwordNonExpired) {
        this.passwordNonExpired = passwordNonExpired;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", accountExpired=" + accountNonExpired +
                ", passwordExpired=" + passwordNonExpired +
                '}';
    }
}
