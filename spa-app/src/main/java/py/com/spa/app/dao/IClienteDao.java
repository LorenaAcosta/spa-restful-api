package py.com.spa.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.Clientes;

public interface IClienteDao extends JpaRepository<Clientes, Integer> {

	
}