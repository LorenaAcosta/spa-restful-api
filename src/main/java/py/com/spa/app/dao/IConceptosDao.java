package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import py.com.spa.app.entities.Conceptos;

public interface IConceptosDao extends JpaRepository<Conceptos, Integer>{


	@Query(value="select * from conceptos where tipo =:tipo", nativeQuery = true)
	List<Conceptos> listarConceptos(String tipo);

}
