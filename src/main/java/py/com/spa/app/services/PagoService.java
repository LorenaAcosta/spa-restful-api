package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.IPagoDao;
import py.com.spa.app.entities.Pagos;

@Service
public class PagoService {
	
	@Autowired
	private IPagoDao pagoDao;
	
	@Transactional(readOnly=true)
	public List<Pagos> findAll(){
		return (List<Pagos>) pagoDao.findAll();
	}
	
	@Transactional
	public void agregarPago (Pagos pago){	
		pagoDao.save(pago);
	}
	
	@Transactional
	public void eliminarPago (Integer id){	
		pagoDao.deleteById(id);
	}

	
}
