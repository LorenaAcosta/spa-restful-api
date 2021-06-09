package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Productos;
import py.com.spa.app.enumeraciones.EstadoProducto;
import py.com.spa.app.reportes.CategoriaReporte;
import py.com.spa.app.reportes.ProductoReporte;

public interface IProductoDao extends JpaRepository<Productos, Integer>{
	
	List<Productos> findAllByCategoriaId(Categorias categoria);
	
	@Query(value = "select * from productos c \n"
			+ "where UPPER(c.descripcion) like CONCAT('%',UPPER(:id),'%') ",  nativeQuery = true)
	  List<Productos> busquedaProductos(@Param("id") String termino);
	
	@Query(value = "select * from productos c \n"
			+ "where UPPER(c.descripcion) = UPPER(:nombre) ",  nativeQuery = true)
	  Productos busquedaPorNombre(@Param("nombre") String termino);
	
	
	/*dao para reporte*/
	@Query(value="Select ROW_NUMBER() OVER (ORDER BY p.categoria_id ) as item,\r\n" + 
			"codigo, p.descripcion, costo, precio_venta as precioventa, stock_actual as stock, \r\n" + 
			"p.image_name as imagen, estado, c.descripcion as categoria, i.descripcion as impuesto\r\n" + 
			"from productos p \r\n" + 
			"join categorias c on c.categoria_id = p.categoria_id\r\n" + 
			"join impuesto i on i.impuesto_id = p.impuesto_id\r\n" + 
			"where estado = 'ACTIVO'", nativeQuery = true)
	List<ProductoReporte> getAllActivosReporte();
	
	
	@Query(value = "select * from productos c \n"
			+ "where ESTADO= 'ACTIVO' ",  nativeQuery = true)
	List<Productos> findActivos();
	
	
	
	
}
