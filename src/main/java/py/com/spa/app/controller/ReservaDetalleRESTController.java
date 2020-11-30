package py.com.spa.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import py.com.spa.app.entities.ReservaDetalle;
import py.com.spa.app.services.HorarioService;
import py.com.spa.app.services.ReservaDetalleService;

@RestController
@RequestMapping(value="/reserva-detalle")
public class ReservaDetalleRESTController {
	
	@Autowired
	public ReservaDetalleService reservaDetalleService;
	
	@Autowired
	public HorarioService horarioService;
	
	@GetMapping("/listar")
	public List<ReservaDetalle> listarReservaDetalle(){
		System.out.println(TimeZone.getDefault());
		return reservaDetalleService.findAll();
	}
	
	@PostMapping("/agregar")
	public void agregarReservaDetalle(@RequestBody ReservaDetalle reservadetalle) {
		System.out.println(reservadetalle.getFechaReserva().toString());
		reservaDetalleService.addReservaDetalle(reservadetalle);
	}
	
	@GetMapping("/encontrar/{id}")
	public ReservaDetalle obtenerReservaDetalleId(@PathVariable(value="id") Integer empleado) {
		return (ReservaDetalle) reservaDetalleService.findByReservaDetalleId(empleado);
	}
	
	@GetMapping("/get-turnos/{id}")
	public List<ReservaDetalle> findByEmpleado(@PathVariable(value="id") Integer id) {
		return (List<ReservaDetalle>) reservaDetalleService.findByEmpleado(id);
	}
	
	@GetMapping("/listarporfecha/{fechaReserva}")
	public List<ReservaDetalle> findByFechaReserva(@PathVariable(value="fechaReserva") String fechaReserva) throws ParseException {
		Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaReserva);
		return (List<ReservaDetalle>) reservaDetalleService.findByFechaReserva(date1);
	}
	
	@GetMapping("/get-turnos/{id}/{date}")
	public List<ReservaDetalle> findAllByEmpleadoAndFechaReservaOrderByHoraAsc(@PathVariable(value="id") Integer empleado, @PathVariable(value="date")  String date) throws ParseException {
		Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		return (List<ReservaDetalle>) reservaDetalleService.findAllByEmpleadoAndFechaReservaOrderByHoraAsc(empleado, date1);
	}
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificarReservaDetalle(@PathVariable(value="id") Integer id, @RequestBody ReservaDetalle reservadetalle) {
		ReservaDetalle c = reservaDetalleService.findByReservaDetalleId(id);
		if(c!=null) {
		//	c.setServicioId(reservadetalle.get
		//c.setEmpleadoId(reservadetalle.getEmpleadoId());
			c.setFechaReserva(reservadetalle.getFechaReserva());
			c.setHora(reservadetalle.getHora());
			reservaDetalleService.updateReserva(c);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarReservaDetalle(@PathVariable(value="id") Integer id) {
		ReservaDetalle c = reservaDetalleService.findByReservaDetalleId(id);
		if (c!=null) {
			reservaDetalleService.deleteReserva(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}	
	}
}
