package py.com.spa.app.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import py.com.spa.app.entities.Compras;

public interface IComprasDao extends JpaRepository<Compras, Integer> {

	
	@Query(value = "select * from Compras c \r\n"
			+ "inner join proveedor p on p.proveedor_id= c.proveedor_id\r\n"
			+ "where c.numero_factura like CONCAT('%',UPPER(:id),'%')\r\n"
			+ " or p.empresa like CONCAT('%',UPPER(:id),'%')\r\n",  nativeQuery = true)
	List<Compras> busquedaCompras(@Param("id") String termino);

	
	
	List<Compras> findByFecha(Date fecha);

}
