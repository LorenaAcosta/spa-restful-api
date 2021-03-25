package py.com.spa.app.controller;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
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
import py.com.spa.app.entities.Disponible;
import py.com.spa.app.entities.Empleados;
import py.com.spa.app.entities.Horario;
import py.com.spa.app.entities.ReservaDetalle;
import py.com.spa.app.entities.Turnos;
import py.com.spa.app.entities.Usuario;
import py.com.spa.app.services.DisponibleService;
import py.com.spa.app.services.EmpleadoService;
import py.com.spa.app.services.HorarioService;
import py.com.spa.app.services.ReservaDetalleService;
import py.com.spa.app.services.ServiceService;
import py.com.spa.app.services.ServicioService;

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
			response.put("mensaje", "El  empleado ID:" .concat(id.toString().concat("no existe en la base de datos")));
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
			response.put("error", e.getMostSpecificCause().getMessage()  );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El empleado ha sido creado con éxito");
		response.put("empleado", empleadoNuevo);
		return new ResponseEntity<Empleados>(empleadoNuevo, HttpStatus.CREATED); 	
	}
	
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificarCliente (@PathVariable(value="id") Integer id, @RequestBody Empleados empActual) {
		Empleados emp = empleadoService.findEmpleadoById(id);
		Empleados empleado =  null;
		Map<String, Object> response = new HashMap<>();
	
		if (emp == null) {
			response.put("mensaje",  "Error, No se pudo editar. No existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			emp.setCedula(empActual.getCedula());
			emp.setNombre(empActual.getNombre());
			emp.setApellido(empActual.getApellido());
			emp.setDireccion(empActual.getDireccion());
			emp.setTelefono(empActual.getTelefono());
			emp.setFechaNac(empActual.getFechaNac());
			emp.setFechaNac(empActual.getFechaNac());
			emp.setCorreo(empActual.getCorreo());
			emp.setEstadoCivil(empActual.getEstadoCivil());
			emp.setEstado(empActual.getEstado());
			emp.setFechaIngreso(empActual.getFechaIngreso());
			emp.setFecha_salida(empActual.getFecha_salida());
			emp.setCelular(empActual.getCelular());
			emp.setFuncion(empActual.getFuncion());
			emp.setSueldo(empActual.getSueldo());
			emp.setCiudad(empActual.getCiudad());
			emp.setCiudad(empActual.getCiudad());
			emp.setCiudad(empActual.getCiudad());
			emp.setNacionalidad(empActual.getNacionalidad());			
			
			empleado = empleadoService.updateEmpleado(emp);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El empleado ha sido actualizada.");
		response.put("empleado", empleado);
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);		
	}  
	
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarEmpleado(@PathVariable(value="id") Integer id) {
		Map<String, Object> response = new HashMap<>();
		Empleados emp = empleadoService.findEmpleadoById(id);

		if (emp == null) {
			response.put("mensaje",  "Error, No se pudo eliminar. La categoria no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			empleadoService.deleteEmpleado(id);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/encontrar-por-cedula/{id}")
	public ResponseEntity<?> findEmpleadoCedula(@PathVariable(value="id") Integer cedula) {
		Empleados emp = empleadoService.findEmpleadoCedula(cedula);
		Map<String, Object> response = new HashMap<>();
		if (emp == null) 
		{
			response.put("mensaje", "El  empelado ID:" .concat(cedula.toString().concat("no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Empleados>(emp, HttpStatus.OK); 		
	}
	
	@GetMapping("/busqueda-empleados/{id}")
	public ResponseEntity<?>  busquedaEmpleados(@PathVariable(value="id") String termino)  {
		List<Empleados> lista = null;
		Map<String, Object> response = new HashMap<>();
		try {
			lista= empleadoService.busquedaEmpleados(termino);
		}catch( DataAccessException e ){
			response.put("mensaje",  "No se encontraron datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (lista==null) {
			response.put("mensaje",  "No hay datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Empleados>>(lista, HttpStatus.OK);
	}
	

	
	
	
}
