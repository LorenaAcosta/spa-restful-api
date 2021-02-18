package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.IComprasDao;
import py.com.spa.app.entities.Compras;

@Service
public class CompraService {
	@Autowired
	private IComprasDao comprasDao;
	
	
	@Transactional(readOnly=true)
	public List<Compras> findAll(){
		return (List<Compras>) comprasDao.findAll();
	}
	
	@Transactional
	public Compras addCompras(Compras compra) {
		return comprasDao.save(compra);
	}
	
	@Transactional(readOnly=true)
	public Compras findByComprasId(Integer id) {
		return (Compras) comprasDao.findById(id).orElse(null);
	}

	@Transactional
	public Compras updateCompras(Compras compra) {
		return comprasDao.save(compra);
	}
	
	@Transactional
	public void deleteCompras(Integer id) {
		comprasDao.deleteById(id);
	}
	

	

}
