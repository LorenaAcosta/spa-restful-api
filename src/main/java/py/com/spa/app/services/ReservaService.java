package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.IReservaDao;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Reserva;
import py.com.spa.app.entities.Usuario;

@Service
public class ReservaService {

	@Autowired
	private IReservaDao reservaDao;
	
	
	@Transactional(readOnly=true)
	public List<Reserva> findAll(){
		return (List<Reserva>) reservaDao.findAll();
	}
	
	@Transactional
	public void addReserva(Reserva reserva) {
		reservaDao.save(reserva);
	}
	
	@Transactional(readOnly=true)
	public Reserva findByReservaId(Integer id) {
		return (Reserva) reservaDao.findById(id).orElse(null);
	}

	@Transactional
	public void updateReserva(Reserva reserva) {
		reservaDao.save(reserva);
	}
	
	@Transactional
	public void deleteReserva(Integer id) {
		reservaDao.deleteById(id);
	}
	

	
}
