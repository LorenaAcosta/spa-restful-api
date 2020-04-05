package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import py.com.spa.app.entities.Empleados;
import py.com.spa.app.entities.Reservas;

public interface IEmpleadoDao extends JpaRepository<Empleados, Integer> {

	
}