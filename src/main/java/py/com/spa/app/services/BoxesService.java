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
import py.com.spa.app.dao.ICategoriaDao;
import py.com.spa.app.dao.IServicioDao;
import py.com.spa.app.entities.Boxes;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Disponible;
import py.com.spa.app.entities.DisponibleBoxes;
import py.com.spa.app.entities.Empleados;
import py.com.spa.app.entities.ReservaDetalle;
import py.com.spa.app.entities.Servicios;
import py.com.spa.app.enumeraciones.TipoCategoria;

@Service
public class BoxesService {
	
	@Autowired
	private IBoxesDao boxesDao;

	@Autowired
	private DisponibleBoxesService disponibleboxService;
	
	@Autowired
	private BoxesService boxesService;


	@Autowired
	private ReservaDetalleService reservaService;
	
	@Autowired
	private DisponibleBoxesService disponibleBoxService;
	
	
	@Transactional(readOnly=true)
	public List<Boxes> findAll(){
		return (List<Boxes>) boxesDao.findAll();
	}
	
	@Transactional
	public Boxes addCategoria(Boxes categoria) {
		return boxesDao.save(categoria);
	}
	
	
	@Transactional(readOnly=true)
	public Boxes findByCategoriaId(Integer id) {
		return (Boxes) boxesDao.findById(id).orElse(null);
	}
	@Transactional
	public void deleteCategoria(Integer id) {
		boxesDao.deleteById(id);
	}
	
	public List<Boxes> getBoxesDisponibles(Integer servicioId) {
		
		return boxesDao.getBoxesDisponibles(servicioId);
	}
	

	
	public Integer obtenerBoxLibre(Date fecha, Time hora, Integer servicioId) {
		List<Integer> lista = boxesDao.obtenerBoxLibre(fecha, hora, servicioId);
		
		if (lista!=null&& lista.isEmpty()) {
			return 0;
		}else {
			return lista.get(0);
		}
	}	

}







