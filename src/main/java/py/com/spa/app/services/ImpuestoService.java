package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.IImpuestoDao;
import py.com.spa.app.entities.Impuesto;

@Service
public class ImpuestoService {
	
	@Autowired
	private IImpuestoDao impuestoDao;
	
	@Transactional(readOnly=true)
	public List<Impuesto> findAll(){
		return (List<Impuesto>) impuestoDao.findAll();
	}
	
	
	@Transactional
	public Impuesto addImpuesto(Impuesto impuesto) {
		return impuestoDao.save(impuesto);
	}
	
	@Transactional(readOnly=true)
	public Impuesto findByImpuestoId(Integer id) {
		return (Impuesto) impuestoDao.findById(id).orElse(null);
	}

	@Transactional
	public void updateImpuesto(Impuesto impuesto) {
		impuestoDao.save(impuesto);
	}
	
	@Transactional
	public void deleteImpuesto(Integer id) {
		impuestoDao.deleteById(id);
	}


}
