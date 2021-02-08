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
	public void agregarProducto(@RequestBody Productos producto) {
		productoService.addProducto(producto);
	}

	@GetMapping("/encontrar/{id}")
	public Productos obtenerProductosId(@PathVariable(value="id") Integer id) {
		return (Productos) productoService.findProductoById(id);
	}

	@GetMapping("/getProductosByCategoriaId/{id}")
	public List<Productos> getServciosByCategoriaId(@PathVariable Integer id)
	{
		
		Categorias c = categoriaService.findByCategoriaId(id);
		
		return (List<Productos>) productoService.findAllByCategoriaId(c);
	}
	
	@PutMapping("/modificar/{id}")
	public void modificarProducto(@PathVariable Integer id, @RequestBody Productos producto) {
		Productos prod= null;
		prod = productoService.findProductoById(id);
		if (prod!=null) {
			prod.setDescripcion(producto.getDescripcion());
			prod.setCosto(producto.getCosto());
			prod.setPrecioVenta(producto.getPrecioVenta());
			prod.setStockActual(producto.getStockActual());
			prod.setCategoriaId(producto.getCategoriaId());
			prod.setImageName(producto.getImageName());
			prod.setEstado(producto.getEstado());
			productoService.updateProducto(prod);
		}
		
	} 
	@DeleteMapping("/eliminar/{id}")
	public void eliminarProducto(@PathVariable(value="id") Integer id) {
		productoService.deleteProducto(id);
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
	

}
