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

import py.com.spa.app.entities.Empleados;
import py.com.spa.app.entities.Horario;
import py.com.spa.app.entities.Servicios;
import py.com.spa.app.services.EmpleadoService;
import py.com.spa.app.services.HorarioService;

@RestController
@RequestMapping("/horario")
public class HorarioRESTController  {
	
	@Autowired
	public HorarioService horarioService;
	
	@Autowired
	public EmpleadoService empleadoService;
	
	
	@GetMapping("/listar")
	public List<Horario> listarhorarios(){
		return horarioService.findAll();
	}
	
	
	@GetMapping("/getServicios")
	public List<Horario> getServicios(){
		return horarioService.findAll();
	}
	
	@PostMapping("/agregar")
	public void agregarhorario(@RequestBody Horario horario) {
		horarioService.addHorario(horario);
	}
	
	@GetMapping("/encontrar/{id}")
	public Horario obtenerhorariosId(@PathVariable(value="id") Integer id) {
		return (Horario) horarioService.findByHorarioId(id);
	}
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificarhorario (@PathVariable(value="id") Integer id, @RequestBody Horario horario) {
		Horario c = horarioService.findByHorarioId(id);
		if(c!=null) {
			c.setHoraInicio(horario.getHoraInicio());
			c.setHoraFin(horario.getHoraFin());
			horarioService.updateHorario(c);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarhorario(@PathVariable(value="id") Integer id) {
		Horario c = horarioService.findByHorarioId(id);
		if (c!=null) {
			horarioService.deleteHorario(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	

	@GetMapping("/obtener-horario-empleado/{id}")
	public List<Horario> findByEmpleadoId(@PathVariable Integer id)
	{
		Empleados e = empleadoService.findEmpleadoById(id);	
		return (List<Horario>) horarioService.findByEmpleadoId(e);
	}
	
	
}