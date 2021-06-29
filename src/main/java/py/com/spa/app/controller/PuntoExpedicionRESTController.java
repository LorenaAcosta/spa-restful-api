package py.com.spa.app.controller;

import java.util.Formatter;
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

import py.com.spa.app.entities.PuntoExpedicion;
import py.com.spa.app.entities.Usuario;
import py.com.spa.app.reportes.Cajero;
import py.com.spa.app.services.PuntoExpedicionService;

@RestController
@RequestMapping("/punto-expedicion")
@CrossOrigin(origins = "*")
public class PuntoExpedicionRESTController  {
	
	@Autowired
	public PuntoExpedicionService puntoExpedicionService;
	private Formatter fmt;
	
	@GetMapping("/listar")
	public List<PuntoExpedicion> listarTipoComprobantes(){
		return puntoExpedicionService.findAll();
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregarPuntoExpedicion(@RequestBody PuntoExpedicion puntoExpedicion) {
		PuntoExpedicion imp = null;
		fmt = new Formatter();
		puntoExpedicion.setCodigo(fmt.format("%03d", puntoExpedicionService.getNextId()).toString());
		System.out.println("Codigo " + fmt);
		Map<String, Object> response = new HashMap<>();
		try {
			imp = puntoExpedicionService.addPuntoExpedicion(puntoExpedicion);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar el insert en la bd");
			response.put("error", e.getMostSpecificCause().getMessage() );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Punto expedición guardado.");
		response.put("puntoExpedicion", imp);
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);		
	}
	
	@GetMapping("/encontrar/{id}")
	public PuntoExpedicion obtenerPuntoExpedicionId(@PathVariable(value="id") Integer id) {
		return (PuntoExpedicion) puntoExpedicionService.findByPuntoExpedicionId(id);
	}
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificarhorario (@PathVariable(value="id") Integer id, @RequestBody PuntoExpedicion puntoExpedicion) {
		PuntoExpedicion c = puntoExpedicionService.findByPuntoExpedicionId(id);
		if(c!=null) {
			c.setDescripcion(puntoExpedicion.getDescripcion());
			//c.setCodigo(puntoExpedicion.getCodigo());
			puntoExpedicionService.updatePuntoExpedicion(c);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarTipoComprobante(@PathVariable(value="id") Integer id) {
		PuntoExpedicion c = puntoExpedicionService.findByPuntoExpedicionId(id);
		if (c!=null) {
			puntoExpedicionService.deletePuntoExpedicion(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
	}	
	
	@GetMapping("/obtener-cajeros")
	public List<Cajero> getCajeros() {
		return (List<Cajero>) puntoExpedicionService.getCajeros();
	}
}