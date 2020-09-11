package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.ICategoriaDao;
import py.com.spa.app.dao.IVentasDao;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Ventas;

@Service
public class VentaService {
	@Autowired
	private IVentasDao ventasDao;
	
	
	@Transactional(readOnly=true)
	public List<Ventas> findAll(){
		return (List<Ventas>) ventasDao.findAll();
	}
	
	@Transactional
	public void addVentas(Ventas venta) {
		ventasDao.save(venta);
	}
	
	@Transactional(readOnly=true)
	public Ventas findByVentasId(Integer id) {
		return (Ventas) ventasDao.findById(id).orElse(null);
	}

	@Transactional
	public void updateVentas(Ventas venta) {
		ventasDao.save(venta);
	}
	
	@Transactional
	public void deleteVentas(Integer id) {
		ventasDao.deleteById(id);
	}
	
	

}
