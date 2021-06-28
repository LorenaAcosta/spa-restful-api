package py.com.spa.app.controller;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.com.spa.app.entities.Boxes;
import py.com.spa.app.entities.Carrito;
import py.com.spa.app.entities.CarritoCabecera;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.ReservaDetalle;
import py.com.spa.app.services.BoxesService;
import py.com.spa.app.services.CarritoCabeceraService;
import py.com.spa.app.services.CarritoService;

@RestController
@RequestMapping("/carrito-cabecera" )
@CrossOrigin(origins = "*")
public class CarritoCabeceraRESTController  {
	
	@Autowired
	public CarritoCabeceraService carritoService;
	

	
	@GetMapping("/listar")
	public List<CarritoCabecera> listarCategorias(){
		return carritoService.findAll();
	}
	
	@GetMapping("/getmaxid")
	public Integer getmaxid(){
		return carritoService.getmaxid();
	}
	
	@GetMapping("/encontrar/{id}")
	public CarritoCabecera encontrarCategoria(@PathVariable Integer id) {
		return (CarritoCabecera) carritoService.findByCarritoCabeceraId(id);
	}
	
	  
    @GetMapping("/mis-ordenes/{id}")
	public List<CarritoCabecera> misReservas(@PathVariable(value="id") Integer usuarioId){
		return carritoService.misOrdenes(usuarioId);
	}

	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregarCategoria(@RequestBody CarritoCabecera  c) {
	
		CarritoCabecera carrito = null;
		Map<String, Object> response = new HashMap<>();
		try {
			carrito= carritoService.addCategoria(c);
			
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar el insert en la bd");
			response.put("error",  e.getMostSpecificCause().getMessage()   )  ;
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El carrito ha sido creado con exito.");
		response.put("carrito", carrito);
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);		
	} 
		

	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarCategoria(@PathVariable(value="id") Integer id) {
		Map<String, Object> response = new HashMap<>();
		CarritoCabecera c = carritoService.findByCategoriaId(id);

		if (c == null) {
			response.put("mensaje",  "Error, No se pudo eliminar. La categoria no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			carritoService.deleteCategoria(id);
			
		}catch(DataAccessException e ){
			response.put("mensaje",  "Error al realizar la consulta");
			response.put("error",  e.getMostSpecificCause().getMessage()   )  ;
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}
	
	

}
