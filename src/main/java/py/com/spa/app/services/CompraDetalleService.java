package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.IComprasDao;
import py.com.spa.app.dao.IComprasDetalleDao;
import py.com.spa.app.entities.Compras;
import py.com.spa.app.entities.ComprasDetalle;
import py.com.spa.app.entities.Productos;

@Service
public class CompraDetalleService {
	@Autowired
	private IComprasDetalleDao detallesDao;
	
	@Autowired
	public ProductoService productoService;
	
	@Transactional(readOnly=true)
	public List<ComprasDetalle> findAll(){
		return (List<ComprasDetalle>) detallesDao.findAll();
	}
	
	@Transactional
	public void addDetalle(ComprasDetalle detalle) {
		Productos p;
		detallesDao.save(detalle);
		/* actualizar stock de productos */
		p = productoService.findProductoById(detalle.getProductoId().getProductoId());
		p.setStockActual(p.getStockActual() + detalle.getCantidad());
		productoService.updateProducto(p);
		/* actualizar stock de productos */
	}
	
	@Transactional(readOnly=true)
	public ComprasDetalle findByCompraDetalleId(Integer id) {
		return (ComprasDetalle) detallesDao.findById(id).orElse(null);
	}
	
	@Transactional(readOnly=true)
	public List<ComprasDetalle> findByCompra(Compras compra) {
		return (List<ComprasDetalle>) detallesDao.findByCompras(compra);
		//return (ComprasDetalle) detallesDao.findById(id).orElse(null);
	}

	@Transactional
	public void updateDetalles(ComprasDetalle detalle) {
		ComprasDetalle detalleOld;
		detalleOld = this.findByCompraDetalleId(detalle.getDetalleId());
		detallesDao.save(detalle);
		/* actualizar stock de productos */
		Productos p;
		p = productoService.findProductoById(detalle.getProductoId().getProductoId());
		p.setStockActual((p.getStockActual() + detalle.getCantidad()) - detalleOld.getCantidad());
		productoService.updateProducto(p);
		/* actualizar stock de productos */
		
	}
	
	@Transactional
	public void deleteDetalles(Integer id) {
		/* actualizar stock de productos */
		ComprasDetalle detalle;
		Productos p;
		detalle = this.findByCompraDetalleId(id);
		p = productoService.findProductoById(detalle.getProductoId().getProductoId());
		p.setStockActual(p.getStockActual() - detalle.getCantidad());
		productoService.updateProducto(p);
		/* actualizar stock de productos */
		detallesDao.deleteById(id);
	}
	

	

}
