package py.com.spa.app.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Disponible;
import py.com.spa.app.entities.Empleados;
import py.com.spa.app.entities.ReservaDetalle;
import py.com.spa.app.entities.Servicios;


public interface IReservaDetalleDao  extends JpaRepository<ReservaDetalle, Integer>{
	
	List<ReservaDetalle> findByEmpleado(Integer empleado);
	
}
