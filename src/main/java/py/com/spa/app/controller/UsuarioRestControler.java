package py.com.spa.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.com.spa.app.entities.Usuario;
import py.com.spa.app.services.UsuariosService;
import py.com.spa.app.validators.ApiUnprocessableEntity;
import py.com.spa.app.validators.UsuarioValidatorImpl;


@RestController
@RequestMapping("/usuarios")
public class UsuarioRestControler {

	@Autowired
	public UsuariosService usuarioService;
	
	@Autowired
	private UsuarioValidatorImpl validatorImpl;

	@GetMapping("/listar")
	public List<Usuario> listarusuarios() {
		return usuarioService.findAll();
	}

	@PostMapping("/agregar")
	public ResponseEntity<?> agregarUsuario(@RequestBody Usuario usuario) throws ApiUnprocessableEntity  {
		
		Logger log = LoggerFactory.getLogger(UsuarioRestControler.class);
		try {
			validatorImpl.validator(usuario);
		} catch (ApiUnprocessableEntity e) {
			log.error(e.getMessage() + " controler");
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
			
		}
		usuarioService.addUsuario(usuario);
		
		return ResponseEntity.ok(Boolean.TRUE);
	}
	

	@GetMapping("/encontrar/{id}")
	public Usuario obtenerusuariosId(@PathVariable(value = "id") Integer id) {
		return (Usuario) usuarioService.findByUsuarioId(id);
	}

	@PutMapping("/usuarios/modificar/{id}")
	public ResponseEntity<Void> modificarReserva(@PathVariable(value = "id") Integer usuarioId,
			@RequestBody Usuario usuario) {
		Usuario us = null;

		us = usuarioService.findByUsuarioId(usuarioId);

		if (us != null) {
			us.setApellido(usuario.getApellido());
			us.setEmail(us.getEmail());
			us.setEnabled(us.getEnabled());
			us.setId(us.getId());
			us.setNombre(us.getNombre());
			us.setPassword(us.getPassword());
			us.setRoles(us.getRoles());
			us.setUsername(us.getUsername());
			us.setRuc(us.getRuc());
			us.setSexo(us.getSexo());
			us.setTelefono(us.getTelefono());
			usuarioService.updateUsuario(us);
			return new ResponseEntity<Void>(HttpStatus.OK);

		} else {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarusuario(@PathVariable(value = "id") Integer id) {
		Usuario c = usuarioService.findByUsuarioId(id);
		if (c != null) {
			usuarioService.deleteUsuario(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

	}

}
