package com.milestone.milestone.web.configuration.security;

import com.milestone.milestone.web.component.user.UserRegistryComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserRegistryComponent userRegistryComponent;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //TODO: Remove after test
        http.authorizeRequests().anyRequest().authenticated().and().oauth2Login()
                .and().oauth2Client();

        /*http.cors().and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests().anyRequest().authenticated().and()
                .oauth2Client().and()
                .oauth2Login()*//*.userInfoEndpoint().userService(userRegistryComponent)*//*;*/


    }
}
