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

import py.com.spa.app.entities.TiposProducto;
import py.com.spa.app.services.TiposProductoService;

@RestController
@RequestMapping("/tipos-producto")
public class TiposProductoRESTController {
	
	@Autowired
	private TiposProductoService tiposProductoService;
	
	@GetMapping("/listar")
	public List<TiposProducto> getTiposProducto(){
		return (List<TiposProducto>) tiposProductoService.findAll();
	}
	
	@PostMapping("/agregar")
	public void addTiposProducto(@RequestBody TiposProducto tiposProducto) {
		tiposProductoService.addTiposProducto(tiposProducto);
	}
	
	@GetMapping("/encontrar/{id}")
	public TiposProducto obtenerTiposProductoById(@PathVariable(value="id") Integer id) {
		return (TiposProducto) tiposProductoService.findTiposProductoById(id);
	}
	
	@PutMapping("/modificar/{id}")
	public void modificarTiposProducto(@PathVariable(value="id") Integer id, @RequestBody TiposProducto tiposProducto) {
		TiposProducto tp = tiposProductoService.findTiposProductoById(id);
		if (tp!= null) {
			tp.setCodigo(tiposProducto.getCodigo());
			tp.setDescripcion(tiposProducto.getDescripcion());
			tiposProductoService.updateTiposProducto(tp);
			
		}
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminarTiposProducto (@PathVariable(value="id") Integer id) {
		 tiposProductoService.deleteTiposProducto(id);
	}
	

}
