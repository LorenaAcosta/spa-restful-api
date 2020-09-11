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

import py.com.spa.app.entities.Proveedor;
import py.com.spa.app.services.ProveedorService;

@RestController
@RequestMapping("/proveedor")
public class ProveedorRESTController {
	@Autowired
	public ProveedorService proveedorService;
	
	@GetMapping("/listar")
	public List<Proveedor> listarProveedor(){
		return proveedorService.findAll();
	}

	
	@PostMapping("/agregar")
	public void agregarCategoria(@RequestBody Proveedor proveedor) {
		proveedorService.add(proveedor);
	}
	
	@GetMapping("/encontrar/{id}")
	public Proveedor obtenerProveedorId(@PathVariable(value="id") Integer id) {
		return (Proveedor) proveedorService.findById(id);
	}
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificarCategoria (@PathVariable(value="id") Integer id, @RequestBody Proveedor proveedor) {
		Proveedor c = proveedorService.findById(id);
		if(c!=null) {
			c.setNombreProveedor(proveedor.getNombreProveedor());
			c.setTelefono(proveedor.getTelefono());
			c.setEmpresa(proveedor.getEmpresa());
			proveedorService.update(c);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarCategoria(@PathVariable(value="id") Integer id) {
		Proveedor c = proveedorService.findById(id);
		if (c!=null) {
			proveedorService.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
	}

}
