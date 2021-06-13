package py.com.spa.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.com.spa.app.entities.DisponibleBoxes;
import py.com.spa.app.entities.Empleados;
import py.com.spa.app.entities.Servicios;
import py.com.spa.app.services.DisponibleBoxesService;
import py.com.spa.app.services.EmpleadoService;

import py.com.spa.app.services.ServicioService;


@RestController
@RequestMapping("/disponible-boxes")
@CrossOrigin(origins = "*")
public class DisponibleBoxRESTController {
	

	@Autowired
	private DisponibleBoxesService disponibleBoxesService;
	@Autowired
	private ServicioService servicioService;

	@Autowired
	private EmpleadoService empleadoService;
	
	@GetMapping("/listar")
	public List<DisponibleBoxes> listarDisponible(){
		return disponibleBoxesService.findAll();
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregarDisponible(@RequestBody DisponibleBoxes disponible) {
		DisponibleBoxes disp = null;
		Map<String, Object> response = new HashMap<>();
		try {
			disp = disponibleBoxesService.addDisponible(disponible);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar el insert en la bd");
			response.put("error", e.getMostSpecificCause().getMessage() );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Se ha creado con exito.");
		response.put("disponible", disp);
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);	
	}
	
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarDiponible(@PathVariable(value="id") Integer id) {
		Map<String, Object> response = new HashMap<>();
		DisponibleBoxes d = disponibleBoxesService.findByDisponibleId(id);
		if ( d == null) {
			response.put("mensaje",  "Error, No se pudo eliminar. La categoria no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			disponibleBoxesService.deleteCategoria(id);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error", e.getMostSpecificCause().getMessage() );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	
	@GetMapping("/obtener-servicios-disponibles/{id}")
	public ResponseEntity<?> findAllByServicioId(@PathVariable Integer id)
	{
		List<DisponibleBoxes> lista = null;
		Map<String, Object> response = new HashMap<>();
		Servicios s = servicioService.findServicioById(id);
		
		try {
			lista = disponibleBoxesService.findAllByCategoriaId(s);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error", e.getMostSpecificCause().getMessage() );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (lista==null) {
			response.put("mensaje",  "No hay datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<DisponibleBoxes>>(lista, HttpStatus.OK);
	}
	

	@GetMapping("/encontrar/{id}")
	public DisponibleBoxes encontrarProducto(@PathVariable Integer id) {
		return (DisponibleBoxes) disponibleBoxesService.findByDisponibleId(id);
	}

	
	@GetMapping("/encontrar-empleado/{id}")
	public DisponibleBoxes getDisponibilidad(@PathVariable(value="id") Integer id) {
		Empleados emp = empleadoService.findEmpleadoById(id);
		return (DisponibleBoxes) disponibleBoxesService.findByDisponibleId(id);
	}  //ver
	
	
	 @GetMapping("/listar-porservicio/{servicioId}")
	public  List<DisponibleBoxes>  listarByServicioV2(@PathVariable(value="servicioId") Integer id) {
		Servicios emp = servicioService.findServicioById(id);
		return ( List<DisponibleBoxes> ) disponibleBoxesService.findByServicioId(emp);
	}  
	



	
}
