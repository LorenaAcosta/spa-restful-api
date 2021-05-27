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
	
	@Transactional(readOnly=true)
	public Integer numeroActual() {
		return (Integer) comprobanteDao.getNumeroActual();
	}
	
	@Transactional(readOnly=true)
	public Integer numeroActualPorPunto(Integer id) {
		return (Integer) comprobanteDao.getNumeroActualPorPunto(id);
	}
	
	@Transactional(readOnly=true)
	public Comprobante getComprobanteActivo() {
		return (Comprobante) comprobanteDao.getComprobanteActivo();
	}
	
	@Transactional(readOnly=true)
	public Boolean getComprobanteActivoPorPunto(Integer id) {
		Boolean valor = comprobanteDao.getComprobanteActivoPorPunto(id);
		if (valor == null) {
			valor = false;
		}
		return (Boolean) valor;
	}
	
	@Transactional(readOnly=true)
	public Comprobante getComprobanteActivoPorPunto2(Integer id) {
		return (Comprobante) comprobanteDao.getComprobanteActivoPorPunto2(id);
	}


}
