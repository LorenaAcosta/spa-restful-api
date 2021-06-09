package py.com.spa.app.dao;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import py.com.spa.app.entities.Boxes;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Disponible;
import py.com.spa.app.entities.Horario;
import py.com.spa.app.entities.Servicios;
import py.com.spa.app.enumeraciones.TipoCategoria;


public interface IBoxesDao extends JpaRepository<Boxes, Integer>{
	
	
	/*

	select disponible_boxes_id from disponible_boxes d
	where d.disponible_boxes_id not in (select r.disponible_boxes_id from reserva_detalle r
										where fecha_reserva='2021-03-02' and hora='13:00'
									   )
									   and d.servicio_id=1 */
									   
	 @Query(value = "select d.disponible_boxes_id \r\n"
	 		+ "from disponible_boxes d\r\n"
	 		+ "where d.disponible_boxes_id not in (select r.disponible_boxes_id \r\n"
	 		+ "									from reserva_detalle r \r\n"
	 		+ "									where r.fecha_reserva= :fecha and r.hora= :hora and r.estado!='ANULADO') \r\n"
	 		+ "and d.servicio_id= :servicioId",  nativeQuery = true)
	List<Integer> obtenerBoxLibre(@PathVariable(value="fecha") Date fecha
			,@PathVariable(value="hora") Time hora,
			 @PathVariable(value="servicioId") Integer servicioId);

	 
	 @Query(value = "select * from boxes b\r\n"
	 		+ "where b.boxes_id not in(select boxes_id \r\n"
	 		+ "						from disponible_boxes dbx\r\n"
	 		+ "					 where b.boxes_id= dbx.boxes_id\r\n"
	 		+ "					 and dbx.servicio_id=:servicioId )",  nativeQuery = true)
	List<Boxes> getBoxesDisponibles(Integer servicioId);

	
}
