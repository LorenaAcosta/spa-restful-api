package py.com.spa.app.services;

import java.sql.Time;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.ICategoriaDao;
import py.com.spa.app.dao.IDisponibleBoxesDao;
import py.com.spa.app.dao.IDisponibleDao;
import py.com.spa.app.dao.IHorarioDao;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Disponible;
import py.com.spa.app.entities.DisponibleBoxes;
import py.com.spa.app.entities.Empleados;

import py.com.spa.app.entities.Horario;
import py.com.spa.app.entities.Servicios;

@Service
public class DisponibleBoxesService {
	
	
	@Autowired
	private IDisponibleBoxesDao disponibleDao;
	;
	
	
	@Transactional(readOnly=true)
	public List<DisponibleBoxes> findAll(){
		return (List<DisponibleBoxes>) disponibleDao.findAll();
	}
	
	@Transactional
	public DisponibleBoxes addDisponible(DisponibleBoxes disponible) {
		return disponibleDao.save(disponible);
	}	
	
	@Transactional(readOnly=true)
	public DisponibleBoxes findByDisponibleId(Integer id) {
		return (DisponibleBoxes) disponibleDao.findById(id).orElse(null);
	}

	@Transactional
	public void updateCategoria(DisponibleBoxes disponible) {
		disponibleDao.save(disponible);
	}
	
	@Transactional
	public void deleteCategoria(Integer id) {
		disponibleDao.deleteById(id);
	}
	
	
	public List<DisponibleBoxes> findAllByCategoriaId(Servicios servicio) {
		return (List<DisponibleBoxes>) disponibleDao.findAllByServicioId(servicio);
	}
	

	
	public List<DisponibleBoxes> findByServicioId(Servicios emp) {
		return (List<DisponibleBoxes>) disponibleDao.findAllByServicioId(emp);
	}
}







