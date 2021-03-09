package py.com.spa.app.controller;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

import py.com.spa.app.entities.Categorias;
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
	public ResponseEntity<?>  agregarUsuario(@RequestBody Usuario Usuario) {
		 Usuario usuario = null;
		 Map<String, Object> response = new HashMap<>();
		 try {
			 usuario = UsuarioService.addUsuario(Usuario);
		 }catch(DataAccessException e ){
				response.put("mensaje",  "Error al realizar el insert en la bd");
				response.put("error",  e.getMessage().concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario ha sido creado con exito.");
		response.put("usuario", usuario);
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);		
	}
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificarUsuario (@PathVariable(value="id") Integer id, @RequestBody Usuario UsuarioActual) {
	    Usuario c = UsuarioService.findUsuarioById(id);
		Usuario nuevo =  null;
		Map<String, Object> response = new HashMap<>();
	    
		if (c == null) {
			response.put("mensaje",  "Error, No se pudo editar. La categoria no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			c.setNombre( UsuarioActual.getNombre());
			c.setUsername(UsuarioActual.getUsername());
			c.setPassword(UsuarioActual.getPassword());
			c.setApellido( UsuarioActual.getApellido());
			c.setCorreo(UsuarioActual.getCorreo());
			c.setCedula(UsuarioActual.getCedula());
			c.setTelefono( UsuarioActual.getTelefono());
			c.setSexo(UsuarioActual.getSexo());
			c.setEstado(UsuarioActual.getEstado());
			nuevo = UsuarioService.updateUsuario(c);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario ha sido actualizado.");
		response.put("usuario", nuevo);
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);	
	}  
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?>  eliminarUsuario(@PathVariable(value="id") Integer id) {
		Map<String, Object> response = new HashMap<>();
		Usuario c = UsuarioService.findUsuarioById(id);
		
		if (c == null) {
			response.put("mensaje",  "Error, No se pudo eliminar. La categoria no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			 UsuarioService.deleteUsuario(id);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	
	}


}
