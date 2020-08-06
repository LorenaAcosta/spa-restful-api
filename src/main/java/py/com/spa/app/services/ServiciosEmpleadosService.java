package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.IProductoDao;
import py.com.spa.app.dao.IServicioEmpleadoDao;
import py.com.spa.app.entities.ServiciosEmpleados;

@Service
public class ServiciosEmpleadosService {
	@Autowired
	private IServicioEmpleadoDao serEmpDao;
	

	@Transactional(readOnly=true)
	public List<ServiciosEmpleados> findAll(){
		return (List<ServiciosEmpleados>) serEmpDao.findAll(); 
	}
	@Transactional
	public void add(ServiciosEmpleados producto) {
		serEmpDao.save(producto);
	}
	
	@Transactional(readOnly=true)
	public ServiciosEmpleados findById(Integer id){
		return (ServiciosEmpleados) serEmpDao.findById(id).orElse(null);
	}
	
	@Transactional
	public void update(ServiciosEmpleados producto) {
		serEmpDao.save(producto);
	} 
	
	@Transactional
	public void delete(Integer id) {
		serEmpDao.deleteById(id);
	}
	
	  

}
