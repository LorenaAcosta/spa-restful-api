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

import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Planilla;
import py.com.spa.app.services.PlanillaService;

@RestController
@RequestMapping("/planilla")
public class PlanillaRESTController  {
	
	@Autowired
	public PlanillaService planillaService;
	
	@GetMapping("/listar")
	public List<Planilla> listarPlanilla(){
		return planillaService.findAll();
	}
	
	@PostMapping("/agregar")
	public void agregarPlanilla(@RequestBody Planilla planilla) {
		planillaService.addPlanilla(planilla);
	}
	
	@GetMapping("/encontrar/{id}")
	public Planilla obtenerPlanillaId(@PathVariable(value="id") Integer id) {
		return (Planilla) planillaService.findByPlanillaId(id);
	}
	
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificarCategoria (@PathVariable(value="id") Integer id, @RequestBody Planilla planilla) {
		Planilla c = planillaService.findByPlanillaId(id);
		if(c!=null) {
			c.setEmpleadoId(planilla.getEmpleadoId());
			c.setBonificacionVentas(planilla.getBonificacionVentas());
			c.setSalarioBase(planilla.getSalarioBase());
			planillaService.updatePlanilla(c);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarPlanilla(@PathVariable(value="id") Integer id) {
		Planilla c = planillaService.findByPlanillaId(id);
		if (c!=null) {
			planillaService.deletePlanilla(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
	}
	

	
	
	
	
	
	


}
