package py.com.spa.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.Comprobante;


public interface IComprobanteDao extends  JpaRepository<Comprobante, Integer> {
	

}
