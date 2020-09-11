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

import py.com.spa.app.entities.Ventas;
import py.com.spa.app.services.CategoriaService;
import py.com.spa.app.services.VentaService;

@RestController
@RequestMapping("/ventas")
public class VentaRESTController {

	@Autowired
	public VentaService ventaService;
	
	@GetMapping("/listar")
	public List<Ventas> listarVentas(){
		return ventaService.findAll();
	}
	
	
	@PostMapping("/agregar")
	public void agregarVenta(@RequestBody Ventas venta) {
		ventaService.addVentas(venta);
	}
	
	@GetMapping("/encontrar/{id}")
	public Ventas obtenerVentasId(@PathVariable(value="id") Integer id) {
		return (Ventas) ventaService.findByVentasId(id);
	}
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificarVenta(@PathVariable(value="id") Integer id, @RequestBody Ventas venta) {
		Ventas c = ventaService.findByVentasId(id);
		if(c!=null) {
			c.setFecha(venta.getFecha());
			c.setMontoTotal(venta.getMontoTotal());
			c.setMedioPagoId(venta.getMedioPagoId());
			c.setEstado(venta.getEstado());
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarVenta(@PathVariable(value="id") Integer id) {
		Ventas c = ventaService.findByVentasId(id);
		if (c!=null) {
			ventaService.deleteVentas(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
	}
	
}
