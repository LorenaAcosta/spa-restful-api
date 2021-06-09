package py.com.spa.app.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.RolDao;
import py.com.spa.app.dao.UsuarioDao;
import py.com.spa.app.entities.Rol;
import py.com.spa.app.entities.Usuario;
import py.com.spa.app.validators.ApiUnprocessableEntity;
import py.com.spa.app.validators.UsuarioValidatorImpl;


@Service
public class UsuariosService {

	
		@Autowired
		private UsuarioDao usuarioDao;
		
		@Autowired
		private RolDao rolDao;
		
		@Transactional(readOnly=true)
		public List<Usuario> findAll(){
			return (List<Usuario>) usuarioDao.findAll();
		}
		
		@Transactional
		public void addUsuario(Usuario usuario) {
			usuario.setNombres(usuario.getNombre().concat(" ").concat(usuario.getApellido()));
			if (usuario.getRuc() == null || usuario.getRuc() == "") {
				usuario.setRuc(usuario.getCedula());
			}
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			usuario.setPassword(encoder.encode(usuario.getPassword()));
			usuarioDao.save(usuario);
		}
		
		@Transactional(readOnly=true)
		public Usuario findByUsuarioId(Integer id) {
			return (Usuario) usuarioDao.findById(id).orElse(null);
		}

		@Transactional
		public void updateUsuario(Usuario usuario) {
			usuarioDao.save(usuario);
		}
		
		@Transactional
		public void deleteUsuario(Integer id) {
			usuarioDao.deleteById(id);
		}
		/*------------------Roles-----------------------*/
		@Transactional
		public void insertUsuariosRoles(Integer usuarioId, Integer rolId) {
			usuarioDao.insertUsuariosRoles(usuarioId, rolId);
		}
		
		@Transactional
		public List<Rol> obtenerRoles() {
			return (List<Rol>) rolDao.findAll();
		}
	
}
