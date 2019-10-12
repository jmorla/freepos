package com.freelancer.billing.service.impl;

import com.freelancer.billing.domain.Permission;
import com.freelancer.billing.domain.Role;
import com.freelancer.billing.domain.User;
import com.freelancer.billing.repository.PermissionRepository;
import com.freelancer.billing.repository.RoleRepository;
import com.freelancer.billing.repository.UserRepository;
import com.freelancer.billing.util.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The UserDetailServiceImpl Class another way to get user information :/
 *
 * @author Jorge L. Morla
 * @version 1.0
 * */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsService.class);
    /**
     * The User Repository
     * */
    @Autowired
    private UserRepository userRepository;

    /**
     * The Role Repository
     * */
    @Autowired
    private RoleRepository roleRepository;

    /**
     * The Permission Repository
     * */
    @Autowired
    private PermissionRepository permissionRepository;

    /**
     * The user Session
     * */
    @Autowired
    private UserSession userSession;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(s);


        if(!user.isPresent())
            throw new UsernameNotFoundException(s);

        LOGGER.info("user : "+user.get());

        Role role = roleRepository.findByUsersId(user.get().getId());

        List<Permission> permissions = permissionRepository.findByRoles(role);


        // adding user information to the session
        userSession.setUserLoggedIn(user.get());
        userSession.setRole(role);
        userSession.setPermissions(permissions.stream().collect(Collectors.toSet()));


        return mapToUser(user.get(), permissions);
    }

    private org.springframework.security.core.userdetails.User mapToUser(User user, List<Permission> permissions){

        Collection<GrantedAuthority> authorities = permissions.stream()
                .map(e -> new SimpleGrantedAuthority(e.getName())).collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPasswordHash(), user.isEnabled(),user.isAccountNonExpired(),
                user.isPasswordNonExpired(),true, authorities);
    }
}
