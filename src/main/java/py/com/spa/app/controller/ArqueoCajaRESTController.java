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


import py.com.spa.app.entities.ArqueoCaja;
import py.com.spa.app.services.ArqueoCajaService;

@RestController
@RequestMapping("/arqueo-caja")
@CrossOrigin(origins = "*")
public class ArqueoCajaRESTController  {
	
	@Autowired
	public ArqueoCajaService arqueoService;
	
	@GetMapping("/listar")
	public List<ArqueoCaja> listarArqueos(){
		return arqueoService.findAll();
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregarArqueoCaja(@RequestBody ArqueoCaja arqueo) {
		ArqueoCaja arqueoCaja = null;
		Map<String, Object> response = new HashMap<>();
		try {
			arqueoCaja = arqueoService.addArqueoCaja(arqueo);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar el insert en la bd");
			response.put("error", e.getMostSpecificCause().getMessage() );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Arqueo Guardado.");
		response.put("horario", arqueoCaja);
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);		
	}
	
	@GetMapping("/encontrar/{id}")
	public ArqueoCaja obtenerArqueosId(@PathVariable(value="id") Integer id) {
		return (ArqueoCaja) arqueoService.findByArqueoCajaId(id);
	}
	
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificarhorario (@PathVariable(value="id") Integer id, @RequestBody ArqueoCaja arqueo) {
		ArqueoCaja c = arqueoService.findByArqueoCajaId(id);
		if(c!=null) {
			c.setFechaCierre(arqueo.getFechaCierre());
			c.setHoraCierre(arqueo.getHoraCierre());
			c.setTotalCaja(arqueo.getTotalCaja());
			c.setTotalVentas(arqueo.getTotalVentas());
			c.setSaldoCierre(arqueo.getSaldoCierre());
			c.setEstado(arqueo.getEstado());
			arqueoService.updateArqueoCaja(c);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarArqueo(@PathVariable(value="id") Integer id) {
		ArqueoCaja c = arqueoService.findByArqueoCajaId(id);
		if (c!=null) {
			arqueoService.deleteArqueoCaja(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
	}

	@GetMapping("/obtener-caja-activa/{id}")
	public ArqueoCaja getCajaActiva(@PathVariable(value="id") Integer id) {
		return (ArqueoCaja) arqueoService.getCajaActiva(id);
	}
	
}