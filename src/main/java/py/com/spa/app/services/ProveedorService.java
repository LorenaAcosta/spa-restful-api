package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.ICategoriaDao;
import py.com.spa.app.dao.IProveedorDao;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Proveedor;

@Service
public class ProveedorService {

	@Autowired
	private IProveedorDao proveedorDao;
	
	
	@Transactional(readOnly=true)
	public List<Proveedor> findAll(){
		return (List<Proveedor>) proveedorDao.findAll();
	}
	
	@Transactional
	public void add(Proveedor proveedor) {
		proveedorDao.save(proveedor);
	}
	
	@Transactional(readOnly=true)
	public Proveedor findById(Integer id) {
		return (Proveedor) proveedorDao.findById(id).orElse(null);
	}

	@Transactional
	public void update(Proveedor proveedor) {
		proveedorDao.save(proveedor);
	}
	
	@Transactional
	public void delete(Integer id) {
		proveedorDao.deleteById(id);
	}
	

	
}
