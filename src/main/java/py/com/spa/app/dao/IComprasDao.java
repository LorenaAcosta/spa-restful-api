package py.com.spa.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.Compras;

public interface IComprasDao extends JpaRepository<Compras, Integer> {

}
