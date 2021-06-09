package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Productos;
import py.com.spa.app.entities.Servicios;
import py.com.spa.app.enumeraciones.EstadoServicio;
import py.com.spa.app.enumeraciones.TipoCategoria;
import py.com.spa.app.reportes.ProductoReporte;
import py.com.spa.app.reportes.ServicioReporte;

public interface IServicioDao extends JpaRepository<Servicios, Integer>{
	

	List<Servicios> findAllByCategoriaId(Categorias categoria);
	
	//List<Servicios> getServiciosByEstado(String estado);
	List<Servicios> getServiciosByEstado(EstadoServicio estado);
	
	
	@Query(value="select * from servicios s \r\n"
			+ "where s.servicio_id  in (select s.servicio_id from disponible_boxes b \r\n"
			+ "					where b.servicio_id = s.servicio_id)\r\n"
			+ "and s.servicio_id in (select s.servicio_id from disponible dd \r\n"
			+ "					where dd.servicio_id = s.servicio_id)  \r\n"
			+ "and s.categoria_id= :categoriaId  and s.estado='ACTIVO' ", nativeQuery = true)
	List<Servicios> getServiciosActivos(@Param("categoriaId") Integer categoria);
	

	@Query(value = "select * from servicios c \n"
			+ "where UPPER(c.nombre) like CONCAT('%',UPPER(:id),'%') or  "
			+ " UPPER(c.descripcion) like CONCAT('%',UPPER(:id),'%') ",  nativeQuery = true)
	  List<Servicios> busquedaServicios(@Param("id") String termino);
	
	@Query(value = "select * from servicios c \n"
			+ "where UPPER(c.nombre) = UPPER(:nombre) ",  nativeQuery = true)
	  Servicios busquedaPorNombre(@Param("nombre") String termino);

	
	@Query(value = "select * from servicios s\r\n"
			+ "where s.servicio_id not in(select servicio_id \r\n"
			+ "	 					from disponible dbx\r\n"
			+ "	 					where s.servicio_id= dbx.servicio_id\r\n"
			+ "	 					and dbx.empleado_id = :empleadoId )",  nativeQuery = true)	
	List<Servicios> getBoxesDisponibles(Integer empleadoId);
	
	
	/*dao para reporte*/
	@Query(value="Select ROW_NUMBER() OVER (ORDER BY s.servicio_id ) as item, nombre,\r\n" + 
			"	s.descripcion, c.descripcion as categoria, duracion, costo, i.descripcion as impuesto,\r\n" + 
			"	s.estado, s.image_name as imagen\r\n" + 
			"from servicios s\r\n" + 
			"	join categorias c on c.categoria_id = s.categoria_id\r\n" + 
			"	join impuesto i on i.impuesto_id = s.impuesto_id\r\n" + 
			"where estado = 'ACTIVO'", nativeQuery = true)
	List<ServicioReporte> getAllActivosReporte();

	
	
	@Query(value="select * from servicios s \r\n"
			+ "where s.estado='ACTIVO' ", nativeQuery = true)
	List<Servicios> findActivos();


}
