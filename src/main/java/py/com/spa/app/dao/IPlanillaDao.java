package py.com.spa.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.spa.app.entities.Planilla;

public interface IPlanillaDao extends JpaRepository<Planilla, Integer> {
	
}