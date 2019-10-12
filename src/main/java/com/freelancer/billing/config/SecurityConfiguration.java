package com.freelancer.billing.config;

import com.freelancer.billing.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/javax.faces.resource/**")
                .permitAll().anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login.xhtml").permitAll()
//              .successHandler(authenticationSuccessHandler())
                .defaultSuccessUrl("/dashboard/")
                .failureUrl("/login.xhtml?error=true")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .and()
                .logout()
                .logoutSuccessUrl("/signIn/?success");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder()
                        .encode("admin"))
                .roles("ADMIN");
//        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
}
