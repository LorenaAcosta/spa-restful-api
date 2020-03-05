package py.com.spa.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.com.spa.app.entities.Clientes;
import py.com.spa.app.entities.Reservas;
import py.com.spa.app.services.ReservaService;

@RestController
@RequestMapping("/reserva")
public class ReservaRESTController {

	@Autowired
	private ReservaService reservaService;
	
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
		reservaService.addReserva(reserva);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@PostMapping("/reservas-cliente")
	public ResponseEntity<?> getReservasCliente(@RequestBody Clientes cliente){
		List<Reservas> reservas= reservaService.getReservasCliente(cliente.getClienteId());
		if (reservas!=null) {
			if (reservas.size()!=0) {
				return new ResponseEntity<> (reservas, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
}
