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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import py.com.spa.app.entities.Impuesto;
import py.com.spa.app.services.ImpuestoService;

@RestController
@RequestMapping("/impuesto")
@CrossOrigin(origins = "*")
public class ImpuestoRESTController  {
	
	@Autowired
	public ImpuestoService impuestoService;
	
	@GetMapping("/listar")
	public List<Impuesto> listarImpuestos(){
		return impuestoService.findAll();
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregarImpuesto(@RequestBody Impuesto impuesto) {
		Impuesto imp = null;
		Map<String, Object> response = new HashMap<>();
		try {
			imp = impuestoService.addImpuesto(impuesto);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar el insert en la bd");
			response.put("error", e.getMostSpecificCause().getMessage() );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Impuesto guardado.");
		response.put("impuesto", imp);
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);		
	}
	
	@GetMapping("/encontrar/{id}")
	public Impuesto obtenerImpuestoId(@PathVariable(value="id") Integer id) {
		return (Impuesto) impuestoService.findByImpuestoId(id);
	}
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificarhorario (@PathVariable(value="id") Integer id, @RequestBody Impuesto impuesto) {
		Impuesto c = impuestoService.findByImpuestoId(id);
		if(c!=null) {
			c.setDescripcion(impuesto.getDescripcion());
			c.setValor(impuesto.getValor());
			impuestoService.updateImpuesto(c);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarImpuesto(@PathVariable(value="id") Integer id) {
		Impuesto c = impuestoService.findByImpuestoId(id);
		if (c!=null) {
			impuestoService.deleteImpuesto(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
	}	
}