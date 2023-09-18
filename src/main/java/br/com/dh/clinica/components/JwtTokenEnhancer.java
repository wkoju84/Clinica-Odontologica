package br.com.dh.clinica.components;

import br.com.dh.clinica.entities.Usuario;
import br.com.dh.clinica.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component // Para customizar o response da autenticação
public class JwtTokenEnhancer implements TokenEnhancer {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

        Usuario usuario = usuarioRepository.buscarPorEmail(oAuth2Authentication.getName());

        Map<String, Object> map = new HashMap<>();
        map.put("usuarioPrimeiroNome", usuario.getPrimeroNome());
        map.put("usuarioUltimoNome", usuario.getUltimoNome());
        map.put("usuarioId", usuario.getId());

        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) oAuth2AccessToken;
        token.setAdditionalInformation(map);
        return oAuth2AccessToken;
    }
}
