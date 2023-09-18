package br.com.dh.clinica.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private Environment environment;

    @Autowired
    private JwtTokenStore tokenStore;

    private static final String[] PUBLIC = { "/oauth/token", "/h2/**" };
    private static final String[] OPERATOR_OR_ADMIN = { "/dentistas/**", "/enderecos/**", "/pacientes/**" };
    private static final String[] ADMIN = { "/users/**", "/roles/**" };

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception{
        resources.tokenStore(tokenStore);
    }

    @Override // configurações das rotas
    public void configure(HttpSecurity httpSecurity) throws Exception{

        if (Arrays.asList(environment.getActiveProfiles()).contains("test")){
            httpSecurity.headers().frameOptions().disable();
        }

        httpSecurity.authorizeRequests().antMatchers(PUBLIC).permitAll()
                .antMatchers(HttpMethod.GET, OPERATOR_OR_ADMIN).permitAll()
                .antMatchers(OPERATOR_OR_ADMIN).hasAnyRole("OPERATOR", "ADMIN")
                .antMatchers(ADMIN).hasAnyRole("ADMIN")
                .anyRequest().authenticated();
    }
}
