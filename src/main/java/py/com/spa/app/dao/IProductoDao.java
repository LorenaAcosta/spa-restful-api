package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Productos;

public interface IProductoDao extends JpaRepository<Productos, Integer>{
	
	List<Productos> findAllByCategoriaId(Categorias categoria);
	
	@Query(value = "select * from productos c \n"
			+ "where UPPER(c.descripcion) like CONCAT('%',UPPER(:id),'%') ",  nativeQuery = true)
	  List<Productos> busquedaProductos(@Param("id") String termino);
}
