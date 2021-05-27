package py.com.spa.app.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.RolDao;
import py.com.spa.app.entities.Rol;


@Service
public class RolService {

	
		@Autowired
		private RolDao rolDao;

		
		
		@Transactional(readOnly=true)
		public List<Rol> findAll(){
			return (List<Rol>) rolDao.findAll();
		}
		
		@Transactional(readOnly=true)
		public List<Rol> listarRolPorUsuario(Integer usuario){
			return (List<Rol>) rolDao.listarRolPorUsuario(usuario);
		}
		
		@Transactional(readOnly=true)
		public List<Rol> listarRolNoAsignadosPorUsuario(Integer usuario){
			return (List<Rol>) rolDao.listarRolNoAsignadosPorUsuario(usuario);
		}
		
		@Transactional
		public void addRol(Rol rol) {
			rolDao.save(rol);
		}
		
		@Transactional(readOnly=true)
		public Rol findByRolId(Integer id) {
			return (Rol) rolDao.findById(id).orElse(null);
		}

		@Transactional
		public void updateRol(Rol rol) {
			rolDao.save(rol);
		}
		
		@Transactional
		public void deleteRol(Integer id) {
			rolDao.deleteById(id);
		}
		
		//Tabla usuarios_roles
		
		@Transactional
		public void insertUsuariosRoles(Integer usuarioId, Integer rolId) {
			rolDao.insertUsuariosRoles(usuarioId, rolId);
		}
		
		@Transactional
		public void deleteUsuariosRoles(Integer usuarioId, Integer rolId) {
			rolDao.deleteUsuariosRoles(usuarioId, rolId);
		}
	
}
