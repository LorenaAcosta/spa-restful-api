package py.com.spa.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.spa.app.entities.ReservaDetalle;


public interface IReservaDetalleDao  extends JpaRepository<ReservaDetalle, Integer>{

}
