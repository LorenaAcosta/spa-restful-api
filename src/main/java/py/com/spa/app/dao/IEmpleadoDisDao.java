package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.EmpleadoDisponible;
import py.com.spa.app.entities.Empleados;
import py.com.spa.app.entities.Servicios;

public interface IEmpleadoDisDao extends JpaRepository<EmpleadoDisponible, Integer> {
	
	public List<EmpleadoDisponible> findByEmpleadoId(Integer empleado);
	

}
