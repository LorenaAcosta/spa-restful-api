package py.com.spa.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import py.com.spa.app.dao.ICategoriaDao;
import py.com.spa.app.dao.IServicioDao;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Servicios;

@Service
public class ServicioService {
	
	@Autowired
	private IServicioDao servicioDao;
	
	@Autowired
	private ICategoriaDao categoriaDao;
	

	@Transactional(readOnly=true)
	public List<Servicios> findAll(){
		return (List<Servicios>) servicioDao.findAll(); 
	}
	
	
	@Transactional(readOnly=true)
	public Servicios findServicioById(Integer id){
		return (Servicios) servicioDao.findById(id).orElse(null);
	}
	
	@Transactional(readOnly=true)
	public Categorias findByCategoriaId(Integer id) {
		return (Categorias) categoriaDao.findById(id).orElse(null);
	}

	
	@Transactional
	public void agregarServicio(Servicios prod){
		servicioDao.save(prod);
	}
	
	@Transactional
	public void deleteServicio(Integer id) {
		servicioDao.deleteById(id);
	}
	
	@Transactional
	public void updateServicio(Servicios p) {
		servicioDao.save(p);
	} 
	
	public List<Servicios> findAllByCategoriaId(Categorias categoria) {
		return (List<Servicios>) servicioDao.findAllByCategoriaId(categoria);
	}

	public List<Servicios> getServiciosByEstado(String estado) {
		return (List<Servicios>) servicioDao.getServiciosByEstado(estado);
	}


	public List<Servicios> findAllByCategoriaIdAndEstado(Categorias categoria, String estado){
		return (List<Servicios>) servicioDao.findAllByCategoriaIdAndEstado(categoria, estado);
		
	}
}
