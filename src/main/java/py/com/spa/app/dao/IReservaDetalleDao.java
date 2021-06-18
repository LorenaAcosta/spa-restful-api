package py.com.spa.app.dao;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.entities.ReservaDetalle;
import py.com.spa.app.entities.Rol;
import py.com.spa.app.entities.Ventas;
import py.com.spa.app.reportes.ReservaReporte;
import py.com.spa.app.reportes.ServiciosReservadosPorClienteFecha;


public interface IReservaDetalleDao  extends JpaRepository<ReservaDetalle, Integer>{
	
	List<ReservaDetalle> findByEmpleado(Integer empleado);
	
	List<ReservaDetalle> findByVentasId(Ventas ventasId);
	
	List<ReservaDetalle> findByFechaReservaAndHora(Date fecha, Time hora);
	
	List<ReservaDetalle> findAllByEmpleadoAndFechaReservaOrderByHoraAsc(Integer empleado, Date date);
	
	List<ReservaDetalle> findByFechaReserva(Date fechaReserva);
	
	@Query(value = "select * from reserva_detalle where usuario_id =:id",  nativeQuery = true)
	  List<ReservaDetalle> findByUsuarioId(@Param("id") Integer usuarioId);
	
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
	 
	 
	 @Query(value = "select * from reserva_detalle r  \r\n"
		 		+ "inner join disponible d on r.disponible_id= d.disponible_id \r\n"
		 		+ "--inner join ventas_detalle v on v.servicio_id = d.servicio_id \r\n"
		 		+ "where UPPER(r.estado) = UPPER('Pagado') and d.empleado_id= :id \r\n"
		 		+ "and EXTRACT(MONTH FROM r.fecha_reserva)= :mes order by fecha_reserva ASC",  nativeQuery = true)
	    List<ReservaDetalle> reservasConfirmadas(@Param("id") Integer empleadoId, @Param("mes") Integer mes );
	 
	 
		/*dao para reporte*/
		@Query(value="Select ROW_NUMBER() OVER (ORDER BY r.reserva_id) as item, s.nombre as servicio,\r\n" + 
				"(u.nombre || ' ' || u.apellido) as cliente, to_char(r.fecha_reserva,'DD/mm/YYYY') as fecha,\r\n" + 
				"r.hora, b.nombre as box, (e.nombre || ' ' || e.apellido) as terapista, r.estado\r\n" + 
				"from reserva_detalle r\r\n" + 
				"join disponible d on d.disponible_id = r.disponible_id\r\n" + 
				"join servicios s on s.servicio_id = d.servicio_id\r\n" + 
				"join empleados e on e.empleado_id = r.empleado\r\n" + 
				"join usuario u on u.usuario_id = r.usuario_id\r\n" + 
				"join disponible_boxes db on db.disponible_boxes_id = r.disponible_boxes_id\r\n" + 
				"join boxes b on b.boxes_id = db.boxes_id\r\n" + 
				"where r.fecha_reserva = :fecha", nativeQuery = true)
		List<ReservaReporte> getPorFechaReporte(@Param("fecha") Date fecha);
		
		
		 @Query(value = "select rd.reserva_id as reserva, s.nombre as servicio\r\n" + 
		 		"from servicios s\r\n" + 
		 		"join disponible d on d.servicio_id = s.servicio_id\r\n" + 
		 		"join reserva_detalle rd on rd.disponible_id = d.disponible_id\r\n" + 
		 		"where  upper(rd.estado) = upper('Confirmado') and rd.usuario_id = :id\r\n" + 
		 		"and (to_char(rd.fecha_reserva, 'YYY-MM-DD') = to_char(CURRENT_DATE,'YYY-MM-DD')) ",  nativeQuery = true)
		 List<ServiciosReservadosPorClienteFecha> serviciosReservadosPorClienteFecha(@Param("id") Integer usuarioId );
		 
		 
		@Modifying
		@Transactional
		@Query(value = "update reserva_detalle set estado = 'Pagado' where reserva_id = :id", nativeQuery = true)
		void cambiarEstadoPagado(@Param("id") Integer reservaId);
		
		@Modifying
		@Transactional
		@Query(value = "update reserva_detalle set estado = 'Confirmado', ventas_id = null where reserva_id = :id", nativeQuery = true)
		void cambiarEstadoConfirmado(@Param("id") Integer reservaId);
	
	
}
