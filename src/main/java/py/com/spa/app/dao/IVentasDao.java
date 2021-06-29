package py.com.spa.app.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import py.com.spa.app.entities.Ventas;
import py.com.spa.app.reportes.DetalleVentaReportInterface;
import py.com.spa.app.reportes.VentaEncabezadoReportInterface;
import py.com.spa.app.reportes.VentaFooterReportInterface;

public interface IVentasDao extends JpaRepository<Ventas, Integer>{
	@Query(value="select COALESCE (max(ventas_id), 0) from Ventas", nativeQuery = 
	        true)
	Integer getNextIdVal();
	
	@Query(value="select * from ventas where upper(estado) = 'ACTIVO' order by numero_comprobante desc", nativeQuery = true)
	List <Ventas> ventasActivas();
	
	//ventas por punto expedicion
	@Query(value="select v.* from \r\n" + 
			"ventas v\r\n" + 
			"join comprobante c on c.comprobante_id = v.comprobante_id \r\n" + 
			"join punto_expedicion pe on pe.punto_expedicion_id = c.punto_expedicion_id\r\n" + 
			"where upper(v.estado) = 'ACTIVO' and pe.punto_expedicion_id =:peId and v.arqueo_id =:arqueoId \r\n" + 
			"order by v.numero_comprobante desc", nativeQuery = true)
	List <Ventas> ventasActivasPorPuntoExpedicion(@Param("peId") Integer puntoExpedicionId, @Param("arqueoId") Integer arqueoId);
	
	List<Ventas> findByFecha(Date fecha);
	
	@Query(value="select coalesce(sum(monto_total), 0) from ventas where arqueo_id =:arqueo and upper(estado) = 'ACTIVO'", nativeQuery = true)
	Long getTotalVentasPorArqueo(@Param("arqueo") Integer arqueoId);
	
	Ventas findByVentasId(Integer id);
	
	@Query(value="select *from ventas where ventas_id = 46", nativeQuery = true)
	List<Ventas> findByVentasIdList(Integer id);
	
	@Query(value="select d.cantidad,\r\n" + 
			"	(case \r\n" + 
			"	 when (d.producto_id is null) then \r\n" + 
			"	 	(select descripcion from servicios s\r\n" + 
			"			join ventas_detalle dt on dt.servicio_id = s.servicio_id\r\n" + 
			"			where dt.ventas_id = :ventaId and s.servicio_id = d.servicio_id)\r\n" + 
			"	 when (d.servicio_id is null) then \r\n" + 
			"	 	(select descripcion from productos p\r\n" + 
			"			join ventas_detalle dt on dt.producto_id = p.producto_id\r\n" + 
			"			where dt.ventas_id = :ventaId and p.producto_id = d.producto_id)\r\n" + 
			"	 end) as descripcion,\r\n" + 
			"	 d.precio,\r\n" + 
			"	 (case \r\n" + 
			"	 when ((select impuesto_id from productos prod where prod.producto_id = d.producto_id) = 3 or (select impuesto_id from servicios ser where ser.servicio_id = d.servicio_id) = 3) then \r\n" + 
			"	 	d.monto\r\n" + 
			"	  else\r\n" + 
			"	  	0\r\n" + 
			"	  end) as exenta,\r\n" + 
			"	  (case \r\n" + 
			"	 when ((select impuesto_id from productos prod where prod.producto_id = d.producto_id) = 2 or (select impuesto_id from servicios ser where ser.servicio_id = d.servicio_id) = 2) then \r\n" + 
			"	 	d.monto\r\n" + 
			"	  else\r\n" + 
			"	  	0\r\n" + 
			"	  end) as cinco,\r\n" + 
			"	 (case \r\n" + 
			"	 when ((select impuesto_id from productos prod where prod.producto_id = d.producto_id) = 1 or (select impuesto_id from servicios ser where ser.servicio_id = d.servicio_id) = 1) then \r\n" + 
			"	 	d.monto\r\n" + 
			"	  else\r\n" + 
			"	  	0\r\n" + 
			"	  end) as diez\r\n" + 
			"from ventas_detalle d\r\n" + 
			"where ventas_id = :ventaId", nativeQuery = true)
	List <DetalleVentaReportInterface> getDetallesPorVentaReport(@Param("ventaId") Integer ventaId);
	
	
	@Query(value="select distinct c.timbrado, c.inicio_vigencia as inicio, c.fin_vigencia as fin, v.comprobante_completo as comprobante, v.fecha, u.ruc,\r\n" + 
			"	(u.nombre || ' ' || u.apellido) as cliente, coalesce(u.direccion, ' ') as direccion\r\n" + 
			"from ventas v \r\n" + 
			"join usuario u on u.usuario_id = v.usuario_id\r\n" + 
			"join comprobante c on c.comprobante_id = v.comprobante_id\r\n" + 
			"join ventas_detalle d on d.ventas_id = v.ventas_id\r\n" + 
			"where d.ventas_id = :ventaId", nativeQuery = true)
	List <VentaEncabezadoReportInterface> getVentasEncabezadoReport(@Param("ventaId") Integer ventaId);
	
	
	@Query(value="select sub_total_exenta as subtotalexenta, sub_total_diez as subtotaldiez, sub_total_cinco as subtotalcinco, \r\n" + 
			"sub_total_total as subtotaltotal, total_descuento as totaldescuento,\r\n" + 
			"monto_total_letras as montototalletras, iva_cinco as ivacinco, iva_diez as ivadiez, \r\n" + 
			"iva_total as ivatotal, monto_total as montototal\r\n" + 
			"from ventas where ventas_id = :ventaId", nativeQuery = true)
	List <VentaFooterReportInterface> getVentasFooterReport(@Param("ventaId") Integer ventaId);
	
	
	@Query(value = "select * from ventas v \r\n"
			+ "inner join usuario c on v.usuario_id = c.usuario_id and upper(v.estado)='ACTIVO'\r\n"
			+ "inner join medios_pago m on m.medio_pago_id = v.medio_pago_id\r\n"
			+ "where UPPER(v.comprobante_completo) like CONCAT('%',UPPER(:id),'%')\r\n"
			+ "or UPPER(c.nombre) like CONCAT('%',UPPER(:id),'%')   \r\n"
			+ "or UPPER(c.apellido) like CONCAT('%',UPPER(:id),'%')  \r\n"
			+ "or UPPER(m.descripcion) like CONCAT('%',UPPER(:id),'%')\r\n",  nativeQuery = true)
	  List<Ventas> busquedaVentas(@Param("id") String termino);
	
}
