package com.auth.api.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthFilter extends AbstractAuthenticationProcessingFilter {
  
  
  public JWTAuthFilter(String url, AuthenticationManager authenticationManager) {
	super(new AntPathRequestMatcher(url));
	setAuthenticationManager(authenticationManager);
  }
  
 
  
  
  @Override
  public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
											  HttpServletResponse httpServletResponse) throws AuthenticationException,
		  IOException, ServletException {
	return null;
  }
  
  
}
