package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.ReservaDetalle;

public interface IReservaDao extends JpaRepository<ReservaDetalle, Integer>{
	
	public List<ReservaDetalle> findByClienteId(Integer id);

}
