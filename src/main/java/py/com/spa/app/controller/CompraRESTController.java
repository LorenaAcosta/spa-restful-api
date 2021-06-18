package py.com.spa.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import py.com.spa.app.entities.Compras;
import py.com.spa.app.entities.Ventas;
import py.com.spa.app.services.CompraService;

@RestController
@RequestMapping("/compras")
@CrossOrigin(origins = "*")
public class CompraRESTController {

	@Autowired
	public CompraService compraService;
	
	@GetMapping("/listar")
	public List<Compras> listarCompras(){
		return compraService.findAll();
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregarCompra(@RequestBody Compras compra) {
		Compras compras = null;
		Map<String, Object> response = new HashMap<>();
		try {
			compras = compraService.addCompras(compra);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar el insert en la bd");
			response.put("error", e.getMostSpecificCause().getMessage() );
			                        
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El cliente ha sido creado con exito.");
		response.put("compras", compras);
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);	
	}
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificarCompra(@PathVariable(value="id") Integer id, @RequestBody Compras compra) {
		Compras c = compraService.findByComprasId(id);
		Compras cNuevo =  null;
		Map<String, Object> response = new HashMap<>();

		if (c == null) {
			response.put("mensaje",  "Error, No se pudo editar. La categoria no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			c.setEstado("ANULADO");
			cNuevo = compraService.updateCompras(c);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error", e.getMostSpecificCause().getMessage() );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		response.put("mensaje", "La categoria ha sido actualizada.");
		response.put("compras", cNuevo);
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);	
	}

	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarVenta(@PathVariable(value="id") Integer id) {
		Map<String, Object> response = new HashMap<>();
		Compras c = compraService.findByComprasId(id);

		if (c == null) {
			response.put("mensaje",  "Error, No se pudo eliminar. La categoria no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			compraService.deleteCompras(id);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error", e.getMostSpecificCause().getMessage() );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	@GetMapping("/encontrar/{id}")
	public Compras obtenerComprasId(@PathVariable(value="id") Integer id) {
		return (Compras) compraService.findByComprasId(id);
	}
	
	
	@GetMapping("/busqueda-compras/{id}")
	public ResponseEntity<?>  busquedaCompras(@PathVariable(value="id") String termino)  {
		List<Compras> lista = null;
		Map<String, Object> response = new HashMap<>();
		try {
			lista=compraService.busquedaCompras(termino);
		}catch( DataAccessException e ){
			response.put("mensaje",  "No se encontraron datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (lista==null) {
			response.put("mensaje",  "No hay datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Compras>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/listarporfecha/{fecha}")
	public List<Compras> findByFecha(@PathVariable(value="fecha") String fecha) throws ParseException {
		Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
		return (List<Compras>) compraService.findByFecha(date1);
	}
	
	
	
}
