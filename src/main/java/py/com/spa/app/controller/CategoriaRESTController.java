package py.com.spa.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Compras;
import py.com.spa.app.services.CategoriaService;
import py.com.spa.params.PaginadoParam;
import py.com.spa.result.PaginadoResult;

@RestController
@RequestMapping("/categoria" )
@CrossOrigin(value = "*")
public class CategoriaRESTController  {
	
	@Autowired
	public CategoriaService categoriaService;
	
	@GetMapping("/listar")
	public List<Categorias> listarCategorias(){
		return categoriaService.findAll();
	}
	
	@GetMapping("/obtener-por-tipo/{tipo}")
	public List<Categorias> obtenerPorTipo(@PathVariable(value="tipo") String tipo){
		return (List<Categorias>) categoriaService.obtenerCategorias(tipo);
	}
	
	@GetMapping("/getServicios")
	public List<Categorias> getServicios(){
		return categoriaService.findAll();
	}
	
	//@RequestMapping(value="categoria", produces = "application/json", consumes = "multipart/form-data")
	@PutMapping("/upload")
	public void cargarImagen( @RequestParam("file") MultipartFile imagen ) {
		Categorias categoria = categoriaService.findByCategoriaId(1);
		if (!imagen.isEmpty()) {
			Path directorioImagenes = Paths.get("src//main//resources//img");
			String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			
			try {
				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//"+ imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
				
				categoria.setImagen(imagen.getOriginalFilename());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		categoriaService.addCategoria(categoria);
	}
	
	//@RequestMapping(value="categoria", produces = "application/json", consumes = "multipart/form-data")
	@PostMapping(value="/agregar" )
	public ResponseEntity<?> agregarCategoria(@RequestBody Categorias categoria ) {
		
		categoriaService.addCategoria(categoria);
		return new ResponseEntity<Categorias>(categoria, HttpStatus.OK);
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
			c.setDataType(categoria.getDataType());
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