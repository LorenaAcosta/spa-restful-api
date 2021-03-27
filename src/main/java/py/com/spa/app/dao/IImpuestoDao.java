package py.com.spa.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.Impuesto;

public interface IImpuestoDao extends  JpaRepository<Impuesto, Integer> {
	

}
