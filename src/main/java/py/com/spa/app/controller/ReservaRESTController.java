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

import py.com.spa.app.entities.Clientes;
import py.com.spa.app.entities.MediosPago;
import py.com.spa.app.entities.Reservas;
import py.com.spa.app.services.MediosPagoService;
import py.com.spa.app.services.ReservaService;

@RestController
@RequestMapping("/reserva")
public class ReservaRESTController {

	@Autowired
	private ReservaService reservaService;
	
	@Autowired
	public MediosPagoService mediosPagoService;
	
	@GetMapping("/listar")
	public  ResponseEntity<?> listarReservas(){
		List<Reservas> reservas = reservaService.findAll();
		if (reservas!=null) {
			if (reservas.size()!=0) {
				return new ResponseEntity<>(reservas, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregarReserva (@RequestBody Reservas reserva){		
		reservaService.agregarReserva(reserva);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	

	@PutMapping("/modificar/{id}/{id}")
	public ResponseEntity<Void> modificarReserva (@PathVariable(value="id") Integer reservaId,
			@RequestBody Reservas reserva) {
	    Reservas rs = null;
	    
	    rs = reservaService.findReservaById(reservaId);
	    
		if(rs!=null) {
			rs.setEstado(reserva.getEstado());
			rs.setFecha(reserva.getFecha());
			reservaService.updateReserva(rs);	
			return new ResponseEntity<Void>(HttpStatus.OK);
			
		}else{
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} 
	} 
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Void> eliminarReserva (@PathVariable(value="id") Integer id) {
		Reservas rs= reservaService.findReservaById(id);
		if (rs!=null) {
			reservaService.eliminarReserva(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	

	
}
