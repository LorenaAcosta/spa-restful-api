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
import py.com.spa.app.entities.Reserva;
import py.com.spa.app.services.ReservaService;

@RestController
@RequestMapping("/reserva")
public class ReservaRESTController {
	
	@Autowired
	public ReservaService reservaService;
	
	@GetMapping("/listar")
	public List<Reserva> listarReserva(){
		return reservaService.findAll();
	}
	
	@PostMapping("/agregar")
	public void agregarReserva(@RequestBody Reserva reserva) {
		reservaService.addReserva(reserva);
	}
	@GetMapping("/encontrar/{id}")
	public Reserva obtenerReservaId(@PathVariable(value="id") Integer id) {
		return (Reserva) reservaService.findByReservaId(id);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarReserva(@PathVariable(value="id") Integer id) {
		Reserva c = reservaService.findByReservaId(id);
		if (c!=null) {
			reservaService.deleteReserva(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
	}

}
