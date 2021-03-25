package py.com.spa.app.dao;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import py.com.spa.app.entities.ReservaDetalle;


public interface IReservaDetalleDao  extends JpaRepository<ReservaDetalle, Integer>{
	
	List<ReservaDetalle> findByEmpleado(Integer empleado);
	
	List<ReservaDetalle> findAllByEmpleadoAndFechaReservaOrderByHoraAsc(Integer empleado, Date date);
	
	List<ReservaDetalle> findByFechaReserva(Date fechaReserva);
	
	 @Query(value = "select *\n"
	 		+ "from reserva_detalle r \n"
	 		+ "join empleados e on e.empleado_id =  r.empleado\n"
	 		+ "join disponible d on d.disponible_id = r.disponible_id\n"
	 		+ "join servicios s on s.servicio_id = d.servicio_id\n"
	 		+ "join usuario u on u.usuario_id = r.usuario_id\n"
	 		+ "where UPPER(e.nombre) like CONCAT('%',UPPER(:id),'%') "
	 		+ "or UPPER(s.nombre) like CONCAT('%',UPPER(:id),'%')"
	 		+ "or UPPER(u.nombre) like CONCAT('%',UPPER(:id),'%')",  nativeQuery = true)
	  List<ReservaDetalle> busquedaReservas(@Param("id") String termino);
	

	
}
