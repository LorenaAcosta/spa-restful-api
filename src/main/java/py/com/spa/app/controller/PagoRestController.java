package py.com.spa.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.com.spa.app.entities.Pagos;
import py.com.spa.app.services.PagoService;

@RestController
@RequestMapping("/pago")
public class PagoRestController {

	@Autowired
	private PagoService pagoService;
	
	
	@GetMapping("/listar")
	public  ResponseEntity<?> listarPagos(){
		List<Pagos> pagos = pagoService.findAll();
		if (pagos!=null) {
			if (pagos.size()!=0) {
				return new ResponseEntity<>(pagos, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregarPago (@RequestBody Pagos pago ){		
		pagoService.agregarPago(pago);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/eliminar{id}")
	public ResponseEntity<?> eliminarPago (@PathVariable(value="id")  Integer id){
		pagoService.eliminarPago(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
