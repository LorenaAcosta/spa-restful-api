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


import py.com.spa.app.entities.Disponible;
import py.com.spa.app.entities.Servicios;
import py.com.spa.app.services.DisponibleService;
import py.com.spa.app.services.ServicioService;

@RestController
@RequestMapping("/disponible")
public class DisponibleRESTController {
	

	@Autowired
	private DisponibleService disponibleService;
	@Autowired
	private ServicioService servicioService;
	
	@GetMapping("/listar")
	public List<Disponible> listarDisponible(){
		return disponibleService.findAll();
	}
	
	@PostMapping("/agregar")
	public void agregarDisponible(@RequestBody Disponible disponible) {
		disponibleService.addDisponible(disponible);
	}

	
	@GetMapping("/obtener-empleados-disponibles/{id}")
	public List<Disponible> findAllByServicioId(@PathVariable Integer id)
	{
		
		Servicios s = servicioService.findServicioById(id);
		
		return (List<Disponible>) disponibleService.findAllByCategoriaId(s);
	}
	

	
	@GetMapping("/encontrar/{id}")
	public Disponible encontrarProducto(@PathVariable Integer id) {
		return (Disponible) disponibleService.findByDisponibleId(id);
	}  
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminarDiponible(@PathVariable(value="id") Integer id) {
		disponibleService.deleteCategoria(id);
	}
	
	

	
	
}
