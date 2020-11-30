package py.com.spa.app.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.IReservaDetalleDao;
import py.com.spa.app.entities.Disponible;
import py.com.spa.app.entities.Empleados;
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
		System.out.println(reserva.getFechaReserva());
		reservaDao.save(reserva);
	}


	@Transactional
	public void updateReserva(ReservaDetalle reserva) {
		reservaDao.save(reserva);
	}
	
	@Transactional
	public void deleteReserva(Integer id) {
		reservaDao.deleteById(id);
	}
	
	@Transactional
	public List<ReservaDetalle> findByEmpleado(Integer empleado){
		return (List<ReservaDetalle>) reservaDao.findByEmpleado(empleado);
	}
	

	@Transactional
	public List<ReservaDetalle> findAllByEmpleadoAndFechaReservaOrderByHoraAsc(Integer empleado, Date date){
		return (List<ReservaDetalle>) reservaDao.findAllByEmpleadoAndFechaReservaOrderByHoraAsc(empleado, date);
	}
	
	@Transactional(readOnly=true)
	public ReservaDetalle findByReservaDetalleId(Integer id) {
		return (ReservaDetalle) reservaDao.findById(id).orElse(null);
	}
	
	@Transactional(readOnly=true)
	public List<ReservaDetalle> findByFechaReserva(Date fechaReserva) {
		return (List<ReservaDetalle>) reservaDao.findByFechaReserva(fechaReserva);
	}

	
	


}
