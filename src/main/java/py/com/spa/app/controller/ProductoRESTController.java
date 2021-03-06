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
import py.com.spa.app.entities.Empleados;
import py.com.spa.app.entities.Productos;
import py.com.spa.app.entities.Servicios;
import py.com.spa.app.services.CategoriaService;
import py.com.spa.app.services.ProductoService;
import py.com.spa.params.PaginadoParam;
import py.com.spa.result.PaginadoResult;

@RestController
@RequestMapping("/producto")
public class ProductoRESTController {
	

	@Autowired
	private ProductoService productoService;
	@Autowired
	private CategoriaService categoriaService;
	
	
	@GetMapping("/listar")
	public List<Productos> listarProductos(){
		return productoService.findAll();
	}
	
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregarProducto(@RequestBody Productos producto) {
		Map<String, Object> response = new HashMap<>();
		Productos productoA = null;
		try {
			productoA = productoService.addProducto(producto);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar el insert en la BD");
			response.put("error",  e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El producto ha sido creado con exito.");
		response.put("producto", productoA);
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);				
	}
	
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?>  modificarProducto(@PathVariable Integer id, @RequestBody Productos producto) {
		Productos prod= productoService.findProductoById(id);
		Productos p =  null;
		Map<String, Object> response = new HashMap<>();
		if (prod == null) {
			response.put("mensaje",  "Error, No se pudo editar. La categoria no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			prod.setDescripcion(    producto.getDescripcion());
			prod.setCosto(          producto.getCosto());
			prod.setPrecioVenta(    producto.getPrecioVenta());
			prod.setStockActual(    producto.getStockActual());
			prod.setCategoriaId(    producto.getCategoriaId());
			prod.setImageName(      producto.getImageName());
			prod.setEstado(         producto.getEstado());
			p = productoService.updateProducto(prod);		
		} catch (DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Producto actualizado.");
		response.put("producto", producto);
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);		
		
	} 
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?>  eliminarProducto(@PathVariable(value="id") Integer id) {
		Map<String, Object> response = new HashMap<>();
		Productos p = productoService.findProductoById(id);

		if (p == null) {
			response.put("mensaje",  "Error, No se pudo eliminar. La categoria no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			 productoService.deleteProducto(id);
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	
	@PostMapping("/productos-list")
	public ResponseEntity<?> listarPaginado (@RequestBody PaginadoParam<Productos> producto){
		try {
			PaginadoResult<Productos> clista = productoService.listar(producto);
			if (clista.getLista().size()==0) {
				return new ResponseEntity<PaginadoResult<?>>(clista, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<PaginadoResult<?>>(clista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/getProductosByCategoriaId/{id}")
	public List<Productos> getServciosByCategoriaId(@PathVariable Integer id)
	{
		Categorias c = categoriaService.findByCategoriaId(id);
		return (List<Productos>) productoService.findAllByCategoriaId(c);
	}
	

	@GetMapping("/encontrar/{id}")
	public Productos obtenerProductosId(@PathVariable(value="id") Integer id) {
		return (Productos) productoService.findProductoById(id);
	}


	

}
