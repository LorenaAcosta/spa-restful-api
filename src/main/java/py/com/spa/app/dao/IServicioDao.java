package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Servicios;

public interface IServicioDao extends JpaRepository<Servicios, Integer>{
	

	List<Servicios> findAllByCategoriaId(Categorias categoria);
	
	List<Servicios> getServiciosByEstado(String estado);
	
	@Query(value="select * from servicios where categoria_id = :cat and estado = :estado", nativeQuery = true)
	List<Servicios> findAllByCategoriaIdAndEstado(@Param("cat") Integer categoria, @Param("estado") String estado);

	
	
}
