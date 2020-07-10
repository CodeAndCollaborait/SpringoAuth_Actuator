package com.auth.api.config;

import com.auth.api.filter.JWTAuthFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
  }
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
	http.csrf().disable().authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers(HttpMethod.GET, "/login").permitAll()
			.antMatchers(HttpMethod.POST, "/login").permitAll()
			//need authentication
			.anyRequest().authenticated()
			.and()
			//Add the filter to get Token and authenticate
			.addFilterBefore(new JWTAuthFilter("/login", authenticationManager()),
					UsernamePasswordAuthenticationFilter.class);
	
	
  }
}
