package py.com.spa.app.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import py.com.spa.app.dao.IBoxesDao;
import py.com.spa.app.dao.ICarritoCabeceraDao;
import py.com.spa.app.dao.ICarritoDao;
import py.com.spa.app.dao.ICategoriaDao;
import py.com.spa.app.dao.IServicioDao;
import py.com.spa.app.entities.Boxes;
import py.com.spa.app.entities.Carrito;
import py.com.spa.app.entities.CarritoCabecera;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Disponible;
import py.com.spa.app.entities.DisponibleBoxes;
import py.com.spa.app.entities.Empleados;
import py.com.spa.app.entities.ReservaDetalle;
import py.com.spa.app.entities.Servicios;
import py.com.spa.app.enumeraciones.TipoCategoria;

@Service
public class CarritoCabeceraService {
	
	@Autowired
	private ICarritoCabeceraDao boxesDao;

	
	
	@Transactional(readOnly=true)
	public List<CarritoCabecera> findAll(){
		return (List<CarritoCabecera>) boxesDao.findAll();
	}
	
	@Transactional
	public CarritoCabecera addCategoria(CarritoCabecera categoria) {
		return boxesDao.save(categoria);
	}
	
	
	@Transactional(readOnly=true)
	public CarritoCabecera findByCategoriaId(Integer id) {
		return (CarritoCabecera) boxesDao.findById(id).orElse(null);
	}
	@Transactional
	public void deleteCategoria(Integer id) {
		boxesDao.deleteById(id);
	}

	public Integer getmaxid() {
		return (Integer) boxesDao.getmaxid();
	}

	public CarritoCabecera findByCarritoCabeceraId(Integer id) {
		return (CarritoCabecera) boxesDao.findById(id).orElse(null);
	}

	public List<CarritoCabecera> misOrdenes(Integer usuarioId) {
		return (List<CarritoCabecera>) boxesDao.findByUsuario(usuarioId);
	
	}
	


}







