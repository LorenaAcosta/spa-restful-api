package py.com.spa.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import py.com.spa.app.entities.Comprobante;


public interface IComprobanteDao extends  JpaRepository<Comprobante, Integer> {
	
	@Query(value="select CASE	\r\n" + 
			"		WHEN (numero_actual) < numero_final THEN (numero_actual + 1)\r\n" + 
			"		ELSE 9999\r\n" + 
			"		END\r\n" + 
			"from comprobante where estado = 'ACTIVO'", nativeQuery = true)
	Integer getNumeroActual();
	
	@Query(value="select * from comprobante where estado = 'ACTIVO'", nativeQuery = true)
	Comprobante getComprobanteActivo();

}
