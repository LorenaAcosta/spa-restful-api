package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Compras;
import py.com.spa.app.entities.ComprasDetalle;

public interface IComprasDetalleDao extends JpaRepository<ComprasDetalle, Integer> {
	
	List<ComprasDetalle> findByCompras(Compras compra);

	
	@Query(value="select * from compras_detalle where compras_id = :comprasId", nativeQuery = true)
	List<ComprasDetalle> getDetalles(Integer comprasId);
}
