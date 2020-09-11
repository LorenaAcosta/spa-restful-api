package py.com.spa.app.controller;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RestController;

import py.com.spa.app.entities.Usuario;
import py.com.spa.app.services.UsuarioService;

//@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UsuarioRESTController {

	@Autowired
	private UsuarioService UsuarioService;
	
	@GetMapping("/resource")
	  public Map<String,Object> home() {
	    Map<String,Object> model = new HashMap<String,Object>();
	    model.put("id", UUID.randomUUID().toString());
	    model.put("content", "Hello World");
	    return model;
	  }

	
	@GetMapping("/user")
	  public Principal user(Principal user) {
	    return user;
	  } 

	
	@GetMapping("/listar")
	public List<Usuario> listarUsuario() {
		List<Usuario> Usuario = UsuarioService.findAll();
		return Usuario;
	}
	
	@GetMapping("/encontrar/{id}")
	public Usuario obtenerUsuarioId(@PathVariable(value="id") Integer id) {
		return (Usuario) UsuarioService.findUsuarioById(id);
	}
	
	
	@PostMapping("/agregar")
	public ResponseEntity<Void> agregarUsuario(@RequestBody Usuario Usuario) {
		 UsuarioService.addUsuario(Usuario);
		 return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<Void> modificarUsuario (@PathVariable(value="id") Integer id, @RequestBody Usuario UsuarioActual) {
	
	    Usuario c = UsuarioService.findUsuarioById(id);
		if(c!=null) {
			c.setNombre( UsuarioActual.getNombre());
			c.setUsername(UsuarioActual.getUsername());
			c.setPassword(UsuarioActual.getPassword());
			c.setApellido( UsuarioActual.getApellido());
			c.setCorreo(UsuarioActual.getCorreo());
			c.setCedula(UsuarioActual.getCedula());
			c.setTelefono( UsuarioActual.getTelefono());
			c.setSexo(UsuarioActual.getSexo());
			c.setEstado(UsuarioActual.getEstado());
			UsuarioService.updateUsuario(c);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else{
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}  
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Void> eliminarUsuario(@PathVariable(value="id") Integer id) {
		Usuario c = UsuarioService.findUsuarioById(id);
		if (c!=null) {
			UsuarioService.deleteUsuario(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
		
	}


}
