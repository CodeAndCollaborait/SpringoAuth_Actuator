package com.auth.api.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTLogInFilter extends AbstractAuthenticationProcessingFilter {
  
  
  public JWTLogInFilter(String url, AuthenticationManager authenticationManager) {
	super(new AntPathRequestMatcher(url));
	setAuthenticationManager(authenticationManager);
  }
  
 
  
  
  @Override
  public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
		  throws AuthenticationException, IOException, ServletException {
	return null;
  }
  
  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain
		  , Authentication authResult) throws IOException, ServletException {
	super.successfulAuthentication(request, response, chain, authResult);
  }
  
  
}
