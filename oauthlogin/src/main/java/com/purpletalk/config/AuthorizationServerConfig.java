package com.purpletalk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

//Authorization Server Config responsible for generating tokens specific to a clients.

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	/**
	 * This class extends AuthorizationServerConfigurerAdapter and 
	 * is responsible for generating tokens specific to a client.
	 * 
	 * @EnableAuthorizationServer: Enables an authorization server.
	 * AuthorizationServerEndpointsConfigurer defines the authorization and token end points and the token services.
	 */

	@Autowired
	TokenStore tokenStore;
	
	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
	@Autowired
    private UserApprovalHandler userApprovalHandler;
	
	/*@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("test");
        return converter;
	}*/

   /* @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }*/


	/*@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager);
	}*/
	@Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
                .authenticationManager(authenticationManager);
    }
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("test_client").secret("test_secret").authorities("ROLE_ADMIN","ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
		.authorizedGrantTypes("password","authorization_code","refresh_token","client_credentials","implicit")
		.scopes("read","write","trust").accessTokenValiditySeconds(120).refreshTokenValiditySeconds(120);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.allowFormAuthenticationForClients();
	}

	

}
