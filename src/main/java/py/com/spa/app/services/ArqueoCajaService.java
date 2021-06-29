package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import py.com.spa.app.dao.IArqueoCajaDao;
import py.com.spa.app.entities.ArqueoCaja;

@Service
public class ArqueoCajaService {
	
	@Autowired
	private IArqueoCajaDao arqueoDao;
	
	@Transactional(readOnly=true)
	public List<ArqueoCaja> findAll(){
		return (List<ArqueoCaja>) arqueoDao.findAll();
	}
	
	
	@Transactional
	public ArqueoCaja addArqueoCaja(ArqueoCaja arqueo) {
		return arqueoDao.save(arqueo);
	}
	
	@Transactional(readOnly=true)
	public ArqueoCaja findByArqueoCajaId(Integer id) {
		return (ArqueoCaja) arqueoDao.findById(id).orElse(null);
	}

	@Transactional
	public void updateArqueoCaja(ArqueoCaja arqueo) {
		arqueoDao.save(arqueo);
	}
	
	@Transactional
	public void deleteArqueoCaja(Integer id) {
		arqueoDao.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	public ArqueoCaja getCajaActiva(Integer punto) {
		ArqueoCaja arqueo = (ArqueoCaja) arqueoDao.getCajaActiva(punto);
		if (arqueo != null) {
			return arqueo;
		} else {
			arqueo = null;
			return arqueo;
		}
		
	}
}
