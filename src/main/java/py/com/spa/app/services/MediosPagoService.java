package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import py.com.spa.app.dao.IMedioPagoDao;
import py.com.spa.app.entities.MediosPago;

@Service
public class MediosPagoService {
	
	@Autowired
	private IMedioPagoDao mediosPagoDao;
	
	@Transactional(readOnly=true)
	public List<MediosPago> findAll(){
		return (List<MediosPago>) mediosPagoDao.findAll(); 
	}
	
	@Transactional
	public MediosPago addMediosPago(MediosPago mediospago) {
		return mediosPagoDao.save(mediospago);
	}
	
	@Transactional(readOnly=true)
	public MediosPago findByMedioPagoId(Integer id) {
		return (MediosPago) mediosPagoDao.findById(id).orElse(null);
	}
	
	@Transactional
	public MediosPago updateMedioPago(MediosPago mediospago) {
		return mediosPagoDao.save(mediospago);
	}
	
	
	@Transactional
	public void deleteTiposProducto(Integer id) {
		mediosPagoDao.deleteById(id);
	}

}
