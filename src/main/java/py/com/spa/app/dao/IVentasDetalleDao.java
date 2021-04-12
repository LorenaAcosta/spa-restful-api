package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import py.com.spa.app.entities.Ventas;
import py.com.spa.app.entities.VentasDetalle;
import py.com.spa.app.reportes.DetalleVentaReportInterface;
import py.com.spa.app.reportes.RankingP;
import py.com.spa.app.reportes.VentaEncabezadoReportInterface;
import py.com.spa.app.reportes.VentaFooterReportInterface;

public interface IVentasDetalleDao extends JpaRepository<VentasDetalle, Integer> {
	List<VentasDetalle> findByVentas(Ventas venta);
	
	@Query(value="	select sum(ventas_detalle.cantidad) as max, productos.descripcion as producto, sum(productos.precio_venta) as total"
			+ " from ventas_detalle \r\n" + 
			"	join productos on productos.producto_id = ventas_detalle.producto_id\r\n" + 
			"	group by (productos.descripcion, productos.precio_venta)\r\n" + 
			"	order by (max) desc", nativeQuery = true)
	List <RankingP> rankingProductos();
	

}
