package py.com.spa.app.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Empleados;
import py.com.spa.app.entities.Usuario;

public interface IEmpleadoDao extends JpaRepository<Empleados, Integer> {

	Empleados findByCedula(Integer cedula);
	
	@Query(value = "select * from empleados e \n"
			+ "where UPPER(e.apellido) like CONCAT('%',UPPER(:id),'%') "
			+ "or UPPER(e.nombre) like CONCAT('%',UPPER(:id),'%') "
			+ "or UPPER(e.direccion) like CONCAT('%',UPPER(:id),'%') "
	 		+ "or UPPER(e.telefono) like CONCAT('%',UPPER(:id),'%')"
	 		+ "or UPPER(cast(e.cedula as varchar)) like CONCAT('%',UPPER(:id),'%')"
	 		+ "or UPPER(cast(e.telefono as varchar)) like CONCAT('%',UPPER(:id),'%') ",  nativeQuery = true)
	  List<Empleados> busquedaEmpleados(@Param("id") String termino);

}