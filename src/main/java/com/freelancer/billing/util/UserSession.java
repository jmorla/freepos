package com.freelancer.billing.util;

import com.freelancer.billing.domain.Company;
import com.freelancer.billing.domain.Permission;
import com.freelancer.billing.domain.Role;
import com.freelancer.billing.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Set;

/**
 * @author Jorge L. Morla
 * @version 1.0
 *
 * The UserSession Class
 * Manage all the user information when its logs in successfully
 * */
@Component
@ManagedBean
@SessionScoped
public class UserSession {

    /**
     * The user logged in Information
     * */
    private User userLoggedIn;

    /**
     * The User Role
     * */
    private Role role;

    /**
     * The User Permissions
     * */
    private Set<Permission> permissions;

    /**
     * The Company its belongs
     * */
    private Company company;


    public User getUserLoggedIn() {
        return userLoggedIn;
    }

    public void setUserLoggedIn(User userLoggedIn) {
        this.userLoggedIn = userLoggedIn;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userLoggedIn=" + userLoggedIn +
                ", role=" + role +
                ", permissions=" + permissions +
                ", company=" + company +
                '}';
    }
}
