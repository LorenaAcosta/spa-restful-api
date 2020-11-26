package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Compras;
import py.com.spa.app.entities.ComprasDetalle;
import py.com.spa.app.entities.Ventas;
import py.com.spa.app.entities.VentasDetalle;

public interface IVentasDetalleDao extends JpaRepository<VentasDetalle, Integer> {
	List<VentasDetalle> findByVentas(Ventas venta);
}
