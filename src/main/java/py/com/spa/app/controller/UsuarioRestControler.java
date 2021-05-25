package py.com.spa.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import py.com.spa.app.entities.Rol;
import py.com.spa.app.entities.Usuario;
import py.com.spa.app.entities.Ventas;
import py.com.spa.app.services.UsuariosService;
import py.com.spa.app.validators.ApiUnprocessableEntity;
import py.com.spa.app.validators.UsuarioValidatorImpl;


@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
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
		//return ResponseEntity.ok(Boolean.TRUE);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	

	@GetMapping("/encontrar/{id}")
	public Usuario obtenerusuariosId(@PathVariable(value = "id") Integer id) {
		return (Usuario) usuarioService.findByUsuarioId(id);
	}

	@PutMapping("/modificar/{id}")
	public ResponseEntity<Void> modificarReserva(@PathVariable(value = "id") Integer usuarioId,
			@RequestBody Usuario usuario) {
		Usuario us = null;

		us = usuarioService.findByUsuarioId(usuarioId);

		if (us != null) {
			System.out.println(us.getEmail());
			System.out.println(usuario.getEmail());
			us.setApellido(usuario.getApellido());
			us.setEmail(usuario.getEmail());
			//us.setEnabled(usuario.getEnabled());
			//us.setUsuarioId(us.getUsuarioId());
			us.setNombre(usuario.getNombre());
			//us.setPassword(us.getPassword());
			//us.setRoles(usuario.getRoles());
			us.setUsername(usuario.getUsername());
			us.setRuc(usuario.getRuc());
			us.setSexo(usuario.getSexo());
			us.setTelefono(usuario.getTelefono());
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
	
	@PostMapping("/asignar-rol/{usuarioId}/{rolId}")
	public void asignarRol(@PathVariable Integer usuarioId, @PathVariable Integer rolId){
		
		usuarioService.insertUsuariosRoles(usuarioId, rolId);
	}
	
	@GetMapping("/listar-roles")
	public List<Rol> listarRoles(){
		
		return usuarioService.obtenerRoles();
		
	}

}
