package org.ares.app.o2.cfg;

import javax.sql.DataSource;

import org.ares.app.o2.security.oauth2.spring.UcJdbcTokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/*
 * @Autowired RedisConnectionFactory redisConnectionFactory; private TokenStore
 * getRedisTokenStore() { return new RedisTokenStore(redisConnectionFactory); }
 */

@Configuration
@EnableAuthorizationServer
public class AuthServerCfg extends AuthorizationServerConfigurerAdapter {

	@Bean
	public TokenStore jdbcTokenStore() {
		return new JdbcTokenStore(dataSource);
	}

	/*@Bean*/
	public TokenStore ucJdbcTokenStore() {
		return new UcJdbcTokenStore(dataSource);
	}

	@Bean // 声明 ClientDetails实现
	public ClientDetailsService jdbcClientDetailsService() {
		return new JdbcClientDetailsService(dataSource);
	}
	
	@Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 设置OAuth2的client信息也使用数据库存储和读取
        clients.jdbc(dataSource);
    }

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		/*
		 * endpoints.tokenStore(new RedisTokenStore(redisConnectionFactory))
		 * .authenticationManager(authenticationManager); // redis保存token
		 */
		endpoints.tokenStore(jdbcTokenStore) // 数据库保存token
				.authenticationManager(authenticationManager)
				.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
	}

	@Override // 允许表单认证
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.allowFormAuthenticationForClients();
	}

	final String UC_RESOURCE_ID = "*";
	@Autowired
	private DataSource dataSource;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	TokenStore jdbcTokenStore;
	@Autowired
	ClientDetailsService jdbcClientDetailsService;

}
