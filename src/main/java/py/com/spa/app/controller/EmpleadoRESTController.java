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

import py.com.spa.app.entities.Empleados;
import py.com.spa.app.services.EmpleadoService;

@RestController
@RequestMapping("/empleado")
public class EmpleadoRESTController {

	@Autowired
	private EmpleadoService empleadoService;
	
	@GetMapping("/listar")
	public List<Empleados> listarEmpleados() {
		List<Empleados> empleados = empleadoService.findAll();
		return empleados;
	}
	
	@GetMapping("/encontrar/{id}")
	public Empleados obtenerEmpleadoId(@PathVariable(value="id") Integer id) {
		return (Empleados) empleadoService.findEmpleadoById(id);
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<Void> agregarEmpleado(@RequestBody Empleados empleado) {
		Empleados e = empleadoService.findEmpleadoById(empleado.getCedula());
		if(e==null) {
			empleadoService.addEmpleado(empleado);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}else {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT );
		}
		
	
	}
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<Void> modificarCliente (@PathVariable(value="id") Integer id, @RequestBody Empleados empActual) {
	
		Empleados e = empleadoService.findEmpleadoById(id);
		if(e!=null) {
			e.setNombre(empActual.getNombre());
			e.setApellido(empActual.getApellido());
			e.setDireccion(empActual.getDireccion());
			e.setFechaNac(empActual.getFechaNac());
			e.setTelefono(empActual.getTelefono());
			empleadoService.updateEmpleado(e);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else{
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}  
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Void> eliminarEmpleado(@PathVariable(value="id") Integer id) {
		Empleados e = empleadoService.findEmpleadoById(id);
		if (e !=null) {
			empleadoService.deleteEmpleado(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
		
	}
	



}
