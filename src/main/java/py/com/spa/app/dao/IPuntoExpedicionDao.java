package py.com.spa.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import py.com.spa.app.entities.PuntoExpedicion;


public interface IPuntoExpedicionDao extends  JpaRepository<PuntoExpedicion, Integer> {
	
	@Query(value="select (count(punto_expedicion_id) + 1) from punto_expedicion", nativeQuery = 
	        true)
	Integer getNextIdVal();
}
