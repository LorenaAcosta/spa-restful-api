package py.com.spa.app.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.sf.jasperreports.engine.JRException;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.reportes.Datos;
import py.com.spa.app.services.CategoriaService;
import py.com.spa.app.services.ServicioService;

@RestController
@RequestMapping("/categoria" )
@CrossOrigin(origins = "*")
public class CategoriaRESTController  {
	
	@Autowired
	public CategoriaService categoriaService;
	
	@Autowired
	public ServicioService servicioService;

	
	@GetMapping("/listar")
	public List<Categorias> listarCategorias(){
		return categoriaService.findAll();
	}
	
	@GetMapping("/paginado")
	public Page<Categorias> listarCategorias(@RequestParam(name="page", defaultValue="0") int page, @RequestParam(name="elementos", defaultValue="5") int elementos, @RequestParam(name="filtro", defaultValue="filtro") String ordenadoPor, @RequestParam(name="orden", defaultValue="asc") String orden){
		Pageable pageRequest = null;
		if (orden.equalsIgnoreCase("asc")) {
			pageRequest = PageRequest.of(page, elementos, Sort.by(ordenadoPor).ascending());
		} else if (orden.equalsIgnoreCase("desc")) {
			pageRequest = PageRequest.of(page, elementos, Sort.by(ordenadoPor).descending());
		} else {
			pageRequest = PageRequest.of(page, elementos, Sort.by(ordenadoPor).descending());
		}
		//Pageable pageRequest = PageRequest.of(page, elementos, Sort.by(ordenadoPor).descending());
		return categoriaService.findAll(pageRequest);
	}
	
	@GetMapping("/descripcion")
	public List<String> listarServiciosPorDescripcion(){
		return categoriaService.findAllByDescripcion();
	}
	
	@GetMapping("/getServicios")
	public List<Categorias> getServicios(){
		return categoriaService.findAll();
	}
	
	//@RequestMapping(value="categoria", produces = "application/json", consumes = "multipart/form-data")
	@PutMapping("/upload")
	public void cargarImagen( @RequestParam("file") MultipartFile imagen ) {
		Categorias categoria = categoriaService.findByCategoriaId(1);
		if (!imagen.isEmpty()) {
			Path directorioImagenes = Paths.get("src//main//resources//img");
			String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			
			try {
				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//"+ imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
				
				//categoria.setImagen(imagen.getOriginalFilename());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		categoriaService.addCategoria(categoria);
	}
	
	@GetMapping("/encontrar/{id}")
	public Categorias encontrarCategoria(@PathVariable Integer id) {
		return (Categorias) categoriaService.findByCategoriaId(id);
	}
	
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregarCategoria(@RequestBody Categorias  c) {
	
		Categorias categoria = null;
		Map<String, Object> response = new HashMap<>();
		try {
			categoria= categoriaService.addCategoria(c);
			
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar el insert en la bd");
			response.put("error",  e.getMostSpecificCause().getMessage()   )  ;
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El cliente ha sido creado con exito.");
		response.put("categoria", categoria);
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);		
	} 
		

	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificarCategoria (@PathVariable(value="id") Integer id, @RequestBody Categorias categoria) {
		Categorias c = categoriaService.findByCategoriaId(id);
		Categorias nuevo =  null;
		Map<String, Object> response = new HashMap<>();
		
		if (c == null) {
			response.put("mensaje",  "Error, No se pudo editar. La categoria no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			c.setDescripcion(categoria.getDescripcion());
			//c.setCodigo(categoria.getCodigo());
			c.setImageName(categoria.getImageName());
			c.setDataType(categoria.getDataType());
			nuevo = categoriaService.updateCategoria(c);
			
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error",  e.getMostSpecificCause().getMessage()   )  ;
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La categoria ha sido actualizada.");
		response.put("categoria", nuevo);
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);		
	}
	
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarCategoria(@PathVariable(value="id") Integer id) {
		Map<String, Object> response = new HashMap<>();
		Categorias c = categoriaService.findByCategoriaId(id);

		if (c == null) {
			response.put("mensaje",  "Error, No se pudo eliminar. La categoria no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			categoriaService.deleteCategoria(id);
		
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error",  e.getMostSpecificCause().getMessage()   )  ;
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}
	

    @GetMapping("/reporte")
    public void generateReport() throws FileNotFoundException, JRException {
        String r = categoriaService.exportReport();
        System.out.print(r);
    }

	
	
	@GetMapping("/obtener-por-tipo/{id}")
	public ResponseEntity<?> obtenerCategorias(@PathVariable(value="id") String id) {
		List<Categorias> lista = null;
		Map<String, Object> response = new HashMap<>();
		try {
			lista= categoriaService.obtenerCategorias(id);
		}catch( DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error",  e.getMostSpecificCause().getMessage())  ;
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (lista==null) {
			response.put("mensaje",  "No hay datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Categorias>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/find-categoria/{id}")
	public ResponseEntity<?> findByDataTypee(@PathVariable(value="id") String id) {
		List<Categorias> lista = null;
		Map<String, Object> response = new HashMap<>();
		try {
			
			lista= categoriaService.findByDataTypee(id);
		}catch( DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error",  e.getMostSpecificCause().getMessage() )  ;
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (lista==null) {
			response.put("mensaje",  "No hay datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Categorias>>(lista, HttpStatus.OK);
	}
	

	@GetMapping("/busqueda-categorias/{id}")
	public ResponseEntity<?>  busquedaCategorias(@PathVariable(value="id") String termino)  {
		List<Categorias> lista = null;
		Map<String, Object> response = new HashMap<>();
		try {
			lista= categoriaService.busquedaCategorias(termino);
		}catch( DataAccessException e ){
			response.put("mensaje",  "No se encontraron datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (lista==null) {
			response.put("mensaje",  "No hay datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Categorias>>(lista, HttpStatus.OK);
	}
	
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename){
        Resource file = categoriaService.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\""+file.getFilename() + "\"").body(file);
    }
    
    
    @GetMapping("/datos")
	public List<Datos> getdatos(){
		return categoriaService.getdatos();
	}

}
