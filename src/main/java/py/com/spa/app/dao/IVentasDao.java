package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import py.com.spa.app.entities.RankingP;
import py.com.spa.app.entities.Ventas;
import py.com.spa.app.entities.VentasDetalle;

public interface IVentasDao extends JpaRepository<Ventas, Integer>{
	@Query(value="select COALESCE (max(ventas_id), 0) from Ventas", nativeQuery = 
	        true)
	Integer getNextIdVal();
	
	@Query(value="select * from ventas where upper(estado) = 'ACTIVO' order by numero_comprobante desc", nativeQuery = true)
	List <Ventas> ventasActivas();
}
