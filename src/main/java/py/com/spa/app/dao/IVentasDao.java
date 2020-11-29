package py.com.spa.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import py.com.spa.app.entities.Ventas;

public interface IVentasDao extends JpaRepository<Ventas, Integer>{
	@Query(value="select max(ventas_id) from Ventas", nativeQuery = 
	        true)
	Integer getNextIdVal();
}
