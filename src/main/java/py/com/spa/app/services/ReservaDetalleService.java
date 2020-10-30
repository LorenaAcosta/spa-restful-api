package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.IReservaDetalleDao;
import py.com.spa.app.entities.ReservaDetalle;

@Service
public class ReservaDetalleService {
	
	@Autowired
	private IReservaDetalleDao reservaDao;
	
	
	@Transactional(readOnly=true)
	public List<ReservaDetalle> findAll(){
		return (List<ReservaDetalle>) reservaDao.findAll();
	}
	
	@Transactional
	public void addReservaDetalle(ReservaDetalle reserva) {
		reservaDao.save(reserva);
	}
	
	@Transactional(readOnly=true)
	public ReservaDetalle findByReservaDetalleId(Integer id) {
		return (ReservaDetalle) reservaDao.findById(id).orElse(null);
	}

	@Transactional
	public void updateReserva(ReservaDetalle reserva) {
		reservaDao.save(reserva);
	}
	
	@Transactional
	public void deleteReserva(Integer id) {
		reservaDao.deleteById(id);
	}


}