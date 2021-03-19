package com.github.grusnac.taco.cloud.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .fullyAuthenticated()
                .and()
                .formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authentication) throws Exception {
//        authentication.inMemoryAuthentication()
//                .withUser("buzz")
//                .password("{noop}infinity")
//                .authorities("ROLE_USER")
//                .and()
//                .withUser("woody")
//                .password("{noop}bullseye")
//                .authorities("ROLE_USER");

//        JDBC Authentication
//        authentication.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select username, password, enabled from Users where username = ?")
//                .authoritiesByUsernameQuery("select username, authority from UserAuthorities where username = ?")
//                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());

//        LDAP Authentication
        authentication
                .ldapAuthentication()
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups")
                .contextSource()
                .url("ldap://localhost:8389/dc=tacocloud,dc=org")
                .and()
                .passwordCompare()
//                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder())
                .passwordAttribute("userPassword");
    }
}
