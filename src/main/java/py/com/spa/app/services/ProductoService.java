package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.ICategoriaDao;
import py.com.spa.app.dao.IProductoDao;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Productos;
import py.com.spa.app.entities.Servicios;
import py.com.spa.params.PaginadoParam;
import py.com.spa.result.PaginadoResult;

@Service
public class ProductoService {
	
	
	@Autowired
	private IProductoDao productoDao;
	@Autowired
	private ICategoriaDao categoriaDao;
	

	@Transactional(readOnly=true)
	public List<Productos> findAll(){
		return (List<Productos>) productoDao.findAll(); 
	}
	@Transactional
	public void addProducto(Productos producto) {
		productoDao.save(producto);
	}
	
	
	@Transactional(readOnly=true)
	public Categorias findByCategoriaId(Integer id) {
		return (Categorias) categoriaDao.findById(id).orElse(null);
	}


	@Transactional(readOnly=true)
	public Productos findProductoById(Integer id){
		return (Productos) productoDao.findById(id).orElse(null);
	}
	
	@Transactional
	public void updateProducto(Productos producto) {
		productoDao.save(producto);
	} 
	
	@Transactional
	public void deleteProducto(Integer id) {
		productoDao.deleteById(id);
	}
	
	
	public List<Productos> findAllByCategoriaId(Categorias categoria) {
		return (List<Productos>) productoDao.findAllByCategoriaId(categoria);
	}
	
	  
	
	@Transactional(readOnly=true)
	public PaginadoResult<Productos> listar (PaginadoParam<Productos> param) {
		
		
		ExampleMatcher matcher = ExampleMatcher.matching()
	            .withStringMatcher(StringMatcher.CONTAINING);
	        Example<Productos> example = Example.of(param.getFiltros(),matcher);
	            Page<Productos> lista = productoDao.findAll(example,
	                PageRequest.of(
	                        param.getPagina(), 
	                        param.getCantidad(), 
	                        Sort.by(
	                                param.getOrderDir().equals("ASC")? Sort.Direction.ASC:Sort.Direction.DESC,
	                                param.getOrderBy())
	                        ));
	                PaginadoResult<Productos> result = new PaginadoResult<>(lista);
	                return result;
		
	}
	

}
