package py.com.spa.app.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import py.com.spa.app.entities.Categorias;
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
	public ResponseEntity<?> obtenerEmpleadoId(@PathVariable(value="id") Integer id) {
		Empleados emp = empleadoService.findEmpleadoById(id);
		
		Map<String, Object> response = new HashMap<>();
		if (emp == null) 
		{
			response.put("mensaje", "El  empelado ID:" .concat(id.toString().concat("no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Empleados>(emp, HttpStatus.OK); 		
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregarEmpleado(@RequestBody Empleados empleado) {
		
		Empleados empleadoNuevo = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			empleadoNuevo = empleadoService.saveEmpleado(empleado);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al realizar el insert");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El empelado ha sido creado con éxito");
		response.put("empleado", empleadoNuevo);
		return new ResponseEntity<Empleados>(empleadoNuevo, HttpStatus.CREATED); 	
	}
	
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<Void> modificarCliente (@PathVariable(value="id") Integer id, @RequestBody Empleados empActual) {
	
		Empleados e = empleadoService.findEmpleadoById(id);
		if(e!=null) {
			e.setCedula(empActual.getCedula());
			e.setNombre(empActual.getNombre());
			e.setApellido(empActual.getApellido());
			e.setDireccion(empActual.getDireccion());
			e.setTelefono(empActual.getTelefono());
			e.setFechaNac(empActual.getFechaNac());
			e.setImageName(empActual.getImageName());
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
