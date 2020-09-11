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

import py.com.spa.app.entities.EmpleadoDisponible;
import py.com.spa.app.services.EmpleadoDisponibleService;


@RestController
@RequestMapping("/disponibilidad")
public class EmpleadoDisponibleRESTController {

	@Autowired
	public EmpleadoDisponibleService edService;
	
	@GetMapping("/listar")
	public List<EmpleadoDisponible> listarCategorias(){
		return edService.findAll();
	}
	
	@PostMapping("/agregar")
	public void agregarEd(@RequestBody EmpleadoDisponible ed) {
		edService.add(ed);
	}
	
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificarEd (@PathVariable(value="id") Integer id, @RequestBody EmpleadoDisponible ed) {
		EmpleadoDisponible c = edService.findById(id);
		if(c!=null) {
			c.setServicioId(ed.getServicioId());;
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarEd(@PathVariable(value="id") Integer id) {
		EmpleadoDisponible c = edService.findById(id);
		if (c!=null) {
			edService.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
	}
}
