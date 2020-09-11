package py.com.spa.app.dao;


import org.springframework.data.jpa.repository.JpaRepository;


import py.com.spa.app.entities.Empleados;

public interface IEmpleadoDao extends JpaRepository<Empleados, Integer> {

	
}