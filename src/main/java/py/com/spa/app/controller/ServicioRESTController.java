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
import py.com.spa.app.entities.Servicios;
import py.com.spa.app.services.CategoriaService;
import py.com.spa.app.services.ServicioService;

@RestController
@RequestMapping("/servicio")
public class ServicioRESTController {
	

	@Autowired
	private ServicioService servicioService;
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/listar")
	public List<Servicios> listarServicios(){
		return servicioService.findAll();
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregarServicio(@RequestBody Servicios p) {
		servicioService.agregarServicio(p);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping("/getServciosByCategoriaId/{id}")
	public List<Servicios> getServciosByCategoriaId(@PathVariable Integer id)
	{
		Categorias c = categoriaService.findByCategoriaId(id);	
		return (List<Servicios>) servicioService.findAllByCategoriaId(c);
	}
	
	@GetMapping("/getServiciosByEstado/{estado}")
	public List<Servicios> getServiciosByEstado(@PathVariable String estado)
	{
		return (List<Servicios>) servicioService.getServiciosByEstado(estado);
	}
	
	@GetMapping("/getServiciosActivos/{categoriaId}/{estado}")
	public List<Servicios> findByCategoriaIdAndEstado(@PathVariable(value="categoriaId")  Integer categoriaId, @PathVariable(value="estado") String estado )
	{
		Categorias c = categoriaService.findByCategoriaId(categoriaId);
		return (List<Servicios>) servicioService.findAllByCategoriaIdAndEstado(c, estado);
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
	public void modificarProducto(@PathVariable Integer id, @RequestBody Servicios servicio) {
		Servicios s= servicioService.findServicioById(id);
		if (s!=null) {
			s.setNombre(servicio.getNombre());
			s.setDescripcion(servicio.getDescripcion());
			s.setCategoriaId(servicio.getCategoriaId());
			s.setEstado(servicio.getEstado());
			s.setDuracion(servicio.getDuracion());
			s.setCosto(servicio.getCosto());
			servicioService.updateServicio(s);
		}	
	} 
	
	
	
}
