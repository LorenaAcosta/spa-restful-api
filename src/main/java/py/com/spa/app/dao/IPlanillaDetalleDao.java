package py.com.spa.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Planilla;
import py.com.spa.app.entities.PlanillaDetalle;
import py.com.spa.app.enumeraciones.TipoCategoria;
import py.com.spa.app.reportes.DetallesConceptos;
import py.com.spa.app.reportes.RankingP;

public interface IPlanillaDetalleDao extends JpaRepository<PlanillaDetalle, Integer> {
	


	@Query(value="select * from planilla_detalle where planilla_id=:id", nativeQuery = true)
	List<PlanillaDetalle> findByPlanillaId(@Param("id") Integer planillaId);

	@Query(value="select sum(monto_debe) from planilla_detalle where planilla_id=:id and concepto_id=990", nativeQuery = true)
	Integer getComisiones(Integer id);

	@Query(value="select monto_debe from planilla_detalle where planilla_id=:id and concepto_id=999", nativeQuery = true)
	Integer getSalario(Integer id);

	@Query(value="select  p.monto_debe as debe, p.monto_haber as haber, c.descripcion as descripcion from planilla_detalle p\r\n"
			+ "inner join conceptos c on c.conceptos_id = p.concepto_id\r\n"
			+ "where planilla_id=:id and concepto_id!=999 and concepto_id!=990", nativeQuery = true)
	List <DetallesConceptos> getDetalles(@Param("id") Integer id);

	
}