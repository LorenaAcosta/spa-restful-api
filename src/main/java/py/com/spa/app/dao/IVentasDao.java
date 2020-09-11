package py.com.spa.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.spa.app.entities.Ventas;

public interface IVentasDao extends JpaRepository<Ventas, Integer>{

}
