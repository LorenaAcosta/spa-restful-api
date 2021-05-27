package py.com.spa.app.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import py.com.spa.app.dao.UsuarioDao;
import py.com.spa.app.entities.Usuario;

@Component
public class UsuarioValidatorImpl implements UsuarioValidator{

	@Autowired
	private UsuarioDao usuarioDao;
	private Logger log = LoggerFactory.getLogger(UsuarioValidator.class);
	Pattern pattern = Pattern
			.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	
	@Override
	public void validator(Usuario usuario) throws ApiUnprocessableEntity  {
		if (usuario.getUsername() == null || usuario.getUsername().isEmpty()) {
			//log.error("El nombre es obligatorio");
			message("El nombre es obligatorio");
		}
		if (usuario.getUsername().length() < 3) {
			//log.error("El nombre es muy corto, se requieren al menos 3 caracteres");
			message("El nombre es muy corto, se requieren al menos 3 caracteres");
		}
		if (usuarioDao.findByUsername(usuario.getUsername()) != null) {
			message("El nombre de usuario ya existe");
		}
		//email
		if (usuarioDao.findByEmail(usuario.getEmail()) != null) {
			message("El correo ya se encuentra registrado");
		}
		Matcher mather = pattern.matcher(usuario.getEmail());
		if (mather.find() != true) {
			message("El correo ingresado es inválido.");
		}
		
		//password
		if (usuario.getPassword() == null || usuario.getPassword().isEmpty() || usuario.getPassword().length() < 3) {
			//log.error("La contraseña es obligatoria y debe tener al menos 3 caracteres");
			message("La contraseña es obligatoria y debe tener al menos 3 caracteres");
		}
	}
	
	private void message(String msg) throws ApiUnprocessableEntity{
		ApiUnprocessableEntity a = new ApiUnprocessableEntity(msg);
		throw a;
		
	}


}
