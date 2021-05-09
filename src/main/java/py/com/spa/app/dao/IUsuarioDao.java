package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import py.com.spa.app.entities.ReservaDetalle;
import py.com.spa.app.entities.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Integer> {

	//public List<ReservaDetalle> getReservasByClienteId(Integer Id);
	
	public Usuario findByUsername(Usuario categoria);
	
	
	@Query(value = "select * from usuario u \n"
			+ "where UPPER(u.apellido) like CONCAT('%',UPPER(:id),'%') "
			+ "or UPPER(u.nombre) like CONCAT('%',UPPER(:id),'%') "
			+ "or UPPER(u.correo) like CONCAT('%',UPPER(:id),'%') "
	 		+ "or UPPER(u.username) like CONCAT('%',UPPER(:id),'%')"
	 		+ "or UPPER(cast(u.cedula as varchar)) like CONCAT('%',UPPER(:id),'%')"
	 		+ "or UPPER(cast(u.telefono as varchar)) like CONCAT('%',UPPER(:id),'%') ",  nativeQuery = true)
	  List<Usuario> busquedaClientes(@Param("id") String termino);
	
}