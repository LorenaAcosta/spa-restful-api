package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.IPuntoExpedicionDao;
import py.com.spa.app.entities.PuntoExpedicion;
import py.com.spa.app.entities.Usuario;
import py.com.spa.app.reportes.Cajero;

@Service
public class PuntoExpedicionService {
	
	@Autowired
	private IPuntoExpedicionDao puntoExpedicionDao;
	
	@Transactional(readOnly=true)
	public List<PuntoExpedicion> findAll(){
		return (List<PuntoExpedicion>) puntoExpedicionDao.findAll();
	}
	
	
	@Transactional
	public PuntoExpedicion addPuntoExpedicion(PuntoExpedicion pe) {
		return puntoExpedicionDao.save(pe);
	}
	
	@Transactional(readOnly=true)
	public PuntoExpedicion findByPuntoExpedicionId(Integer id) {
		return (PuntoExpedicion) puntoExpedicionDao.findById(id).orElse(null);
	}

	@Transactional
	public void updatePuntoExpedicion(PuntoExpedicion pe) {
		puntoExpedicionDao.save(pe);
	}
	
	@Transactional
	public void deletePuntoExpedicion(Integer id) {
		puntoExpedicionDao.deleteById(id);
	}

	@Transactional(readOnly=true)
	public Integer getNextId() {
		return (Integer) puntoExpedicionDao.getNextIdVal();
	}
	
	@Transactional(readOnly=true)
	public List<Cajero> getCajeros() {
		return (List<Cajero>) puntoExpedicionDao.getCajeros();
	}

}
