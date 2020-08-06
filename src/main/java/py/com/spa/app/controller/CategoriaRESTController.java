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
import py.com.spa.app.entities.Clientes;
import py.com.spa.app.services.CategoriaService;
import py.com.spa.params.PaginadoParam;
import py.com.spa.result.PaginadoResult;

@RestController
@RequestMapping("/categoria")
public class CategoriaRESTController  {
	
	@Autowired
	public CategoriaService categoriaService;
	
	@GetMapping("/listar")
	public List<Categorias> listarCategorias(){
		return categoriaService.findAll();
	}
	
	@GetMapping("/getDataType/{dataType}")
	public List<Categorias> getDataType(@PathVariable(value="dataType") String dataType){
		return (List<Categorias>) categoriaService.findByDataType(dataType);
	}

	
	
	@GetMapping("/getServicios")
	public List<Categorias> getServicios(){
		return categoriaService.findAll();
	}
	
	@PostMapping("/agregar")
	public void agregarCategoria(@RequestBody Categorias categoria) {
		categoriaService.addCategoria(categoria);
	}
	
	@GetMapping("/encontrar/{id}")
	public Categorias obtenerCategoriasId(@PathVariable(value="id") Integer id) {
		return (Categorias) categoriaService.findByCategoriaId(id);
	}
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificarCategoria (@PathVariable(value="id") Integer id, @RequestBody Categorias categoria) {
		Categorias c = categoriaService.findByCategoriaId(id);
		if(c!=null) {
			c.setDescripcion(categoria.getDescripcion());
			c.setCodigo(categoria.getCodigo());
			c.setImageName(categoria.getImageName());
			categoriaService.updateCategoria(c);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarCategoria(@PathVariable(value="id") Integer id) {
		Categorias c = categoriaService.findByCategoriaId(id);
		if (c!=null) {
			categoriaService.deleteCategoria(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
	}
}
