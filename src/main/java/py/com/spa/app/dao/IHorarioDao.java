package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import py.com.spa.app.entities.Empleados;
import py.com.spa.app.entities.Horario;
import py.com.spa.app.entities.Ventas;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.Empleados;
import py.com.spa.app.entities.Horario;

public interface IHorarioDao extends  JpaRepository<Horario, Integer> {
	
	Horario findByEmpleadoId(Empleados id);
	 
	 @Query(value = "select *from horario where empleado_id = :id",  nativeQuery = true)
	 Horario findByEmpleado(@Param("id") Integer id);

}
