package py.com.spa.app.controller;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
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

import py.com.spa.app.dao.IHorarioDao;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Disponible;

import py.com.spa.app.entities.Empleados;
import py.com.spa.app.entities.Horario;
import py.com.spa.app.entities.Servicios;
import py.com.spa.app.services.DisponibleService;
import py.com.spa.app.services.EmpleadoService;

import py.com.spa.app.services.HorarioService;
import py.com.spa.app.services.ServicioService;
import py.com.spa.result.DisponibleDatosResult;


@RestController
@RequestMapping("/disponible")
@CrossOrigin(origins = "*")
public class DisponibleRESTController {
	

	@Autowired
	private DisponibleService disponibleService;
	@Autowired
	private ServicioService servicioService;

	@Autowired
	private EmpleadoService empleadoService;
	
	@Autowired 
	private HorarioService horarioService;
	
	@GetMapping("/listar")
	public List<Disponible> listarDisponible(){
		return disponibleService.findAll();
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregarDisponible(@RequestBody Disponible disponible) {
		Disponible disp = null;
		Map<String, Object> response = new HashMap<>();
		try {
			disp = disponibleService.addDisponible(disponible);
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
		Disponible d = disponibleService.findByDisponibleId(id);
		if ( d == null) {
			response.put("mensaje",  "Error, No se pudo eliminar. La categoria no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			disponibleService.deleteCategoria(id);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error", e.getMostSpecificCause().getMessage() );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	
	@GetMapping("/obtener-empleados-disponibles/{id}")
	public ResponseEntity<?> findAllByServicioId(@PathVariable Integer id)
	{
		List<Disponible> lista = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			//lista = disponibleService.findAllByCategoriaId(s);
			lista = disponibleService.findEmpleadosDisponibles(id);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error", e.getMostSpecificCause().getMessage() );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (lista==null) {
			response.put("mensaje",  "No hay datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Disponible>>(lista, HttpStatus.OK);
	}
	

	@GetMapping("/encontrar/{id}")
	public Disponible encontrarProducto(@PathVariable Integer id) {
		return (Disponible) disponibleService.findByDisponibleId(id);
	}
	
	
	@GetMapping("/encontrar-empleado/{id}")
	public Disponible getDisponibilidad(@PathVariable(value="id") Integer id) {
		Empleados emp = empleadoService.findEmpleadoById(id);
		return (Disponible) disponibleService.findByDisponibleId(id);
	}  //ver
	
	
	 @GetMapping("/listar-porempleado/{empleadoId}")
	public  List<Disponible>  listarByEmpleadoV2(@PathVariable(value="empleadoId") Integer id) {
		Empleados emp = empleadoService.findEmpleadoById(id);
		return ( List<Disponible> ) disponibleService.findByEmpleadoId(emp);
	}  
	


	@GetMapping("/getHorariosDisponibles/{categoriaId}/{servicioId}/{empleadoId}/{fecha}")
	public List<Time> getHorariosDisponibles(@PathVariable(value="categoriaId")  Integer categoriaId, @PathVariable(value="servicioId") Integer servicioId,
												@PathVariable(value="empleadoId")  Integer empleadoId, @PathVariable(value="fecha")  String fecha) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fech = sdf.parse(fecha);
		

		return (List<Time>) disponibleService.getHorariosDisponibles(categoriaId, servicioId, empleadoId, fech);
	}
	
	@GetMapping("/encontrar-datos/{id}")
	public DisponibleDatosResult encontraDisponibleDAtos(@PathVariable Integer id) {
		DisponibleDatosResult data = new DisponibleDatosResult();
		Disponible disponible = disponibleService.findByDisponibleId(id);
		if (disponible != null ) {
			 List<Horario> horario =  (List<Horario>)  horarioService.findByIdEmpleadoLista(disponible.getEmpleadoId().getEmpleadoId());
			 data.setDisponible(disponible);
			 data.setHorario(horario);	
		}
		
		return data ;
	}
	
	
}
