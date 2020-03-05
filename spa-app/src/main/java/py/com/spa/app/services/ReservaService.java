package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.IReservaDao;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Reservas;

@Service
public class ReservaService {
	
	@Autowired 
	private IReservaDao reservaDao;
	
	@Transactional(readOnly=true)
	public List<Reservas> findAll(){
		return (List<Reservas>) reservaDao.findAll();
	}
	
	@Transactional
	public void addReserva(Reservas reserva) {
		reservaDao.save(reserva);
	}
	
	@Transactional(readOnly=true)
	public List<Reservas> getReservasCliente(Integer id){
		return (List<Reservas>) reservaDao.findByClienteId(id);
	}
	
	
}
