package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.Categorias;


public interface ICategoriaDao extends JpaRepository<Categorias, Integer>{
	
	List<Categorias> findByDataType(String dataType);

	
	@Query(value = "select * from categoria c \n"
			+ "where UPPER(c.descripcion) like CONCAT('%',UPPER(:id),'%') ",  nativeQuery = true)
	  List<Categoria> busquedaCategorias(@Param("id") String termino);
}
