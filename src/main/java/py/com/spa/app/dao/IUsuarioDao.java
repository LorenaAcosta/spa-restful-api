package py.com.spa.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Integer> {

	//public List<ReservaDetalle> getReservasByClienteId(Integer Id);
	
	public Usuario findByUsername(Usuario categoria);
	
	
}