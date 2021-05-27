package py.com.spa.app.validators;

import org.springframework.stereotype.Service;

import py.com.spa.app.entities.Usuario;
/**
 * Interface de validacion de datos para Usuarios
 * @author PC
 *
 */
@Service
public interface UsuarioValidator {
	
	void validator(Usuario usuario) throws ApiUnprocessableEntity;
	
}
