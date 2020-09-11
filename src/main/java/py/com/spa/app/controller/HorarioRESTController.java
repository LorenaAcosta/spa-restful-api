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

import py.com.spa.app.entities.Horario;
import py.com.spa.app.services.HorarioService;

@RestController
@RequestMapping("/horario")
public class HorarioRESTController {
	

	@Autowired
	private HorarioService horarioService;
	
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregarHorario(@RequestBody Horario p) {
		horarioService.addHorario(p);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping("/listar")
	public ResponseEntity<?> listarHorarios(){
		List<Horario> Horarios = horarioService.findAll();
		if ( Horarios!= null ) {
			if (Horarios.size()!=0) {
			return new ResponseEntity<>( Horarios, HttpStatus.OK);
			}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping("/encontrar/{id}")
	public Horario encontrarHorario(@PathVariable Integer id) {
		return (Horario) horarioService.findByHorarioId(id);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminarHorario(@PathVariable(value="id") Integer id) {
		horarioService.deleteHorario(id);
	}
	
	
	@PutMapping("/modificar/{id}")
	public void modificarHorario(@PathVariable Integer id, @RequestBody Horario horario) {
		Horario h= horarioService.findByHorarioId(id);
		if (h!=null) {
			h.setEmpleadoId(horario.getEmpleadoId());
			h.setHoraFin(horario.getHoraFin());
			h.setHoraInicio(horario.getHoraInicio());
			h.setHorarioId(horario.getHorarioId());
			horarioService.updateHorario(h);
		}	
	} 
	
	
	
}
