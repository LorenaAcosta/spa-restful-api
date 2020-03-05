package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import py.com.spa.app.dao.ICategoriaDao;
import py.com.spa.app.dao.IProductoDao;
import py.com.spa.app.dao.ITiposProductoDao;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Clientes;
import py.com.spa.app.entities.Productos;
import py.com.spa.app.entities.TiposProducto;

@Service
public class ProductoService {
	
	@Autowired
	private IProductoDao productoDao;
	
	@Autowired
	private ICategoriaDao categoriaDao;
	
	@Autowired
	private ITiposProductoDao tipoProdDao;
	
	@Transactional(readOnly=true)
	public List<Productos> findAll(){
		return (List<Productos>) productoDao.findAll(); 
	}
	
	
	@Transactional(readOnly=true)
	public Productos findProductoById(Integer id){
		return (Productos) productoDao.findById(id).orElse(null);
	}
	
	@Transactional
	public ResponseEntity<?> agregar(Productos prod){
		TiposProducto tp = tipoProdDao.findById(prod.getTipoProductoId().getTipoProductoId()).orElse(null);
		Categorias cat = categoriaDao.findById( prod.getCategoriaId().getCategoriaId()).orElse(null);
		
		System.out.println("TIPO-PROD"+tp);
		System.out.println("CATEGORIA"+cat);
		
		if(tp ==null || cat ==null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		productoDao.save(prod);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@Transactional
	public void deleteProducto(Integer id) {
		productoDao.deleteById(id);
	}
	
	@Transactional
	public void updateProducto(Productos p) {
		productoDao.save(p);
	}   
	
	

}
