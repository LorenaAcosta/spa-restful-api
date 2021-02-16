package py.com.spa.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
	
		Map<String, Object> response = new HashMap<>();
		Servicios servicio = null;
		try {
			servicio= servicioService.guardarServicio(p);
			
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar el insert en la bd");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El servicio ha sido creado con exito.");
		response.put("servicio", servicio);
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);		
		
	} 
	

	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificarProducto(@PathVariable Integer id, @RequestBody Servicios servicio) {
		Servicios s= servicioService.findServicioById(id);
		Servicios nuevo =  null;
		Map<String, Object> response = new HashMap<>();
		if (s == null) {
			response.put("mensaje",  "Error, No se pudo editar. La categoria no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			s.setNombre(servicio.getNombre());
			s.setDescripcion(servicio.getDescripcion());
			s.setCategoriaId(servicio.getCategoriaId());
			s.setEstado(servicio.getEstado());
			s.setDuracion(servicio.getDuracion());
			s.setCosto(servicio.getCosto());
			nuevo = servicioService.updateServicio(s);
		} catch (DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Servicio actualizado.");
		response.put("servicio", nuevo);
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);		
	} 
	

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarProducto(@PathVariable(value="id") Integer id) {
		Map<String, Object> response = new HashMap<>();
		Servicios s = servicioService.findServicioById(id);
		if (s == null) {
			response.put("mensaje",  "Error, No se pudo eliminar. Servicio no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
		  servicioService.deleteServicio(id);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
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
	
	
	
	
}
