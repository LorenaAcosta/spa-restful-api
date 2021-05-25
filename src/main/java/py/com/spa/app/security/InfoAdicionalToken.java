package py.com.spa.app.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import py.com.spa.app.entities.Usuario;
import py.com.spa.app.services.IUsuarioService;


@Component
public class InfoAdicionalToken implements TokenEnhancer {

	@Autowired
	private IUsuarioService usuarioService;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<String, Object>();
		
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		info.put("usuarioId", usuario.getUsuarioId());
		info.put("nombre", usuario.getNombre());
		info.put("apellido", usuario.getApellido());
		info.put("correo", usuario.getEmail());
		info.put("roles", usuario.getRoles());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
