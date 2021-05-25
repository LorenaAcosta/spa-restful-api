package py.com.spa.app.services;

import py.com.spa.app.entities.Usuario;

public interface IUsuarioService {
	public Usuario findByUsername(String username);
}
