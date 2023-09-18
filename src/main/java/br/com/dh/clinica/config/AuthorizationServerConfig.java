package br.com.dh.clinica.config;

import br.com.dh.clinica.components.JwtTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Configuration
@EnableAuthorizationServer //Para utilizar o Oauth2
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    @Autowired
    private JwtTokenStore jwtTokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenEnhancer tokenEnhancer;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception{
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
        clients.inMemory()
                .withClient("dhclinica")
                .secret(passwordEncoder.encode("dh2023"))
                .scopes("read", "write")
                .authorizedGrantTypes("senha")
                .accessTokenValiditySeconds(1800); //60 * 30 = 30 minutos
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception{

        TokenEnhancerChain chain = new TokenEnhancerChain();
        chain.setTokenEnhancers(Arrays.asList(accessTokenConverter, tokenEnhancer));

        endpoints.authenticationManager(authenticationManager)
                .tokenStore(jwtTokenStore)
                .accessTokenConverter(accessTokenConverter)
                .tokenEnhancer(chain);
    }
}
