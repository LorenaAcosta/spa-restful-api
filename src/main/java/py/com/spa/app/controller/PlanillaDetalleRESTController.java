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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Planilla;
import py.com.spa.app.entities.PlanillaDetalle;
import py.com.spa.app.reportes.DetallesConceptos;
import py.com.spa.app.reportes.RankingP;
import py.com.spa.app.services.PlanillaDetalleService;
import py.com.spa.app.services.PlanillaService;
@RestController
@RequestMapping("/planilla-detalle")
@CrossOrigin(origins = "*")
public class PlanillaDetalleRESTController  {
	
	@Autowired
	public PlanillaDetalleService planillaService;
	

	@GetMapping("/listar")
	public List<PlanillaDetalle> listarPlanilla(){
		return planillaService.findAll();
	}
	
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregarPlanilla(@RequestBody PlanillaDetalle planilla) {
		PlanillaDetalle planillares = null;
		Map<String, Object> response =  new HashMap<>();
		try {
			planillares = planillaService.addPlanilla(planilla);	
			
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar el insert en la bd");
			response.put("error",  e.getMostSpecificCause().getMessage()   )  ;
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "PlanillaDetalle ha sido creado con exito.");
		response.put("planilla", planillares);
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);		
		
	}
	

	
	@GetMapping("/encontrar/{id}")
	public PlanillaDetalle obtenerPlanillaId(@PathVariable(value="id") Integer id) {
		return (PlanillaDetalle) planillaService.findByPlanillaId(id);
	}
	
	
	@GetMapping("/get-by-planilla/{id}")
	public List<PlanillaDetalle> getPlanillaId(@PathVariable(value="id") Integer id) {
		return (List<PlanillaDetalle>) planillaService.findByPlanillaId2(id);
	}
	
	@GetMapping("/get-comisiones/{id}")
	public Integer getComisiones(@PathVariable(value="id") Integer id) {
		return (Integer) planillaService.getComisiones(id);
	}
	
	@GetMapping("/get-salario/{id}")
	public Integer getSalario(@PathVariable(value="id") Integer id) {
		return (Integer) planillaService.getSalario(id);
	}
		

	@GetMapping("/get-detalles/{id}")
	public List<DetallesConceptos> getDetalles(@PathVariable(value="id") Integer id) {
		return (List<DetallesConceptos>) planillaService.getDetalles(id);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarPlanilla(@PathVariable(value="id") Integer id) {
		PlanillaDetalle c = planillaService.findByPlanillaId(id);
		if (c!=null) {
			planillaService.deletePlanilla(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
	}
	

	
	
	
	
	
	


}
