package py.com.spa.app.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.entities.Rol;
import py.com.spa.app.entities.Usuario;


//@RepositoryRestResource(path="usuarios")
public interface UsuarioDao extends JpaRepository<Usuario, Integer>{

	Usuario findByUsername(String username);
	Usuario findByEmail(String email);
	
	@Query(value = "select * from usuario u \n"
			+ "where UPPER(u.apellido) like CONCAT('%',UPPER(:id),'%') "
			+ "or UPPER(u.nombre) like CONCAT('%',UPPER(:id),'%') "
			+ "or UPPER(u.correo) like CONCAT('%',UPPER(:id),'%') "
	 		+ "or UPPER(u.username) like CONCAT('%',UPPER(:id),'%')"
	 		+ "or UPPER(cast(u.cedula as varchar)) like CONCAT('%',UPPER(:id),'%')"
	 		+ "or UPPER(cast(u.telefono as varchar)) like CONCAT('%',UPPER(:id),'%') ",  nativeQuery = true)
	  List<Usuario> busquedaClientes(@Param("id") String termino);
	
	@Modifying
	@Transactional
	@Query(value = "insert into usuarios_roles (usuario_id, rol_id) VALUES (:usuarioId,:rolId)", nativeQuery = true)
	void insertUsuariosRoles(@Param("usuarioId") Integer usuarioId, @Param("rolId") Integer rolId);
	
	@Query(value = "select id, nombre from roles", nativeQuery = true)
	List<Rol> obtenerRoles();
}
