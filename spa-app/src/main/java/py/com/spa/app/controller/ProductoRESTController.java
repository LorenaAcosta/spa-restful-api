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

import py.com.spa.app.dao.ICategoriaDao;
import py.com.spa.app.dao.IProductoDao;
import py.com.spa.app.dao.ITiposProductoDao;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Productos;
import py.com.spa.app.entities.TiposProducto;
import py.com.spa.app.services.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoRESTController {
	

	@Autowired
	private ProductoService productoService;
	
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregarProducto(@RequestBody Productos p) {
		productoService.agregar(p);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
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
	public void modificarProducto(@PathVariable Integer id, @RequestBody Productos p) {
		Productos prod= productoService.findProductoById(id);
		if (prod!=null) {
			p.setDuracion(p.getDuracion());
			p.setCosto(p.getCosto());
			p.setDescripcion(p.getDescripcion());
			p.setEstado(p.getEstado());
			productoService.updateProducto(p);
		}
		
	}
	
	
	
}
