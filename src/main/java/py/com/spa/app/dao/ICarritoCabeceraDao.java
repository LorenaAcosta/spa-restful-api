package py.com.spa.app.dao;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import py.com.spa.app.entities.Boxes;
import py.com.spa.app.entities.Carrito;
import py.com.spa.app.entities.CarritoCabecera;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Disponible;
import py.com.spa.app.entities.Horario;
import py.com.spa.app.entities.ReservaDetalle;
import py.com.spa.app.entities.Servicios;
import py.com.spa.app.enumeraciones.TipoCategoria;


public interface ICarritoCabeceraDao extends JpaRepository<CarritoCabecera, Integer>{

	@Query(value = "select  coalesce (max(orden) +1 , 1) from carrito_cabecera" ,  nativeQuery = true)
	Integer getmaxid();


	@Query(value = "select * from carrito_cabecera where usuario_id =:usuarioId",  nativeQuery = true)
	List<CarritoCabecera> findByUsuario(Integer usuarioId);
	
	

	
}
