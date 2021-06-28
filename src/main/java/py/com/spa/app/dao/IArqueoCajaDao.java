package py.com.spa.app.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.ArqueoCaja;

public interface IArqueoCajaDao extends  JpaRepository<ArqueoCaja, Integer> {
	
}
