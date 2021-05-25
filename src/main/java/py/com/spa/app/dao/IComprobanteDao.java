package py.com.spa.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import py.com.spa.app.entities.Comprobante;


public interface IComprobanteDao extends  JpaRepository<Comprobante, Integer> {
	
	@Query(value="select CASE	\r\n" + 
			"		WHEN (numero_actual) < numero_final THEN (numero_actual + 1)\r\n" + 
			"		ELSE 9999\r\n" + 
			"		END\r\n" + 
			"from comprobante where estado = 'ACTIVO'", nativeQuery = true)
	Integer getNumeroActual();
	
	@Query(value="select CASE	\r\n" + 
			"		WHEN (numero_actual) < numero_final THEN (numero_actual + 1)\r\n" + 
			"		ELSE 9999\r\n" + 
			"		END\r\n" + 
			"from comprobante where estado = 'ACTIVO' and punto_expedicion_id =:peId", nativeQuery = true)
	Integer getNumeroActualPorPunto(@Param("peId") Integer puntoExpedicionId);
	
	@Query(value="select * from comprobante where estado = 'ACTIVO'", nativeQuery = true)
	Comprobante getComprobanteActivo();
	
	@Query(value="select distinct\r\n" + 
			"		(case \r\n" + 
			"			when (select coalesce(count(*), 0) from comprobante where estado = 'ACTIVO' and punto_expedicion_id =:peId) = 1 then true\r\n" + 
			"			else false\r\n" + 
			"		end) as f\r\n" + 
			"from comprobante where punto_expedicion_id =:peId", nativeQuery = true)
	Boolean getComprobanteActivoPorPunto(@Param("peId") Integer puntoExpedicionId);
	
	@Query(value="select * from comprobante where punto_expedicion_id =:peId and estado = 'ACTIVO'", nativeQuery = true)
	Comprobante getComprobanteActivoPorPunto2(@Param("peId") Integer puntoExpedicionId);

}
