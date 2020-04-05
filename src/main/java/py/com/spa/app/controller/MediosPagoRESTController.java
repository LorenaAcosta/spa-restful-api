package py.com.spa.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.com.spa.app.entities.MediosPago;
import py.com.spa.app.services.MediosPagoService;

@RestController
@RequestMapping("/medios-pago")
public class MediosPagoRESTController {
	
	@Autowired
	public MediosPagoService mediosPagoService;
	
	@GetMapping("/listar")
	public List<MediosPago> listarMediosPago(){
		return mediosPagoService.findAll();
	}
	
	@PostMapping("/agregar")
	public void agregarCategoria(@RequestBody MediosPago mediosPago) {
		mediosPagoService.addMediosPago(mediosPago);
	}
	
	@GetMapping("/encontrar/{id}")
	public MediosPago obtenerMedioPagoId(@PathVariable(value="id") Integer id) {
		return (MediosPago) mediosPagoService.findByMedioPagoId(id);
	}
	
	@PutMapping("/modificar/{id}")
	public void modificarMedioPago(@PathVariable(value="id") Integer id, @RequestBody MediosPago mediosPago) {
		MediosPago c = mediosPagoService.findByMedioPagoId(id);
		if (c != null) {
			c.setCodigo(mediosPago.getCodigo()); 
			c.setDescripcion(mediosPago.getDescripcion());
			mediosPagoService.updateMedioPago(c);
		}
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminarMediosPago(@PathVariable(value="id") Integer id) {
		mediosPagoService.deleteTiposProducto(id);
	}
	
	
	
}
