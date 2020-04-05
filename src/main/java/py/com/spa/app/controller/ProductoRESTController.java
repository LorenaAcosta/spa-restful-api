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
import py.com.spa.app.entities.Productos;
import py.com.spa.app.services.ProductoService;
import py.com.spa.params.PaginadoParam;
import py.com.spa.result.PaginadoResult;

@RestController
@RequestMapping("/producto")
public class ProductoRESTController {
	

	@Autowired
	private ProductoService productoService;
	
	
	@PostMapping("/agregar")
	public void agregarProducto(@RequestBody Productos producto) {
		productoService.saveProducto(producto);
	}

	@GetMapping("/listar")
	public ResponseEntity<?> listarProductos(){
		List<Productos> productos = productoService.findAll();
		if ( productos!= null ) {
			if (productos.size()!=0) {
			return new ResponseEntity<>( productos, HttpStatus.OK);
			}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/encontrar/{id}")
	public Productos encontrarProducto(@PathVariable Integer id) {
		return (Productos) productoService.findProductoById(id);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminarProducto(@PathVariable(value="id") Integer id) {
		productoService.deleteProducto(id);
	}
	
	
	@PutMapping("/modificar/{id}")
	public void modificarProducto(@PathVariable Integer id, @RequestBody Productos producto) {
		Productos prod= null;
		prod = productoService.findProductoById(id);
		if (prod!=null) {
			prod.setCodigo(producto.getCodigo());
			prod.setCosto(producto.getCosto());
			prod.setDescripcion(producto.getDescripcion());
			prod.setPrecioVenta(producto.getPrecioVenta());
			prod.setStockActual(producto.getStockActual());
			productoService.updateProducto(prod);
		}
		
	} 
	
	@PostMapping("/producto-list")
	public ResponseEntity<?> listarPaginado (@RequestBody PaginadoParam<Productos> productos){
		try {
			PaginadoResult<Productos> clista = productoService.listar(productos);
			if (clista.getLista().size()==0) {
				return new ResponseEntity<PaginadoResult<?>>(clista, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<PaginadoResult<?>>(clista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
	}
	
	
	
}
