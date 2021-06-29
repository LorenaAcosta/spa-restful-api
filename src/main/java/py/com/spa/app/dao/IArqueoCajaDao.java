package py.com.spa.app.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import py.com.spa.app.entities.ArqueoCaja;

public interface IArqueoCajaDao extends  JpaRepository<ArqueoCaja, Integer> {
	@Query(value="SELECT * FROM public.arqueo_caja where upper(estado) = 'ABIERTO' and punto_expedicion_id =:peId", nativeQuery = true)
	ArqueoCaja getCajaActiva(@Param("peId") Integer puntoExpedicionId);
}
