package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.ITipoComprobanteDao;
import py.com.spa.app.entities.TipoComprobante;

@Service
public class TipoComprobanteService {
	
	@Autowired
	private ITipoComprobanteDao tipoComprobanteDao;
	
	@Transactional(readOnly=true)
	public List<TipoComprobante> findAll(){
		return (List<TipoComprobante>) tipoComprobanteDao.findAll();
	}
	
	
	@Transactional
	public TipoComprobante addTipoComprobante(TipoComprobante tc) {
		return tipoComprobanteDao.save(tc);
	}
	
	@Transactional(readOnly=true)
	public TipoComprobante findByTipoComprobanteId(Integer id) {
		return (TipoComprobante) tipoComprobanteDao.findById(id).orElse(null);
	}

	@Transactional
	public void updateTipoComprobante(TipoComprobante tc) {
		tipoComprobanteDao.save(tc);
	}
	
	@Transactional
	public void deleteTipoComprobante(Integer id) {
		tipoComprobanteDao.deleteById(id);
	}


}
