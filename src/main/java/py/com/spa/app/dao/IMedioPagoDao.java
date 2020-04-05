package py.com.spa.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.MediosPago;

public interface IMedioPagoDao extends JpaRepository<MediosPago, Integer>{

}
