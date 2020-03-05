package py.com.spa.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Clientes;
import py.com.spa.app.entities.TiposProducto;
import py.com.spa.app.services.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaRESTController  {
	
	@Autowired
	public CategoriaService categoriaService;
	
	@GetMapping("/listar")
	public List<Categorias> listarCategorias(){
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
	public void modificarCategoria (@PathVariable(value="id") Integer id, @RequestBody Categorias categoria) {
		Categorias c = categoriaService.findByCategoriaId(id);
		if(c!=null) {
			c.setDescripcion(categoria.getDescripcion());
			c.setCodigo(categoria.getCodigo());
			categoriaService.updateCategoria(categoria);
		}
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminarCategoria(@PathVariable(value="id") Integer id) {
		categoriaService.deleteCategoria(id);
	}


}
