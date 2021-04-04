package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Servicios;
import py.com.spa.app.enumeraciones.EstadoServicio;
import py.com.spa.app.enumeraciones.TipoCategoria;

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

	
	@Query(value = "select * from servicios s\r\n"
			+ "where s.servicio_id not in(select servicio_id \r\n"
			+ "	 					from disponible dbx\r\n"
			+ "	 					where s.servicio_id= dbx.servicio_id\r\n"
			+ "	 					and dbx.empleado_id = :empleadoId )",  nativeQuery = true)	
	List<Servicios> getBoxesDisponibles(Integer empleadoId);


}
