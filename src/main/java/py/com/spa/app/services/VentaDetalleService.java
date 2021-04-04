package py.com.spa.app.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import py.com.spa.app.dao.IVentasDetalleDao;
import py.com.spa.app.entities.Productos;
import py.com.spa.app.entities.Ventas;
import py.com.spa.app.entities.VentasDetalle;
import py.com.spa.app.reportes.DetalleVentaReportInterface;
import py.com.spa.app.reportes.RankingP;
import py.com.spa.app.reportes.VentaEncabezadoReportInterface;
import py.com.spa.app.reportes.VentaFooterReportInterface;

@Service
public class VentaDetalleService {
	@Autowired
	private IVentasDetalleDao detallesDao;
	
	
	@Autowired
	public ProductoService productoService;
	
	@Autowired
	public VentaService ventaService;
	
	@Transactional(readOnly=true)
	public List<VentasDetalle> findAll(){
		return (List<VentasDetalle>) detallesDao.findAll();
	}
	
	@Transactional
	public void addDetalle(VentasDetalle detalle) {

		//--------------------------------/
		Productos p;
		detallesDao.save(detalle);
		/* actualizar stock de productos */
		if (detalle.getProductoId() != null) {
		p = productoService.findProductoById(detalle.getProductoId().getProductoId());
		p.setStockActual(p.getStockActual() - detalle.getCantidad());
		productoService.updateProducto(p);
		}
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
		p.setStockActual((p.getStockActual() - detalle.getCantidad()) + detalleOld.getCantidad());
		productoService.updateProducto(p);
		/* actualizar stock de productos */
		
	}
	
	@Transactional
	public void deleteDetalles(Integer id) {
		/* actualizar stock de productos */
		VentasDetalle detalle;
		Productos p;
		detalle = this.findByVentaDetalleId(id);
		if (detalle.getProductoId() != null) {
			p = productoService.findProductoById(detalle.getProductoId().getProductoId());
			p.setStockActual(p.getStockActual() + detalle.getCantidad());
			productoService.updateProducto(p);
			/* actualizar stock de productos */
		}
		detallesDao.deleteById(id);
	}
	
	@Transactional
	public List<RankingP> rankingProductos() {
		return (List<RankingP>) detallesDao.rankingProductos();
	}
	
	
	

}
