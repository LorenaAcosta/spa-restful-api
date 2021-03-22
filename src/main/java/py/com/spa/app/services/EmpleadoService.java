package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.IEmpleadoDao;
import py.com.spa.app.dao.daoImpl;
import py.com.spa.app.entities.Empleados;
import py.com.spa.app.entities.Turnos;

@Service
public class EmpleadoService {
	@Autowired
	private IEmpleadoDao empleadoDao;
	


	@Autowired
    private daoImpl daoimpl;
	
	@Transactional(readOnly=true)
	public List<Empleados> findAll(){
		return (List<Empleados>) empleadoDao.findAll(); 
	}
	
	@Transactional
	public Empleados saveEmpleado(Empleados empleado) {
		return empleadoDao.save(empleado);
	}

	@Transactional
	public Empleados updateEmpleado(Empleados empleado) {
		return empleadoDao.save(empleado);
	}
	
	@Transactional
	public void deleteEmpleado(Integer id) {
		empleadoDao.deleteById(id);
	}
	

	@Transactional(readOnly=true)	
	public Empleados findEmpleadoById(Integer id) {
		return (Empleados) empleadoDao.findById(id).orElse(null);
	}

	

	@Transactional(readOnly=true)	
	public Empleados findEmpleadoCedula(Integer cedula) {
		return (Empleados) empleadoDao.findByCedula(cedula);
	}

	
	
    public Turnos obtenerTurnos(Integer empleadoId) {
   
        Turnos response = new Turnos();

        try {
            response = daoImpl.obtenerTurnos(empleadoId);
        } catch (Exception e) {
        	
        }
        return response;
    }


}
