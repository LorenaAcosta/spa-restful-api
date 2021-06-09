package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import py.com.spa.app.entities.Planilla;

public interface IPlanillaDao extends JpaRepository<Planilla, Integer> {

	@Query(value="select * from planilla where mes_pago=:id\r\n"
			+ "order by fecha_pago asc", nativeQuery = true)
	List<Planilla> listarpormes(String id);
	
}