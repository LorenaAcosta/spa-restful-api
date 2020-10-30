package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.IEmpleadoDisDao;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.EmpleadoDisponible;

@Service
public class EmpleadoDisponibleService {

	@Autowired
	private IEmpleadoDisDao empleadoDisponible;
	
	@Transactional(readOnly=true)
	public List<EmpleadoDisponible> findAll(){
		return (List<EmpleadoDisponible>) empleadoDisponible.findAll();
	}
	
	@Transactional
	public void add(EmpleadoDisponible ed) {
		empleadoDisponible.save(ed);
	}
	
	@Transactional(readOnly=true)
	public EmpleadoDisponible findById(Integer id) {
		return (EmpleadoDisponible) empleadoDisponible.findById(id).orElse(null);
	}

	@Transactional
	public void update(EmpleadoDisponible ed) {
		empleadoDisponible.save(ed);
	}

	
	@Transactional
	public void delete(Integer id) {
		empleadoDisponible.deleteById(id);
	}
	
	@Transactional
	public List<EmpleadoDisponible> findByEmpleadoId(Integer empleadoId) {
		return (List<EmpleadoDisponible>) empleadoDisponible.findByEmpleadoId(empleadoId);
	}
	
}
