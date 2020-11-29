package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Servicios;

public interface IServicioDao extends JpaRepository<Servicios, Integer>{
	

	List<Servicios> findAllByCategoriaId(Categorias categoria);
	
	List<Servicios> getServiciosByEstado(String estado);
	
	List<Servicios> findAllByCategoriaIdAndEstado(Categorias categoria, String estado);
	

	
	
}
