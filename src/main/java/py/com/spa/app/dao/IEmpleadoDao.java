package py.com.spa.app.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import py.com.spa.app.entities.Empleados;
import py.com.spa.app.reportes.EmpleadoReporte;

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
	
	
	/*dao para reporte*/
	@Query(value="Select ROW_NUMBER() OVER (ORDER BY e.empleado_id) as item, cedula, nombre, apellido, \r\n" + 
			"direccion, telefono, celular, correo, nacionalidad, ciudad, estado_civil as estadocivil, \r\n" + 
			"fecha_ingreso as fechaingreso, fecha_nac as fechanac, fecha_salida as fechasalida, funcion, \r\n" + 
			"image_name as imagen, sueldo, estado\r\n" + 
			"from empleados e", nativeQuery = true)
	List<EmpleadoReporte> getEmpleadosReporte();
	

}