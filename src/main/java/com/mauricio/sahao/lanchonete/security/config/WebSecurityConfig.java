package com.mauricio.sahao.lanchonete.security.config;

import com.mauricio.sahao.lanchonete.security.filter.AutenticacaoFilter;
import com.mauricio.sahao.lanchonete.security.filter.JWTLoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String URL_LOGIN = "/login";
	private static final String USUARIO = "admin";
	private static final String SENHA = "finch";
	private static final String NO_ENCODE_SENHA = "{noop}";
	private static final String ROLES_ADMIN = "ADMIN";

	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.POST, URL_LOGIN).permitAll()
			.anyRequest().authenticated().and()
			.addFilterBefore(new JWTLoginFilter(URL_LOGIN, authenticationManager()), UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(new AutenticacaoFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs",
				"/configuration/ui",
				"/swagger-resources",
				"/configuration/security",
				"/swagger-ui.html",
				"/webjars/**");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser(USUARIO).password(NO_ENCODE_SENHA.concat(SENHA)).roles(ROLES_ADMIN);
	}
}