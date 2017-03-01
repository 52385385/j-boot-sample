package provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import provider.user.UserDetailsServiceImpl;
@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	UserDetailsServiceImpl uds;
	@Autowired
	AuthenticationManager am;
	
	@Value("${spring.redis.host}")
	private String rhost;
	@Value("${spring.redis.password}")
	private String rpass;
	@Value("${spring.redis.port}")
	private int rport;
	
	@Bean
	public RedisConnectionFactory redisConnFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(rhost);
		factory.setPassword(rpass);
		factory.setPort(rport);
		return factory;
	}
	@Bean
	public TokenStore tokenStore() {
		return new RedisTokenStore(redisConnFactory());
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient("browser") //untrusted client
			.authorizedGrantTypes("password", "refresh_token")
			.scopes("read")
		.and()
			.withClient("service") //trusted client
			.secret("123456")
			.authorizedGrantTypes("client_credentials", "refresh_token")
			.scopes("server");			
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
			.tokenStore(tokenStore())
			.userDetailsService(uds)
			.authenticationManager(am);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security
			.tokenKeyAccess("permitAll()")
			.checkTokenAccess("isAuthenticated()");
	}
}
