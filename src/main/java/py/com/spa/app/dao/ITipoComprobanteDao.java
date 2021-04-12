package py.com.spa.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.TipoComprobante;

public interface ITipoComprobanteDao extends  JpaRepository<TipoComprobante, Integer> {
	

}
