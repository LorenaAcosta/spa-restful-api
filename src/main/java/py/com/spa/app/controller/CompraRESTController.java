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
import py.com.spa.app.services.CategoriaService;
import py.com.spa.app.services.CompraService;
import py.com.spa.app.services.VentaService;

@RestController
@RequestMapping("/compras")
public class CompraRESTController {

	@Autowired
	public CompraService compraService;
	
	@GetMapping("/listar")
	public List<Compras> listarCompras(){
		return compraService.findAll();
	}
	
	
	/*@PostMapping("/agregar")
	public void agregarCompra(@RequestBody Compras venta) {
		compraService.addCompras(venta);
	}*/
	@PostMapping("/agregar")
	public ResponseEntity<?> agregarCompra(@RequestBody Compras compra) {
		compraService.addCompras(compra);
		return new ResponseEntity<Compras>(compra, HttpStatus.OK);
	}
	
	
	@GetMapping("/encontrar/{id}")
	public Compras obtenerComprasId(@PathVariable(value="id") Integer id) {
		return (Compras) compraService.findByComprasId(id);
	}
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificarCompra(@PathVariable(value="id") Integer id, @RequestBody Compras compra) {
		Compras c = compraService.findByComprasId(id);
		if(c!=null) {
			c.setFecha(compra.getFecha());
			c.setMontoTotal(compra.getMontoTotal());
			c.setProveedorId(compra.getProveedorId());
			compraService.updateCompras(c);
			//-c.setEstado(compra.getEstado());
			return new ResponseEntity<Compras>(compra, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarVenta(@PathVariable(value="id") Integer id) {
		Compras c = compraService.findByComprasId(id);
		if (c!=null) {
			compraService.deleteCompras(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
	}
	
}
