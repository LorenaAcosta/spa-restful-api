package py.com.spa.app.services;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.IUsuarioDao;
import py.com.spa.app.entities.Usuario	;
import py.com.spa.app.entities.ReservaDetalle;

@Service
public class UsuarioService {
	@Autowired
	private IUsuarioDao UsuarioDao;
	
	@Transactional(readOnly=true)
	public List<Usuario> findAll(){
		return (List<Usuario>) UsuarioDao.findAll(); 
	}
	
	@Transactional
	public Usuario addUsuario(Usuario Usuario) {
		return UsuarioDao.save(Usuario);
	}

	@Transactional
	public Usuario updateUsuario(Usuario Usuario) {
		return UsuarioDao.save(Usuario);
	}
	
	@Transactional
	public void deleteUsuario(Integer id) {
		UsuarioDao.deleteById(id);
	}
	
	/*
	 * @Transactional
	
	public List<ReservaDetalle> getReservasByUsuarioId(Integer id){
		List<ReservaDetalle> reservas= UsuarioDao.getReservasByUsuarioId(id);
		return reservas;
	}
 */
	@Transactional(readOnly=true)	
	public Usuario findUsuarioById(Integer id) {
		return (Usuario) UsuarioDao.findById(id).orElse(null);
	}
	
	@Transactional(readOnly=true)
	public List<Usuario> busquedaClientes (String termino){
		return UsuarioDao.busquedaClientes(termino);
	}



}
