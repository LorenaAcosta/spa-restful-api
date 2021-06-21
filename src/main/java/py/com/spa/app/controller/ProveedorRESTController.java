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
import py.com.spa.app.entities.Proveedor;
import py.com.spa.app.services.ProveedorService;

@RestController
@RequestMapping("/proveedor")
@CrossOrigin(origins = "*")
public class ProveedorRESTController {
	@Autowired
	public ProveedorService proveedorService;
	
	@GetMapping("/listar")
	public List<Proveedor> listarProveedor(){
		return proveedorService.findAll();
	}

	
	@PostMapping("/agregar")
	public  ResponseEntity<?> agregarCategoria(@RequestBody Proveedor pv) {
		Proveedor proveedor = null;
		Map<String, Object> response = new HashMap<>();
		try {
			proveedor = proveedorService.add(pv);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar el insert en la bd");
			response.put("error", e.getMostSpecificCause().getMessage() );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El cliente ha sido creado con exito.");
		response.put("categoria", proveedor);
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);	
	}
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificarCategoria (@PathVariable(value="id") Integer id, @RequestBody Proveedor proveedor) {
		Proveedor c = proveedorService.findById(id);
		Proveedor nuevo =  null;
		Map<String, Object> response = new HashMap<>();
		if (c == null) {
			response.put("mensaje",  "Error, No se pudo editar. La categoria no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			c.setNombreProveedor(proveedor.getNombreProveedor());
			c.setTelefono(proveedor.getTelefono());
			c.setEmpresa(proveedor.getEmpresa());
			nuevo = proveedorService.update(c);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error", e.getMostSpecificCause().getMessage() );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Proveedor actualizado");
		response.put("categoria", nuevo);
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);	
	}

	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarCategoria(@PathVariable(value="id") Integer id) {
		Proveedor c = proveedorService.findById(id);
		Map<String, Object> response = new HashMap<>();
		if (c == null) {
			response.put("mensaje",  "Error, No se pudo eliminar. La categoria no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			proveedorService.delete(id);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error", e.getMostSpecificCause().getMessage() );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	
	}
	
	
	@GetMapping("/encontrar/{id}")
	public Proveedor obtenerProveedorId(@PathVariable(value="id") Integer id) {
		return (Proveedor) proveedorService.findById(id);
	}
	
	
	
	@GetMapping("/busqueda-proveedores/{id}")
	public ResponseEntity<?>  busquedaProveedores(@PathVariable(value="id") String termino)  {
		List<Proveedor> lista = null;
		Map<String, Object> response = new HashMap<>();
		try {
			lista= proveedorService.busquedaProveedores(termino);
		}catch( DataAccessException e ){
			response.put("mensaje",  "No se encontraron datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (lista==null) {
			response.put("mensaje",  "No hay datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Proveedor>>(lista, HttpStatus.OK);
	}
	
    @GetMapping("/reporte")
    public void generateReport() throws FileNotFoundException, JRException {
        String r = proveedorService.exportReport();
        System.out.print(r);
    }
	
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename){
        Resource file = proveedorService.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\""+file.getFilename() + "\"").body(file);
    }

}
