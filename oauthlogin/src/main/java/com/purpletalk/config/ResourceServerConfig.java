package com.purpletalk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 * To Access the Some of the Resources (eg: CRUD) client must be authenticated.
 * 
 * whenever an user tries to access these resources, the user will be asked to provide his authenticity and 
 * once the user is authorized then he will be allowed to access these protected resources.
 * 
 * @EnableResourceServer: Enables a resource server
 *
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		/*http.anonymous().disable().authorizeRequests().antMatchers("/users/**").authenticated().and()
		.exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());*/
		http.anonymous().disable().authorizeRequests().antMatchers("/users/**").access("hasRole('ADMIN')").and()
		.exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.stateless(false);
	}

}
