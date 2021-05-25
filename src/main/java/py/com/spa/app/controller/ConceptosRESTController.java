package py.com.spa.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Conceptos;
import py.com.spa.app.services.ConceptosService;
import py.com.spa.app.services.ServicioService;

@RestController
@RequestMapping("/conceptos" )
@CrossOrigin(origins = "*")
public class ConceptosRESTController{

	
	@Autowired
	public ConceptosService conceptosService;

	
	@GetMapping("/listar")
	public List<Conceptos> listarConceptos(){
		return conceptosService.findAll();
	}
}
