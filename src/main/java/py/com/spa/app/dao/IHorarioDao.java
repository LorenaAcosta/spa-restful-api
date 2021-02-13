package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import py.com.spa.app.entities.Empleados;
import py.com.spa.app.entities.Horario;
import py.com.spa.app.entities.Ventas;

public interface IHorarioDao extends  JpaRepository<Horario, Integer> {
	
	List<Horario> findByEmpleadoId(Empleados empleado);
	
}
