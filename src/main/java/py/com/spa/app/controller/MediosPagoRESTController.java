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

import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Empleados;
import py.com.spa.app.entities.MediosPago;
import py.com.spa.app.services.MediosPagoService;

@RestController
@RequestMapping("/medios-pago")
@CrossOrigin(origins = "*")
public class MediosPagoRESTController {
	
	@Autowired
	public MediosPagoService mediosPagoService;
	
	Map<String, Object> response = new HashMap<>();
	
	@GetMapping("/listar")
	public List<MediosPago> listarMediosPago(){
		return mediosPagoService.findAll();
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregarCategoria(@RequestBody MediosPago mp) {
		MediosPago mediosPago = null;
		try {
			mediosPago = mediosPagoService.addMediosPago(mp);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar el insert en la bd");
			response.put("error", e.getMostSpecificCause().getMessage() );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El cliente ha sido creado con exito.");
		response.put("mediosPago", mediosPago);
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);	
	}
	

	
	@PutMapping("/modificar/{id}")
	public  ResponseEntity<?> modificarMedioPago(@PathVariable(value="id") Integer id, @RequestBody MediosPago mediosPago) {
		MediosPago c = mediosPagoService.findByMedioPagoId(id);
		MediosPago nuevo =  null;
		Map<String, Object> response = new HashMap<>();
		if (c == null) {
			response.put("mensaje",  "Error, No se pudo editar. La categoria no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			c.setCodigo(      mediosPago.getCodigo()); 
			c.setDescripcion( mediosPago.getDescripcion());
			nuevo = mediosPagoService.updateMedioPago(c);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error", e.getMostSpecificCause().getMessage() );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La categoria ha sido actualizada.");
		response.put("categoria", nuevo);
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);	
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?>  eliminarMediosPago(@PathVariable(value="id") Integer id) {
		Map<String, Object> response = new HashMap<>();
		MediosPago c = mediosPagoService.findByMedioPagoId(id);
		if (c == null) {
			response.put("mensaje",  "Error, No se pudo eliminar. La categoria no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			mediosPagoService.deleteTiposProducto(id);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error", e.getMostSpecificCause().getMessage() );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/encontrar/{id}")
	public ResponseEntity<?>  obtenerMedioPagoId(@PathVariable(value="id") Integer id) {
		MediosPago mp = mediosPagoService.findByMedioPagoId(id);
		Map<String, Object> response = new HashMap<>();
		if (mp == null) 
		{
			response.put("mensaje", "El  empelado ID:" .concat(id.toString().concat("no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<MediosPago>(mp, HttpStatus.OK); 
	}
	
	
	
}
