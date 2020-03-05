package py.com.spa.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@PostMapping("/agregar")
	public void agregarCliente(@RequestBody Clientes cliente) {
		clienteService.addCliente(cliente);
	}
	
	@GetMapping("/encontrar/{id}")
	public Clientes obtenerCliente (@PathVariable(value="id") Integer id) {
		return (Clientes) clienteService.findByClientesById(id);
	}
	
	@PutMapping("/modificar/{id}")
	public void modificarCliente (@PathVariable(value="id") Integer id, @RequestBody Clientes cliente) {
		Clientes c = clienteService.findByClientesById(id);
		if(c!=null) {
			c.setNombre(cliente.getNombre());
			c.setApellido(cliente.getApellido());
			c.setTelefono(cliente.getTelefono());
			c.setCorreo(cliente.getCorreo());
			
			clienteService.updateCliente(c);
		}
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminarCliente(@PathVariable(value="id") Integer id) {
		clienteService.deleteCliente(id);
	}


}
