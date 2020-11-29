package py.com.spa.app.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import py.com.spa.app.dao.ImageRepository;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.ImageModel;
import py.com.spa.app.services.CategoriaService;
import py.com.spa.app.util.Utileria;

@RestController
@RequestMapping("/categoria")
public class CategoriaRESTController  {
	
	@Autowired
	public CategoriaService categoriaService;
	
	@Autowired
	public ImageRepository imageRepository;

	
	@GetMapping("/listar")
	public List<Categorias> listarCategorias(){
		return categoriaService.findAll();
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
	
	@GetMapping("/obtener-por-tipo/{id}")
	public List<Categorias> obtenerCategorias(@PathVariable(value="id") String id) {
		return categoriaService.obtenerCategorias(id);
	}
	

    @PostMapping("/upload")
    public ImageModel uplaodImage(@RequestParam("myFile") MultipartFile file) throws IOException {

        ImageModel img = new ImageModel(file.getOriginalFilename(),file.getContentType(),file.getBytes());
        final ImageModel savedImage = imageRepository.save(img);
        System.out.println("Image saved");
        return savedImage;


    }

}