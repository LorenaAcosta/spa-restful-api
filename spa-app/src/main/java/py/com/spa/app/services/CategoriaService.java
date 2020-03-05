package py.com.spa.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import py.com.spa.app.dao.ICategoriaDao;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Clientes;

@Service
public class CategoriaService {
	
	@Autowired
	private ICategoriaDao categoriaDao;
	
	@Transactional(readOnly=true)
	public List<Categorias> findAll(){
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
	
}
