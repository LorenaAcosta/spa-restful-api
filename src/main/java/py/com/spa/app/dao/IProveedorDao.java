package py.com.spa.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Proveedor;

public interface IProveedorDao extends JpaRepository<Proveedor, Integer> {

}
