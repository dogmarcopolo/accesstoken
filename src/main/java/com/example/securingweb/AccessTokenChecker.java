package com.example.securingweb;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

// filter is only one approach, you can also extends 
// AbstractPreAuthenticatedProcessingFilter and maybe PreAuthenticatedAuthenticationProvider
// if so preferred

@Component
@Order(0)
public class AccessTokenChecker implements Filter {
 
    @Override
    public void doFilter(
      ServletRequest request, 
      ServletResponse response, 
      FilterChain chain) throws IOException, ServletException {
 
       // HttpServletRequest req = (HttpServletRequest) request;
 
    	if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
    	   // user hasn't login yet
    	   // TODO
    	   // get the access token from http request and get user and group information from Microsoft Graph
    	
    	   // now set security context accordingly
    	   SecurityContext context = SecurityContextHolder.createEmptyContext(); 
    	   // just for testing, suggest use 
    	   // PreAuthenticatedAuthenticationToken or RememberMeAuthenticationToken instead
    	   // or OpenIDAuthenticationToken if you really insist
    	   Authentication authentication =
    			   new TestingAuthenticationToken("username", "password", "ROLE_USER"); 
    	   context.setAuthentication(authentication);
    	   SecurityContextHolder.setContext(context); 
    	}
    	chain.doFilter(request, response);
    }
}