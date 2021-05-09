package py.com.spa.app.services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.IComprasDao;
import py.com.spa.app.entities.Compras;
import py.com.spa.app.entities.ComprasDetalle;
import py.com.spa.app.entities.Ventas;

@Service
public class CompraService {
	@Autowired
	private IComprasDao comprasDao;
	
	@Autowired
	private CompraDetalleService detalleService;
	
	
	@Transactional(readOnly=true)
	public List<Compras> findAll(){
		return (List<Compras>) comprasDao.findAll();
	}
	
	@Transactional
	public Compras addCompras(Compras compra) {
		return comprasDao.save(compra);
	}
	
	@Transactional(readOnly=true)
	public Compras findByComprasId(Integer id) {
		return (Compras) comprasDao.findById(id).orElse(null);
	}

	@Transactional
	public Compras updateCompras(Compras compra) {
		return comprasDao.save(compra);
	}
	
	@Transactional
	public void deleteCompras(Integer id) {
		/*Se eliminan previamente todos los detalles*/
		Compras c = this.findByComprasId(id);
		Collection<ComprasDetalle> detallesDelete = c.getDetallesCollection();
		for (ComprasDetalle d:detallesDelete) {
			detalleService.deleteDetalles(d.getDetalleId());
		}
		/**/
		comprasDao.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	public List<Compras> busquedaCompras (String termino){
		return comprasDao.busquedaCompras(termino);
	}
	
	@Transactional(readOnly=true)
	public List<Compras> findByFecha(Date fecha) {
		return (List<Compras>) comprasDao.findByFecha(fecha);
	}
	

	

}
