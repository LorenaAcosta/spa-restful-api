package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Proveedor;
import py.com.spa.app.entities.Usuario;

public interface IProveedorDao extends JpaRepository<Proveedor, Integer> {

	
	@Query(value = "select * from proveedores u \n"
			+ "where UPPER(u.empresa) like CONCAT('%',UPPER(:id),'%') "
			+ "or UPPER(u.razon_social) like CONCAT('%',UPPER(:id),'%') "
			+ "or UPPER(u.correo) like CONCAT('%',UPPER(:id),'%') "
	 		+ "or UPPER(u.nombre_gerente) like CONCAT('%',UPPER(:id),'%')"
	 		+ "or UPPER(cast(u.nombre_proveedor as varchar)) like CONCAT('%',UPPER(:id),'%')"
	 		+ "or UPPER(cast(u.ruc as varchar)) like CONCAT('%',UPPER(:id),'%') ",  nativeQuery = true)
	  List<Proveedor> busquedaProveedores(@Param("id") String termino);
	
}
