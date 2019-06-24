package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Value("${spring.security.user.name}")
	private String username;
	@Value("${spring.security.user.password}")
	private String pwd;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser(username).password("{noop}"+pwd).roles("ADMIN");		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.httpBasic()
		.and()
		.authorizeRequests()		
		//.antMatchers("/").permitAll()
		//.anyRequest().permitAll()
		.anyRequest().authenticated()
		.antMatchers(HttpMethod.GET).hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.POST).hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.DELETE).hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.PATCH).hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.PUT).hasAnyRole("ADMIN")
		.and()		
		.csrf().disable()
		.formLogin().disable()
		;
	}

	

}
