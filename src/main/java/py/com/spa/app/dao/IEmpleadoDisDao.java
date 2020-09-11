package py.com.spa.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.EmpleadoDisponible;

public interface IEmpleadoDisDao extends JpaRepository<EmpleadoDisponible, Integer> {

}
