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
import py.com.spa.app.entities.TipoComprobante;
import py.com.spa.app.services.TipoComprobanteService;

@RestController
@RequestMapping("/tipo-comprobante")
@CrossOrigin(origins = "*")
public class TipoComprobanteRESTController  {
	
	@Autowired
	public TipoComprobanteService tipoComprobanteService;
	
	@GetMapping("/listar")
	public List<TipoComprobante> listarTipoComprobantes(){
		return tipoComprobanteService.findAll();
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregarTipoComprobante(@RequestBody TipoComprobante tipoComprobante) {
		TipoComprobante imp = null;
		Map<String, Object> response = new HashMap<>();
		try {
			imp = tipoComprobanteService.addTipoComprobante(tipoComprobante);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar el insert en la bd");
			response.put("error", e.getMostSpecificCause().getMessage() );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "TipoComprobante guardado.");
		response.put("tipoComprobante", imp);
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);		
	}
	
	@GetMapping("/encontrar/{id}")
	public TipoComprobante obtenerTipoComprobanteId(@PathVariable(value="id") Integer id) {
		return (TipoComprobante) tipoComprobanteService.findByTipoComprobanteId(id);
	}
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificarhorario (@PathVariable(value="id") Integer id, @RequestBody TipoComprobante tipoComprobante) {
		TipoComprobante c = tipoComprobanteService.findByTipoComprobanteId(id);
		if(c!=null) {
			c.setDescripcion(tipoComprobante.getDescripcion());
			c.setTipoComprobanteId(tipoComprobante.getTipoComprobanteId());
			tipoComprobanteService.updateTipoComprobante(c);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarTipoComprobante(@PathVariable(value="id") Integer id) {
		TipoComprobante c = tipoComprobanteService.findByTipoComprobanteId(id);
		if (c!=null) {
			tipoComprobanteService.deleteTipoComprobante(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
	}	
}