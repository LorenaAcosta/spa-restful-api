 package py.com.spa.app.controller;

import java.io.FileNotFoundException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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

import net.sf.jasperreports.engine.JRException;
import py.com.spa.app.entities.Productos;
import py.com.spa.app.entities.ReservaDetalle;
import py.com.spa.app.entities.Usuario;
import py.com.spa.app.entities.Ventas;
import py.com.spa.app.reportes.ServiciosReservadosPorClienteFecha;
import py.com.spa.app.services.BoxesService;
import py.com.spa.app.services.DisponibleService;
import py.com.spa.app.services.HorarioService;
import py.com.spa.app.services.ReservaDetalleService;
import py.com.spa.app.services.VentaService;

@RestController
@RequestMapping(value="/reserva-detalle")
@CrossOrigin(origins = "*")
public class ReservaDetalleRESTController {
	
	@Autowired
	public ReservaDetalleService reservaDetalleService;
	
	@Autowired
	public BoxesService boxesService;
	
	@Autowired
	public HorarioService horarioService;
	
	@Autowired
	public DisponibleService disponibleService;
	
	@Autowired
	public VentaService ventasService;
	
	@GetMapping("/listar")
	public List<ReservaDetalle> listarReservaDetalle(){
		System.out.println(TimeZone.getDefault());
		return reservaDetalleService.findAll();
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?>  agregarReservaDetalle(@RequestBody ReservaDetalle reservadetalle) {
		reservaDetalleService.addReservaDetalle(reservadetalle);
		return new ResponseEntity<ReservaDetalle>(reservadetalle, HttpStatus.OK);
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
		Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		return (List<ReservaDetalle>) reservaDetalleService.findAllByEmpleadoAndFechaReservaOrderByHoraAsc(empleado, fecha);
	}
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificarReservaDetalle(@PathVariable(value="id") Integer id, @RequestBody String valorEstado) {
		ReservaDetalle c = reservaDetalleService.findByReservaDetalleId(id);
		if(c!=null) {
			c.setEstado(valorEstado);
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
	
	
	@GetMapping("/busqueda-reservas/{id}")
	public List<ReservaDetalle> busquedaReservas(@PathVariable(value="id") String termino)  {
		return (List<ReservaDetalle>) reservaDetalleService.busquedaReservas(termino);
	}
	
	@GetMapping("/servicios-re-by-usuario/{id}")
	public List <ServiciosReservadosPorClienteFecha> getServiciosReservadosPorClienteFecha(@PathVariable(value="id") Integer usuarioId) {
		return (List <ServiciosReservadosPorClienteFecha>) reservaDetalleService.serviciosReservadosPorClienteFecha(usuarioId);
	}
	
	@GetMapping("/encontrar-reservas-fecha-hora/{fecha}/{hora}")
	public List<ReservaDetalle> findByFechaReservaAndHora(
			@PathVariable(value="fecha") String fecha
			,@PathVariable(value="hora") String hora ) throws ParseException  {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fetch = sdf.parse(fecha);
		
		
		LocalTime t = LocalTime.parse(hora ) ;
		Time time = Time.valueOf( t );
		
		return (List<ReservaDetalle>) reservaDetalleService.findByFechaReservaAndHora(fetch, time);
	}
	
    @GetMapping("/reporte/{fecha}")
    public void generateReport(@PathVariable(value="fecha")  String fecha) throws FileNotFoundException, JRException, ParseException {
        
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fech = sdf.parse(fecha);
		
        String r = reservaDetalleService.exportReport(fech);
        System.out.print(r);
    }
    
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename){
        Resource file = reservaDetalleService.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\""+file.getFilename() + "\"").body(file);
    }
	
    
    @GetMapping("/reservas-confirmadas/{id}/{mes}")
	public List<ReservaDetalle> reservasConfirmadas(@PathVariable(value="id") Integer empleadoId, @PathVariable(value="mes") Integer mes)  {
		return (List<ReservaDetalle>) reservaDetalleService.reservasConfirmadas(empleadoId, mes);
	}
    
    @GetMapping("/mis-reservas/{id}")
	public List<ReservaDetalle> misReservas(@PathVariable(value="id") Integer usuarioId){
		return reservaDetalleService.misReservas(usuarioId);
		
	}

	@GetMapping("/cambiar-estado-pagado/{reservaId}")
	public void cambiarEstadoPagado(@PathVariable Integer reservaId){	
		ReservaDetalle r = reservaDetalleService.findByReservaDetalleId(reservaId);
		r.setEstado("Pagado");
		reservaDetalleService.updateReserva(r);
	}
	
	@GetMapping("/asignar-venta/{reservaId}/{ventasId}")
	public void relacionarCobranza(@PathVariable Integer reservaId, @PathVariable Integer ventasId){	
		ReservaDetalle r = reservaDetalleService.findByReservaDetalleId(reservaId);
		Ventas v = ventasService.findByVentasId(ventasId);
		r.setVentasId(v);
		reservaDetalleService.updateReserva(r);
	}
	
}
