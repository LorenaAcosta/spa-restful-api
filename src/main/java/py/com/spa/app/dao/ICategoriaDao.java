package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import py.com.spa.app.entities.Categorias;
import py.com.spa.app.enumeraciones.TipoCategoria;


public interface ICategoriaDao extends JpaRepository<Categorias, Integer>{
	
	//List<Categorias> findByDataType(String dataType);
	List<Categorias> findByDataType(TipoCategoria dataType);

	@Query(value="select descripcion from categorias", nativeQuery = true)
	List<String> findAllByDescripcion();
}
