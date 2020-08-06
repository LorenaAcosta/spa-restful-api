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

import py.com.spa.app.entities.Clientes;
import py.com.spa.app.services.ClienteService;

//@CrossOrigin
@RestController
@RequestMapping("/cliente")
public class ClienteRESTController {

	@Autowired
	private ClienteService clienteService;
	
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
	public List<Clientes> listarClientes() {
		List<Clientes> clientes = clienteService.findAll();
		return clientes;
	}
	
	@GetMapping("/encontrar/{id}")
	public Clientes obtenerClienteId(@PathVariable(value="id") Integer id) {
		return (Clientes) clienteService.findClienteById(id);
	}
	
	
	@PostMapping("/agregar")
	public ResponseEntity<Void> agregarCliente(@RequestBody Clientes cliente) {
		 clienteService.addCliente(cliente);
		 return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<Void> modificarCliente (@PathVariable(value="id") Integer id, @RequestBody Clientes clienteActual) {
	
	    Clientes c = clienteService.findClienteById(id);
		if(c!=null) {
			c.setNombre( clienteActual.getNombre());
			c.setUsername(clienteActual.getUsername());
			c.setPassword(clienteActual.getPassword());
			c.setApellido( clienteActual.getApellido());
			c.setCorreo(clienteActual.getCorreo());
			c.setRuc(clienteActual.getRuc());
			c.setTelefono( clienteActual.getTelefono());
			c.setSexo(clienteActual.getSexo());
			clienteService.updateCliente(c);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else{
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}  
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Void> eliminarCliente(@PathVariable(value="id") Integer id) {
		Clientes c = clienteService.findClienteById(id);
		if (c!=null) {
			clienteService.deleteCliente(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	
	



}
