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
import py.com.spa.app.entities.Comprobante;
import py.com.spa.app.services.ComprobanteService;

@RestController
@RequestMapping("/comprobante")
@CrossOrigin(value="*")
public class ComprobanteRESTController  {
	
	@Autowired
	public ComprobanteService comprobanteService;
	
	@GetMapping("/listar")
	public List<Comprobante> listarComprobantes(){
		return comprobanteService.findAll();
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregarComprobante(@RequestBody Comprobante comprobante) {
		Comprobante imp = null;
		comprobante.setNumeroActual(0);
		Map<String, Object> response = new HashMap<>();
		try {
			imp = comprobanteService.addComprobante(comprobante);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar el insert en la bd");
			response.put("error",  e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Comprobante guardado.");
		response.put("comprobante", imp);
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);		
	}
	
	@GetMapping("/encontrar/{id}")
	public Comprobante obtenerComprobanteId(@PathVariable(value="id") Integer id) {
		return (Comprobante) comprobanteService.findByComprobanteId(id);
	}
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificarhorario (@PathVariable(value="id") Integer id, @RequestBody Comprobante comprobante) {
		Comprobante c = comprobanteService.findByComprobanteId(id);
		if(c!=null) {
			c.setComprobanteId(comprobante.getComprobanteId());
			c.setTipoComprobanteId(comprobante.getTipoComprobanteId());
			c.setTimbrado(c.getTimbrado());
			c.setNumeroInicial(comprobante.getNumeroInicial());
			c.setNumeroFinal(comprobante.getNumeroFinal());
			c.setNumeroActual(comprobante.getNumeroActual());
			c.setEstado(comprobante.getEstado());
			c.setInicioVigencia(comprobante.getInicioVigencia());
			c.setFinVigencia(comprobante.getFinVigencia());
			
			comprobanteService.updateComprobante(c);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarComprobante(@PathVariable(value="id") Integer id) {
		Comprobante c = comprobanteService.findByComprobanteId(id);
		if (c!=null) {
			comprobanteService.deleteComprobante(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
	}	
	@GetMapping("/numero-actual")
	public Integer getNumeroActual() {
		return (Integer) comprobanteService.numeroActual();
	}
	
	@GetMapping("/comprobante-activo")
	public Comprobante getComprobanteActivo() {
		return (Comprobante) comprobanteService.getComprobanteActivo();
	}
}