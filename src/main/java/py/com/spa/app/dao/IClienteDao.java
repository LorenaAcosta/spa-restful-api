package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.Clientes;
import py.com.spa.app.entities.Reservas;

public interface IClienteDao extends JpaRepository<Clientes, Integer> {

	public List<Reservas> getReservasByClienteId(Integer Id);
	
}