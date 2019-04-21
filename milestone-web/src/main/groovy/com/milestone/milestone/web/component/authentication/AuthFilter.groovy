package com.milestone.milestone.web.component.authentication

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

class AuthFilter extends GenericFilterBean {
    @Override
    void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        SecurityContextHolder.getContext().getAuthentication()


        chain.doFilter(request, response)
    }
}
