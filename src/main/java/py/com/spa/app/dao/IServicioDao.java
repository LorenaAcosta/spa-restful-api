package py.com.spa.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.spa.app.entities.Servicios;

public interface IServicioDao extends JpaRepository<Servicios, Integer>{

}
