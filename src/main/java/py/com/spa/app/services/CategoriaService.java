package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.ICategoriaDao;
import py.com.spa.app.entities.Categorias;
import py.com.spa.params.PaginadoParam;
import py.com.spa.result.PaginadoResult;

@Service
public class CategoriaService {
	
	@Autowired
	private ICategoriaDao categoriaDao;
	
	
	@Transactional(readOnly=true)
	public List<Categorias> findAll(){
		return (List<Categorias>) categoriaDao.findAll();
	}
	@Transactional(readOnly=true)
	public List<Categorias> findNothing(){
		return (List<Categorias>) categoriaDao.findAll();
	}
	
	@Transactional
	public void addCategoria(Categorias categoria) {
		categoriaDao.save(categoria);
	}
	
	@Transactional(readOnly=true)
	public Categorias findByCategoriaId(Integer id) {
		return (Categorias) categoriaDao.findById(id).orElse(null);
	}

	@Transactional
	public void updateCategoria(Categorias categoria) {
		categoriaDao.save(categoria);
	}
	
	@Transactional
	public void deleteCategoria(Integer id) {
		categoriaDao.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	public PaginadoResult<Categorias> listar (PaginadoParam<Categorias> param) {
		
		
		
		ExampleMatcher matcher = ExampleMatcher.matching()
	            .withStringMatcher(StringMatcher.CONTAINING);
	        Example<Categorias> example = Example.of(param.getFiltros(),matcher);
	            Page<Categorias> lista = categoriaDao.findAll(example,
	                PageRequest.of(
	                        param.getPagina(), 
	                        param.getCantidad(), 
	                        Sort.by(
	                                param.getOrderDir().equals("ASC")? Sort.Direction.ASC:Sort.Direction.DESC,
	                                param.getOrderBy())
	                        ));
	                PaginadoResult<Categorias> result = new PaginadoResult<>(lista);
	                return result;
		
	}
	
}







