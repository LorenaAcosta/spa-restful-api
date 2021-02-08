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
import py.com.spa.app.entities.Compras;
import py.com.spa.app.entities.ComprasDetalle;
import py.com.spa.app.services.CategoriaService;
import py.com.spa.app.services.CompraDetalleService;
import py.com.spa.app.services.CompraService;
import py.com.spa.app.services.ProductoService;
import py.com.spa.app.services.VentaService;

@RestController
@RequestMapping("/detalles-compra")
public class CompraDetalleRESTController {

	@Autowired
	public CompraDetalleService compraDetalleService;
	
	@Autowired
	public CompraService compraService;
	
	@Autowired
	public ProductoService productoService;
	
	@GetMapping("/listar")
	public List<ComprasDetalle> listarDetalles(){
		return compraDetalleService.findAll();
	}
	
	
	@PostMapping("/agregar")
	public void agregarDetalle(@RequestBody ComprasDetalle detalle) {
		compraDetalleService.addDetalle(detalle);
	}
	
	@GetMapping("/encontrar/{id}")
	public ComprasDetalle obtenerComprasId(@PathVariable(value="id") Integer id) {
		return (ComprasDetalle) compraDetalleService.findByCompraDetalleId(id);
	}
	
	@GetMapping("/encontrar-detalles/{id}")
	public List<ComprasDetalle> obtenerPorComprasId(@PathVariable Integer id) {
		Compras c = compraService.findByComprasId(id);
		return (List<ComprasDetalle>) compraDetalleService.findByCompra(c);
	}
	

	
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificarDetalle(@PathVariable(value="id") Integer id, @RequestBody ComprasDetalle detalle) {
		ComprasDetalle cd = compraDetalleService.findByCompraDetalleId(id);
		if(cd!=null) {
			cd.setCantidad(detalle.getCantidad());
			cd.setPrecioCompra(detalle.getPrecioCompra());
			cd.setCompras(detalle.getCompras());
			//-c.setEstado(compra.getEstado());
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarVenta(@PathVariable(value="id") Integer id) {
		ComprasDetalle cd = compraDetalleService.findByCompraDetalleId(id);
		if (cd!=null) {
			compraDetalleService.deleteDetalles(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
	}
	
}
