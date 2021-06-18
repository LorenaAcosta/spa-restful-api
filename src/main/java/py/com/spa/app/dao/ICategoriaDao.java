package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import py.com.spa.app.entities.Categorias;
import py.com.spa.app.enumeraciones.TipoCategoria;
import py.com.spa.app.reportes.CategoriaReporte;
import py.com.spa.app.reportes.Datos;


public interface ICategoriaDao extends JpaRepository<Categorias, Integer>{
	
	//List<Categorias> findByDataType(String dataType);
	List<Categorias> findByDataType(TipoCategoria dataType);
	
	@Query(value="select * from categorias where data_type=:id", nativeQuery = true)
	List<Categorias> findByDataTypee(@Param("id")TipoCategoria dataType);

	@Query(value="select descripcion from categorias", nativeQuery = true)
	List<String> findAllByDescripcion();
	

	@Query(value = "select * from categorias c \n"
			+ "where UPPER(c.descripcion) like CONCAT('%',UPPER(:id),'%') ",  nativeQuery = true)
	  List<Categorias> busquedaCategorias(@Param("id") String termino);
	
	
	//dao para reporte
	@Query(value="Select ROW_NUMBER() OVER (ORDER BY c.categoria_id ) as item, data_type as tipo, descripcion "
			+ "from categorias c", nativeQuery = true)
	List<CategoriaReporte> getAllReporte();

	@Query(value=" select count(servicio_id) as data, 'servicios' as descripcion from servicios\r\n"
			+ "union \r\n"
			+ "select count(producto_id) as data  , 'productos' as descripcion from productos\r\n"
			+ "union \r\n"
			+ "select count(reserva_id) as data, 'reservas' as descripcion from reserva_detalle", nativeQuery = true)
	List<Datos> getdatos();
}
