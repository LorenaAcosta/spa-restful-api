package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.Reservas;

public interface IReservaDao extends JpaRepository<Reservas, Integer>{
	
	public List<Reservas> findByClienteId(Integer id);

}
