package py.com.spa.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.Pagos;

public interface IPagoDao extends JpaRepository<Pagos, Integer> {

}
