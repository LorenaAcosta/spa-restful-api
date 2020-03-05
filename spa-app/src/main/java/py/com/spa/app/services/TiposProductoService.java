package py.com.spa.app.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import py.com.spa.app.dao.ITiposProductoDao;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.TiposProducto;

@Service	
public class TiposProductoService {
	@Autowired
	private ITiposProductoDao tiposProductoDao;
	
	@Transactional(readOnly=true)
	public List<TiposProducto> findAll(){
		return (List<TiposProducto>) tiposProductoDao.findAll(); 
	}
	
	@Transactional
	public void addTiposProducto(TiposProducto tiposProducto) {
		tiposProductoDao.save(tiposProducto);
	}
	
	@Transactional(readOnly=true)
	public TiposProducto findTiposProductoById(Integer id) {
		return (TiposProducto) tiposProductoDao.findById(id).orElse(null);
	}
	
	@Transactional
	public void updateTiposProducto(TiposProducto tiposProducto) {
		tiposProductoDao.save(tiposProducto);
	}
	
	@Transactional
	public void deleteTiposProducto(Integer id) {
		tiposProductoDao.deleteById(id);
	}
	
	
	
}
