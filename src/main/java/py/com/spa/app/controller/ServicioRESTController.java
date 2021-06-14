package py.com.spa.app.controller;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
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
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Productos;
import py.com.spa.app.entities.Servicios;
import py.com.spa.app.services.CategoriaService;
import py.com.spa.app.services.ServicioService;

@RestController
@RequestMapping("/servicio")
@CrossOrigin(origins = "*")
public class ServicioRESTController {
	

	@Autowired
	private ServicioService servicioService;
	@Autowired
	private CategoriaService categoriaService;
	
	
	@GetMapping("/listar")
	public List<Servicios> listarServicios(){
		return servicioService.findAll();
	}
	
	
	@GetMapping("/listarActivos")
	public List<Servicios> listarServiciosActivos(){
		return servicioService.findActivos();
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregarServicio(@RequestBody Servicios p) {
		Map<String, Object> response = new HashMap<>();
		Servicios servicio = null;
		try {
			servicio= servicioService.guardarServicio(p);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar el insert en la bd");
			response.put("error",  e.getMostSpecificCause().getMessage()   );
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
			s.setNombre(      servicio.getNombre());
			s.setDescripcion( servicio.getDescripcion());
			s.setCategoriaId( servicio.getCategoriaId());
			s.setImpuestoId( servicio.getImpuestoId());
			s.setEstado(      servicio.getEstado());
			s.setDuracion(    servicio.getDuracion());
			s.setCosto(       servicio.getCosto());
			nuevo = servicioService.updateServicio(s);
		} catch (DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error", e.getMostSpecificCause().getMessage() );
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
			response.put("error", e.getMostSpecificCause().getMessage() );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	
	@GetMapping("/getServciosByCategoriaId/{id}")
	public ResponseEntity<?> getServciosByCategoriaId(@PathVariable Integer id)
	{
		Map<String, Object> response = new HashMap<>();
		List<Servicios> lista = null;
		Categorias c = categoriaService.findByCategoriaId(id);
		try {
			lista= servicioService.findAllByCategoriaId(c);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error", e.getMostSpecificCause().getMessage() );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (lista==null) {
			response.put("mensaje",  "No hay datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Servicios>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/getServiciosByEstado/{estado}")
	public ResponseEntity<?>  getServiciosByEstado(@PathVariable(value="estado") String estado){
		List<Servicios> lista = null;
		Map<String, Object> response = new HashMap<>();
		try {
			lista = servicioService.getServiciosByEstado(estado);
		}
		catch(DataAccessException e ){
		response.put("mensaje",  "Error al realizar la consulta");
		response.put("error", e.getMostSpecificCause().getMessage() );
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (lista==null) {
			response.put("mensaje",  "No hay datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Servicios>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/getServiciosActivos/{categoriaId}")
	public  ResponseEntity<?> getServiciosActivos(@PathVariable(value="categoriaId")  Integer categoriaId)
	{
		
		Map<String, Object> response = new HashMap<>();
		List<Servicios> lista = null;
		Categorias c = categoriaService.findByCategoriaId(categoriaId);
		try {
			lista= servicioService.getServiciosActivos(c);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error", e.getMostSpecificCause().getMessage() );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (lista==null) {
			response.put("mensaje",  "No hay datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Servicios>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/encontrar/{id}")
	public  ResponseEntity<?>  encontrarProducto(@PathVariable Integer id) {
		Servicios service = null;
		Map<String, Object> response = new HashMap<>();
		try {
			service = servicioService.findServicioById(id);
		}catch( DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error", e.getMostSpecificCause().getMessage() );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (service==null) {
			response.put("mensaje",  "No hay datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Servicios>(service, HttpStatus.OK);
	}


	@GetMapping("/get-servicios-disponibles/{id}")
	public  ResponseEntity<?>  getServiciosDisponibles(@PathVariable(value="id") Integer empleadoId) {
		List<Servicios> servicios = null;
		Map<String, Object> response = new HashMap<>();
		try {
			
			servicios = (List<Servicios>)servicioService.getServiciosDisponibles(empleadoId);
		}catch( DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error", e.getMostSpecificCause().getMessage() );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (servicios==null) {
			response.put("mensaje",  "No hay datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<	List<Servicios> >(	servicios, HttpStatus.OK);
	}
	
	@GetMapping("/busqueda-servicios/{id}")
	public ResponseEntity<?>  busquedaServicios(@PathVariable(value="id") String termino)  {
		List<Servicios> lista = null;
		Map<String, Object> response = new HashMap<>();
		try {
			lista= servicioService.busquedaServicios(termino);
		}catch( DataAccessException e ){
			response.put("mensaje",  "No se encontraron datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (lista==null) {
			response.put("mensaje",  "No hay datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Servicios>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/servicio-por-nombre/{id}")
	public Servicios busquedaPorNombre(@PathVariable(value="id") String nombre) {
		return (Servicios) servicioService.busquedaPorNombre(nombre);
	}
	
	
	
    @GetMapping("/reporte")
    public void generateReport() throws FileNotFoundException, JRException {
        String r = servicioService.exportReport();
        System.out.print(r);
    }
	
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename){
        Resource file = servicioService.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\""+file.getFilename() + "\"").body(file);
    }
}
