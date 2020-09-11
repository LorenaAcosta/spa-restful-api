/*package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.IReservaDao;
import py.com.spa.app.entities.ReservaDetalle;

@Service
public class ReservaService {
	
	@Autowired 
	private IReservaDao reservaDao;
	
	@Transactional(readOnly=true)
	public List<ReservaDetalle> findAll(){
		return (List<ReservaDetalle>) reservaDao.findAll();
	}
	
	@Transactional
	public void agregarReserva(ReservaDetalle reserva) {
		reservaDao.save(reserva);
	}
	
	@Transactional(readOnly=true)
	public List<ReservaDetalle> getReservasCliente(Integer id){
		return (List<ReservaDetalle>) reservaDao.findByClienteId(id);
	}
	
	@Transactional(readOnly=true)	
	public ReservaDetalle  findReservaById(Integer id) {
		return (ReservaDetalle) reservaDao.findById(id).orElse(null);
	}
	
	@Transactional
	public void updateReserva(ReservaDetalle reserva) {
		reservaDao.save(reserva);
	}
	
	@Transactional 
	public void eliminarReserva(Integer id) {
		reservaDao.deleteById(id);
	}

	
}*/
