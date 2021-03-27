package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.IComprobanteDao;
import py.com.spa.app.entities.Comprobante;

@Service
public class ComprobanteService {
	
	@Autowired
	private IComprobanteDao comprobanteDao;
	
	@Transactional(readOnly=true)
	public List<Comprobante> findAll(){
		return (List<Comprobante>) comprobanteDao.findAll();
	}
	
	
	@Transactional
	public Comprobante addComprobante(Comprobante c) {
		return comprobanteDao.save(c);
	}
	
	@Transactional(readOnly=true)
	public Comprobante findByComprobanteId(Integer id) {
		return (Comprobante) comprobanteDao.findById(id).orElse(null);
	}

	@Transactional
	public void updateComprobante(Comprobante tc) {
		comprobanteDao.save(tc);
	}
	
	@Transactional
	public void deleteComprobante(Integer id) {
		comprobanteDao.deleteById(id);
	}


}