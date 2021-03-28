package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import py.com.spa.app.entities.Boxes;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.enumeraciones.TipoCategoria;


public interface IBoxesDao extends JpaRepository<Boxes, Integer>{
	



	
}
