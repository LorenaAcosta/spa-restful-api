package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.ICategoriaDao;
import py.com.spa.app.dao.IDisponibleDao;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Disponible;
import py.com.spa.app.entities.Servicios;

@Service
public class DisponibleService {
	
	@Autowired
	private IDisponibleDao disponibleDao;
	
	
	@Transactional(readOnly=true)
	public List<Disponible> findAll(){
		return (List<Disponible>) disponibleDao.findAll();
	}
	
	@Transactional
	public void addDisponible(Disponible disponible) {
		disponibleDao.save(disponible);
	}	
	
	@Transactional(readOnly=true)
	public Disponible findByDisponibleId(Integer id) {
		return (Disponible) disponibleDao.findById(id).orElse(null);
	}

	@Transactional
	public void updateCategoria(Disponible disponible) {
		disponibleDao.save(disponible);
	}
	
	@Transactional
	public void deleteCategoria(Integer id) {
		disponibleDao.deleteById(id);
	}
	
	
	public List<Disponible> findAllByCategoriaId(Servicios servicio) {
	
		return (List<Disponible>) disponibleDao.findAllByServicioId(servicio);
	}

	
	

}







