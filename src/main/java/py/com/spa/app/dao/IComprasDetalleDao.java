package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Compras;
import py.com.spa.app.entities.ComprasDetalle;

public interface IComprasDetalleDao extends JpaRepository<ComprasDetalle, Integer> {
	List<ComprasDetalle> findByCompras(Compras compra);
}
