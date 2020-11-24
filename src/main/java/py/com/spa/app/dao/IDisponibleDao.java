package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.Disponible;
import py.com.spa.app.entities.Servicios;

public interface IDisponibleDao  extends JpaRepository<Disponible, Integer>{
	

	List<Disponible> findAllByServicioId(Servicios servicio);

}
