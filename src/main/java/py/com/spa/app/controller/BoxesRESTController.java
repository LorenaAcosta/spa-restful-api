package py.com.spa.app.controller;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
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

import py.com.spa.app.entities.Boxes;
import py.com.spa.app.services.BoxesService;

@RestController
@RequestMapping("/boxes" )
@CrossOrigin(origins = "*")
public class BoxesRESTController  {
	
	@Autowired
	public BoxesService boxesService;
	

	
	@GetMapping("/listar")
	public List<Boxes> listarCategorias(){
		return boxesService.findAll();
	}

	
	@GetMapping("/getBoxes")
	public List<Boxes> getServicios(){
		return boxesService.findAll();
	}
	

	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregarCategoria(@RequestBody Boxes  c) {
	
		Boxes categoria = null;
		Map<String, Object> response = new HashMap<>();
		try {
			categoria= boxesService.addCategoria(c);
			
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar el insert en la bd");
			response.put("error",  e.getMostSpecificCause().getMessage()   )  ;
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El cliente ha sido creado con exito.");
		response.put("categoria", categoria);
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);		
	} 
		

	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarCategoria(@PathVariable(value="id") Integer id) {
		Map<String, Object> response = new HashMap<>();
		Boxes c = boxesService.findByCategoriaId(id);

		if (c == null) {
			response.put("mensaje",  "Error, No se pudo eliminar. La categoria no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			boxesService.deleteCategoria(id);
			
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error",  e.getMostSpecificCause().getMessage()   )  ;
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}
	
	@GetMapping("/get-boxes-disponibles/{id}")
	public  ResponseEntity<?>  getBoxesDisponibles(@PathVariable(value="id") Integer servicioId) {
		List<Boxes> boxes = null;
		Map<String, Object> response = new HashMap<>();
		try {
			
			boxes = (List<Boxes>) boxesService.getBoxesDisponibles(servicioId);
		}catch( DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error",  e.getMostSpecificCause().getMessage()   )  ;
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (boxes==null) {
			response.put("mensaje",  "No hay datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<	List<Boxes> >(	boxes, HttpStatus.OK);
	}
	
	
	@GetMapping("/obtener-box-libre/{fecha}/{hora}/{servicioId}")
	public Integer obtenerBoxLibre(
			 @PathVariable(value="fecha") String fecha,
			 @PathVariable(value="hora") String hora,
			 @PathVariable(value="servicioId") Integer servicioId) throws ParseException  {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fech = sdf.parse(fecha);
		

		LocalTime t = LocalTime.parse(hora ) ;
		Time time = Time.valueOf( t );
		
		
		return boxesService.obtenerBoxLibre(fech, time, servicioId);
	}
	

}
