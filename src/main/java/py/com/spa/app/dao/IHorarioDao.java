package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.Empleados;
import py.com.spa.app.entities.Horario;

public interface IHorarioDao extends  JpaRepository<Horario, Integer> {
	
	List<Horario> findByEmpleadoId(Empleados empleado);
	

}
