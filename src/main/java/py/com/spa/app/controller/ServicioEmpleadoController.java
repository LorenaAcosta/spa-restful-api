/*package py.com.spa.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import py.com.spa.app.dao.IServicioEmpleadoDao;
import py.com.spa.app.entities.ServiciosEmpleados;


public class ServicioEmpleadoController {
	@Autowired
	public IServicioEmpleadoDao serEmpService;
	
	@GetMapping("/listar")
	public List<ServiciosEmpleados> listarCategorias(){
		return serEmpService.findAll();
	}
	
	@PostMapping("/agregar")
	public void agregar(@RequestBody ServiciosEmpleados categoria) {
		serEmpService.save(categoria);
	}
	

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable(value="id") Integer id) {
		ServiciosEmpleados c = serEmpService.findById(id).orElse(null);
		if (c!=null) {
			serEmpService.delete(c);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
	}
}*/
