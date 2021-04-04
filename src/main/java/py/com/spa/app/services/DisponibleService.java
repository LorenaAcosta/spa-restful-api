package py.com.spa.app.services;

import java.sql.Time;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.ICategoriaDao;
import py.com.spa.app.dao.IDisponibleDao;
import py.com.spa.app.dao.IHorarioDao;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Disponible;

import py.com.spa.app.entities.Empleados;

import py.com.spa.app.entities.Horario;
import py.com.spa.app.entities.Servicios;

@Service
public class DisponibleService {
	
	
	@Autowired
	private IDisponibleDao disponibleDao;
	
	@Autowired
	private HorarioService horarioService;
	
	@Autowired
	private IHorarioDao horarioDao;
	
	
	@Transactional(readOnly=true)
	public List<Disponible> findAll(){
		return (List<Disponible>) disponibleDao.findAll();
	}
	
	@Transactional
	public Disponible addDisponible(Disponible disponible) {
		return disponibleDao.save(disponible);
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
	
	
	public List<Disponible> findEmpleadosDisponibles(Integer servicio) {
		return (List<Disponible>) disponibleDao.findEmpleadosDisponibles(servicio);
	}
	
	public List<Time> getHorariosDisponibles(Integer categoriaId, Integer servicioId, Integer empleadoId, Date fecha) {
		Calendar c = Calendar.getInstance();
		c.setTime(fecha);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		//Obtenemos la hora de entrada y hora de salida del empleado 
		Horario hor = horarioService.findByIdEmpleado(empleadoId, dayOfWeek-1 );
		Long inicio = (hor.getHoraInicio().getTime() / 1000L) - 14400;
		Long fin = (hor.getHoraFin().getTime() / 1000L) - 14400;
		Long suma = (inicio + 3600);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fechaString = sdf.format(fecha);
		
		List<Time> horasOcupadas = disponibleDao.findHorasOcupadas(empleadoId, fecha);
		//System.out.println("Tamaño : " + horasOcupadas.size());
		
		List<Time> horasDisponibles = new ArrayList<Time>();
		
		for (long i = inicio; i < fin; i = i + 3600 ) {
		       for (Time hora : horasOcupadas) {
		            if (((hora.getTime() / 1000L) - 14400) == i) {
		            	i = i + 3600;
		            }
		        }
		       
		       Time hora = new Time((i * 1000L) + (14400 * 1000L) );
           		if (((hora.getTime() / 1000L) - 14400) != fin ) {
           			horasDisponibles.add(hora);
           		}
		}

		if (horasDisponibles.size() == 0) {
			Time vacio = null; //new Time((14400 - 14400) );
			System.out.println("Hora en formato Time " + vacio);
			horasDisponibles.add(vacio);
		}
		return horasDisponibles;
	}
	

	
	public List<Disponible> findByEmpleadoId(Empleados emp) {
		return (List<Disponible>) disponibleDao.findAllByEmpleadoId(emp);
	}
}







