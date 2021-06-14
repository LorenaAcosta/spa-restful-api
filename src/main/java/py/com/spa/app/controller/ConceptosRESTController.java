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

import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Compras;
import py.com.spa.app.entities.Conceptos;
import py.com.spa.app.entities.Planilla;
import py.com.spa.app.services.ConceptosService;
import py.com.spa.app.services.ServicioService;

@RestController
@RequestMapping("/conceptos" )
@CrossOrigin(origins = "*")
public class ConceptosRESTController{

	
	@Autowired
	public ConceptosService conceptosService;

	
	@GetMapping("/listar")
	public List<Conceptos> listarConceptos(){
		return conceptosService.findAll();
	}
	
	@GetMapping("/listar-conceptos/{tipo}")
	public List<Conceptos> listarConceptos(@PathVariable(value="tipo") String tipo){
		return conceptosService.listarConceptos(tipo);
	}
	
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregarConceptos(@RequestBody Conceptos  c) {
	
		Conceptos conceptos = null;
		Map<String, Object> response = new HashMap<>();
		try {
			conceptos= conceptosService.addConceptos(c);
			
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar el insert en la bd");
			response.put("error", e.getMostSpecificCause().getMessage() );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El concepto ha sido creado con exito.");
		response.put("categoria", conceptos);
		
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);		
	} 
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarConceptos(@PathVariable(value="id") Integer id) {
		Map<String, Object> response = new HashMap<>();
		Conceptos c = conceptosService.findByConceptoId(id);

		if (c == null) {
			response.put("mensaje",  "Error, No se pudo eliminar. La categoria no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			conceptosService.deleteConcepto(id);
		
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error", e.getMostSpecificCause().getMessage() );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}

	
	@GetMapping("/encontrar/{id}")
	public Conceptos obtenerConceptoId(@PathVariable(value="id") Integer id) {
		return (Conceptos) conceptosService.findByConceptoId(id);
	}
	
	
	
}