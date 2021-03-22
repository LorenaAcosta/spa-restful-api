package py.com.spa.app.services;

import java.sql.Time;
import java.util.ArrayList;
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
import py.com.spa.app.dao.IHorarioDao;
import py.com.spa.app.dao.IServicioDao;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Disponible;
import py.com.spa.app.entities.Empleados;
import py.com.spa.app.entities.Horario;
import py.com.spa.app.entities.Servicios;
import py.com.spa.app.entities.Turnos;

@Service
public class ServicioService {
	
	@Autowired
	private IServicioDao servicioDao;
	
	@Autowired
	private ICategoriaDao categoriaDao;
	
	@Autowired
	private DisponibleService disponibleService;
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@Autowired
	private ServicioService servicioService;

	

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
	public void deleteServicio(Integer id) {
		servicioDao.deleteById(id);
	}
	
	@Transactional
	public Servicios updateServicio(Servicios p) {
		return servicioDao.save(p);
	} 

	@Transactional
	public Servicios guardarServicio(Servicios p) {
		return servicioDao.save(p);
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


	public List<Servicios> getServiciosDisponibles(Integer empleadoId) {
		// Buscamos el empleado por el Id
		Empleados emp = empleadoService.findEmpleadoById(empleadoId);
		List<Disponible> disponibles = disponibleService.findByEmpleadoId(emp);
		List<Servicios> servicios =  servicioService.findAll();
		
		List<Servicios> listaNueva =  new ArrayList<Servicios>();
		for (int i=0 ; i < servicios.size(); i++) {
			
			for ( Disponible dis : disponibles) {
				if ( dis.getServicioId().getServicioId() != servicios.get(i).getServicioId()  ) {
					
					listaNueva.add(servicios.get(i));
					
				}
			
			}
		}
		
		return listaNueva;
	}


}
