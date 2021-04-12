package py.com.spa.app.dao;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import py.com.spa.app.entities.Disponible;
import py.com.spa.app.entities.DisponibleBoxes;
import py.com.spa.app.entities.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.Empleados;
import py.com.spa.app.entities.Servicios;

public interface IDisponibleBoxesDao  extends JpaRepository<DisponibleBoxes, Integer>{
	

	
	List<DisponibleBoxes> findAllByServicioId(Servicios id);
	
	 @Query(value = "select distinct hora from reserva_detalle rd\r\n" + 
		 		"	join disponible d on d.empleado_id = rd.empleado\r\n" + 
		 		"	join servicios s on s.servicio_id = d.servicio_id\r\n" + 
		 		"	where rd.empleado = :id and rd.fecha_reserva = :fecha" ,  nativeQuery = true)
	 List<Time> findHorasOcupadas(@Param("id") Integer id, @Param("fecha") Date fecha);

}
