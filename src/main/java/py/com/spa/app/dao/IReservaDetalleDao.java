package py.com.spa.app.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.ReservaDetalle;


public interface IReservaDetalleDao  extends JpaRepository<ReservaDetalle, Integer>{
	
	List<ReservaDetalle> findByEmpleado(Integer empleado);
	
	List<ReservaDetalle> findAllByEmpleadoAndFechaReservaOrderByHoraAsc(Integer empleado, Date date);
	
	List<ReservaDetalle> findByFechaReserva(Date fechaReserva);
	

	
}
