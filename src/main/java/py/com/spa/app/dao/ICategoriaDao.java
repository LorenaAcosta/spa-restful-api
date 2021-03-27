package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import py.com.spa.app.entities.Categorias;
import py.com.spa.app.enumeraciones.TipoCategoria;


public interface ICategoriaDao extends JpaRepository<Categorias, Integer>{
	
	//List<Categorias> findByDataType(String dataType);
	List<Categorias> findByDataType(TipoCategoria dataType);

	@Query(value="select descripcion from categorias", nativeQuery = true)
	List<String> findAllByDescripcion();
	

	@Query(value = "select * from categorias c \n"
			+ "where UPPER(c.descripcion) like CONCAT('%',UPPER(:id),'%') ",  nativeQuery = true)
	  List<Categorias> busquedaCategorias(@Param("id") String termino);
}
