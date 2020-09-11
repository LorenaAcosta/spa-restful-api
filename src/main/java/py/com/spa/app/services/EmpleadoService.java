package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.IEmpleadoDao;
import py.com.spa.app.entities.Empleados;

@Service
public class EmpleadoService {
	@Autowired
	private IEmpleadoDao empleadoDao;
	
	@Transactional(readOnly=true)
	public List<Empleados> findAll(){
		return (List<Empleados>) empleadoDao.findAll(); 
	}
	
	@Transactional
	public Empleados saveEmpleado(Empleados empleado) {
		return empleadoDao.save(empleado);
	}

	@Transactional
	public void updateEmpleado(Empleados empleado) {
		empleadoDao.save(empleado);
	}
	
	@Transactional
	public void deleteEmpleado(Integer id) {
		empleadoDao.deleteById(id);
	}
	

	@Transactional(readOnly=true)	
	public Empleados findEmpleadoById(Integer id) {
		return (Empleados) empleadoDao.findById(id).orElse(null);
	}


}
