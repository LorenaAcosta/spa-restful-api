package py.com.spa.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.TiposProducto;

public interface ITiposProductoDao extends JpaRepository<TiposProducto, Integer>  {
	
}
