package com.milestone.milestone.web.configuration.authentication;

import com.milestone.milestone.web.component.authentication.AuthFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated().and().oauth2Login()
                .and().oauth2Client();

        http.addFilterAfter(new AuthFilter(), BasicAuthenticationFilter.class);
    }
}
