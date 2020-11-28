package org.sid.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		auth.inMemoryAuthentication()
		.withUser("admin").password("{noop}1523").roles("ADMIN", "USER")
		.and()
		.withUser("user").password("{noop}1234").roles("USER");*/
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override 	  
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//http.formLogin();
		http.authorizeRequests().antMatchers("/login/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/users/**").hasAuthority("ADMIN");
		
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/rapports/**").hasAuthority("USER");
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/rapports/**").hasAuthority("USER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT,"/rapports/**").hasAuthority("USER");	
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/postFile/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/rapport/**").permitAll();
		
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/theses/**").hasAuthority("USER");
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/theses/**").hasAuthority("USER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT,"/theses/**").hasAuthority("ADMIN");
		
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/theseSoutenus/**").hasAuthority("USER");
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/theseSoutenus/**").hasAuthority("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT,"/theseSoutenus/**").hasAuthority("ADMIN");
		
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
		http.addFilterBefore(new JWTAuthorisationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
