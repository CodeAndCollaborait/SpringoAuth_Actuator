package com.auth.api.config;

import com.auth.api.filter.JWTLogInFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	return bCryptPasswordEncoder;
  }
  
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
	String password = "123";
	String encryptedPassword = this.passwordEncoder().encode(password);
	System.out.printf("Encoded Password for 123" + encryptedPassword);
	
	InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> manageCofig = auth.inMemoryAuthentication();
	
	UserDetails userOne = User.withUsername("tom").password(encryptedPassword).roles("USER").build();
	UserDetails userTwo = User.withUsername("jerry").password(encryptedPassword).roles("USER").build();
	
	manageCofig.withUser(userOne);
	manageCofig.withUser(userTwo);
	
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
			.addFilterBefore(new JWTLogInFilter("/login", authenticationManager()),
					UsernamePasswordAuthenticationFilter.class);
	
	
  }
}
