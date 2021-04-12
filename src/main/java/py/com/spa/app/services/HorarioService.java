package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import py.com.spa.app.dao.IHorarioDao;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Empleados;
import py.com.spa.app.entities.Horario;
import py.com.spa.app.entities.Servicios;

@Service
public class HorarioService {
	
	@Autowired
	private IHorarioDao horarioDao;
	
	@Transactional(readOnly=true)
	public List<Horario> findAll(){
		return (List<Horario>) horarioDao.findAll();
	}
	
	
	@Transactional
	public Horario addHorario(Horario horario) {
		return horarioDao.save(horario);
	}
	
	@Transactional(readOnly=true)
	public Horario findByHorarioId(Integer id) {
		return (Horario) horarioDao.findById(id).orElse(null);
	}

	@Transactional
	public void updateHorario(Horario horario) {
		horarioDao.save(horario);
	}
	
	@Transactional
	public void deleteHorario(Integer id) {
		horarioDao.deleteById(id);
	}

	public Horario findByEmpleadoId(Empleados id){
		return ( Horario ) horarioDao.findByEmpleadoId(id);
	}
	
	public Horario findByIdEmpleado(Integer id, Integer diaTrabajo){
		
		return ( Horario ) horarioDao.findByEmpleado(id, diaTrabajo);
	}
	
	public List<Horario> findByIdEmpleadoLista(Integer id){
		return (List<Horario>) horarioDao.findByIdEmpleadoLista(id);
	}


	public List<Horario> getListaHorarios(Integer id) {
		return (List<Horario>) horarioDao.getListaHorarios(id);
	}


}
