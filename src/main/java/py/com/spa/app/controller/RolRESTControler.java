package py.com.spa.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import py.com.spa.app.entities.Rol;
import py.com.spa.app.services.RolService;
import py.com.spa.app.validators.ApiUnprocessableEntity;


@RestController
@RequestMapping("/roles")
@CrossOrigin(origins = "*")
public class RolRESTControler {

	@Autowired
	public RolService rolService;

	@GetMapping("/listar")
	public List<Rol> listarusuarios() {
		return rolService.findAll();
	}
	
	@GetMapping("/listar-usuario/{id}")
	public List<Rol> listarRolPorUsuario(@PathVariable(value = "id") Integer id) {
		return rolService.listarRolPorUsuario(id);
	}
	
	@GetMapping("/listar-usuario-na/{id}")
	public List<Rol> listarRolNoAsignadosPorUsuario(@PathVariable(value = "id") Integer id) {
		return rolService.listarRolNoAsignadosPorUsuario(id);
	}

	@PostMapping("/agregar")
	public ResponseEntity<?> agregarRol(@RequestBody Rol rol) throws ApiUnprocessableEntity  {
		
		rolService.addRol(rol);
		
		return ResponseEntity.ok(Boolean.TRUE);
	}
	

	@GetMapping("/encontrar/{id}")
	public Rol obtenerRolesId(@PathVariable(value = "id") Integer id) {
		return (Rol) rolService.findByRolId(id);
	}

	@PutMapping("/modificar/{id}")
	public ResponseEntity<Void> modificarRol(@PathVariable(value = "id") Integer rolId,
			@RequestBody Rol rol) {
		Rol us = null;
		us = rolService.findByRolId(rolId);

		if (us != null) {
			us.setNombre(rol.getNombre());
			rolService.addRol(us);
			return new ResponseEntity<Void>(HttpStatus.OK);

		} else {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarusuario(@PathVariable(value = "id") Integer id) {
		Rol c = rolService.findByRolId(id);
		if (c != null) {
			rolService.deleteRol(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PostMapping("/asignar-rol/{usuarioId}/{rolId}")
	public void asignarRol(@PathVariable Integer usuarioId, @PathVariable Integer rolId){
		
		rolService.insertUsuariosRoles(usuarioId, rolId);
	}
	
	@DeleteMapping("/eliminar/rol-asignado/{usuarioId}/{rolId}")
	public void eliminarRolAsignado(@PathVariable Integer usuarioId, @PathVariable Integer rolId){
		
		rolService.deleteUsuariosRoles(usuarioId, rolId);
	}

}
