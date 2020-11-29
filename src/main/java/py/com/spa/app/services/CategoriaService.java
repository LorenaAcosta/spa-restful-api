package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import py.com.spa.app.dao.ICategoriaDao;
import py.com.spa.app.entities.Categorias;

@Service
public class CategoriaService {
	
	@Autowired
	private ICategoriaDao categoriaDao;
	
	
	@Transactional(readOnly=true)
	public List<Categorias> findAll(){
		return (List<Categorias>) categoriaDao.findAll();
	}
	
	@Transactional
	public Categorias addCategoria(Categorias categoria) {
		return categoriaDao.save(categoria);
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
	
	@Transactional
	public List<Categorias> obtenerCategorias(String id) {
		return (List<Categorias>) categoriaDao.findByDataType(id);
	}

}







