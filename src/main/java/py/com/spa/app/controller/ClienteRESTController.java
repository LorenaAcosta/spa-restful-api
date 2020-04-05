package py.com.spa.app.controller;
import java.util.List;

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

import py.com.spa.app.entities.Clientes;
import py.com.spa.app.services.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteRESTController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/listar")
	public List<Clientes> listarClientes() {
		List<Clientes> clientes = clienteService.findAll();
		return clientes;
	}
	
	@PostMapping("/encontrar-cliente")
	public ResponseEntity<?> encontrarCliente (@RequestBody Clientes cliente){
		Clientes c =  clienteService.findClienteById(cliente.getClienteId());
		if (c !=null) {
			return new ResponseEntity<>(c, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
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
			c.setApellido( clienteActual.getApellido());
			c.setTelefono( clienteActual.getTelefono());
			c.setCorreo( clienteActual.getCorreo());
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
