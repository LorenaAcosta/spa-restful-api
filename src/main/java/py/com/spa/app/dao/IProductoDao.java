package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Productos;

public interface IProductoDao extends JpaRepository<Productos, Integer>{
	
	List<Productos> findAllByCategoriaId(Categorias categoria);
}
