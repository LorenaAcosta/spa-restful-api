package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import py.com.spa.app.entities.PuntoExpedicion;
import py.com.spa.app.entities.Usuario;
import py.com.spa.app.reportes.Cajero;


public interface IPuntoExpedicionDao extends  JpaRepository<PuntoExpedicion, Integer> {
	
	@Query(value="select (count(punto_expedicion_id) + 1) from punto_expedicion", nativeQuery = 
	        true)
	Integer getNextIdVal();
	
	@Query(value="select u.usuario_id as usuario, u.nombres as nombres from usuario u \r\n" + 
			"	join usuarios_roles ur on ur.usuario_id = u.usuario_id\r\n" + 
			"	where ur.rol_id = 3", nativeQuery = 
	        true)
	List<Cajero> getCajeros();
}
