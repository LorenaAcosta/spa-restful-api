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
import py.com.spa.app.entities.Servicios;
import py.com.spa.app.services.ServicioService;

@RestController
@RequestMapping("/servicio")
public class ServicioRESTController {
	

	@Autowired
	private ServicioService servicioService;
	
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregarServicio(@RequestBody Servicios p) {
		servicioService.agregarServicio(p);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping("/listar")
	public ResponseEntity<?> listarServicios(){
		List<Servicios> Servicios = servicioService.findAll();
		if ( Servicios!= null ) {
			if (Servicios.size()!=0) {
			return new ResponseEntity<>( Servicios, HttpStatus.OK);
			}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/encontrar/{id}")
	public Servicios encontrarProducto(@PathVariable Integer id) {
		return (Servicios) servicioService.findServicioById(id);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminarProducto(@PathVariable(value="id") Integer id) {
		servicioService.deleteServicio(id);
	}
	
	
	@PutMapping("/modificar/{id}")
	public void modificarProducto(@PathVariable Integer id, @RequestBody Servicios s) {
		Servicios servicio= servicioService.findServicioById(id);
		if (servicio!=null) {
			s.setNombre(servicio.getNombre());
			s.setDescripcion(servicio.getDescripcion());
			s.setCosto(servicio.getCosto());
			s.setDuracion(servicio.getDuracion());
			s.setEstado(servicio.getEstado());
			s.setFechaVigenciaIni(servicio.getFechaVigenciaIni());
			s.setFechaVigenciaFin(servicio.getFechaVigenciaFin());
			servicioService.updateServicio(s);
		}	
	} 
	
	
	
}
