 package py.com.spa.app.controller;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.fasterxml.jackson.annotation.JsonFormat;

import py.com.spa.app.entities.Empleados;
import py.com.spa.app.entities.Horario;
import py.com.spa.app.entities.Servicios;
import py.com.spa.app.services.EmpleadoService;
import py.com.spa.app.services.HorarioService;

@RestController
@RequestMapping("/horario")
@CrossOrigin(origins = "*")
public class HorarioRESTController  {
	
	@Autowired
	public HorarioService horarioService;
	
	@Autowired
	public EmpleadoService empleadoService;
	
	private Logger log = LoggerFactory.getLogger(HorarioRESTController.class);
	
	@GetMapping("/listar")
	public List<Horario> listarhorarios(){
		return horarioService.findAll();
	}
	
	
	@GetMapping("/getServicios")
	public List<Horario> getServicios(){
		return horarioService.findAll();
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregarhorario(@RequestBody Horario horario) {
		Horario hora = null;
		Map<String, Object> response = new HashMap<>();
		try {
			hora = horarioService.addHorario(horario);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar el insert en la bd");
			response.put("error", e.getMostSpecificCause().getMessage() );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Horario guardado.");
		response.put("horario", hora);
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);		
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
			c.setEmpleadoId(horario.getEmpleadoId());
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
	public Horario findByEmpleadoId(@PathVariable(value="id") Integer id)
	{
		Empleados emp = empleadoService.findEmpleadoById(id);
		return (Horario) horarioService.findByEmpleadoId(emp);
	}
	
	
	
	@GetMapping("/listar-horarios/{id}")
	public List<Horario> getListaHorarios(@PathVariable(value="id") Integer id){
		return horarioService.getListaHorarios(id);
	}
	
	
}