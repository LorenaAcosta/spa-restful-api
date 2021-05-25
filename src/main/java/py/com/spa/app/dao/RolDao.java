package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.entities.Empleados;
import py.com.spa.app.entities.Rol;


public interface RolDao extends JpaRepository<Rol, Integer>{
	
	@Query(value = "select r.* from usuario u\r\n" + 
			"join usuarios_roles ur on ur.usuario_id = u.usuario_id\r\n" + 
			"join roles r on r.id = ur.rol_id\r\n" + 
			"where u.usuario_id =:id",  nativeQuery = true)
	  List<Rol> listarRolPorUsuario(@Param("id") Integer usuario);
	
	@Query(value = "select * from roles where id not in (select id from usuario u\r\n" + 
			"join usuarios_roles ur on ur.usuario_id = u.usuario_id\r\n" + 
			"join roles r on r.id = ur.rol_id\r\n" + 
			"where u.usuario_id =:id)",  nativeQuery = true)
	  List<Rol> listarRolNoAsignadosPorUsuario(@Param("id") Integer usuario);
	
	
	/*-----------------Tabla usuarios_roles---------------------------------*/
	
	@Modifying
	@Transactional
	@Query(value = "insert into usuarios_roles (usuario_id, rol_id) VALUES (:usuarioId,:rolId)", nativeQuery = true)
	void insertUsuariosRoles(@Param("usuarioId") Integer usuarioId, @Param("rolId") Integer rolId);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM usuarios_roles WHERE usuario_id =:usuarioId and rol_id =:rolId", nativeQuery = true)
	void deleteUsuariosRoles(@Param("usuarioId") Integer usuarioId, @Param("rolId") Integer rolId);

}
