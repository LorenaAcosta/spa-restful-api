package py.com.spa.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.Usuario;


//@RepositoryRestResource(path="usuarios")
public interface UsuarioDao extends JpaRepository<Usuario, Integer>{

	Usuario findByUsername(String username);
	Usuario findByEmail(String email);
}
