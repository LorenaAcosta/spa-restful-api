package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.IComprasDao;
import py.com.spa.app.dao.IComprasDetalleDao;
import py.com.spa.app.dao.IVentasDetalleDao;
import py.com.spa.app.entities.Compras;
import py.com.spa.app.entities.ComprasDetalle;
import py.com.spa.app.entities.Productos;
import py.com.spa.app.entities.Ventas;
import py.com.spa.app.entities.VentasDetalle;

@Service
public class VentaDetalleService {
	@Autowired
	private IVentasDetalleDao detallesDao;
	
	@Autowired
	public ProductoService productoService;
	
	@Transactional(readOnly=true)
	public List<VentasDetalle> findAll(){
		return (List<VentasDetalle>) detallesDao.findAll();
	}
	
	@Transactional
	public void addDetalle(VentasDetalle detalle) {
		Productos p;
		detallesDao.save(detalle);
		/* actualizar stock de productos */
		p = productoService.findProductoById(detalle.getProductoId().getProductoId());
		p.setStockActual(p.getStockActual() + detalle.getCantidad());
		productoService.updateProducto(p);
		/* actualizar stock de productos */
	}
	
	@Transactional(readOnly=true)
	public VentasDetalle findByVentaDetalleId(Integer id) {
		return (VentasDetalle) detallesDao.findById(id).orElse(null);
	}
	
	@Transactional(readOnly=true)
	public List<VentasDetalle> findByVenta(Ventas venta) {
		return (List<VentasDetalle>) detallesDao.findByVentas(venta);
		//return (ComprasDetalle) detallesDao.findById(id).orElse(null);
	}

	@Transactional
	public void updateDetalles(VentasDetalle detalle) {
		VentasDetalle detalleOld;
		detalleOld = this.findByVentaDetalleId(detalle.getDetalleId());
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
		VentasDetalle detalle;
		Productos p;
		detalle = this.findByVentaDetalleId(id);
		p = productoService.findProductoById(detalle.getProductoId().getProductoId());
		p.setStockActual(p.getStockActual() - detalle.getCantidad());
		productoService.updateProducto(p);
		/* actualizar stock de productos */
		detallesDao.deleteById(id);
	}
	

	

}
