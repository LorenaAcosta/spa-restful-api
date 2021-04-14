package py.com.spa.app.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JRException;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Compras;
import py.com.spa.app.entities.ComprasDetalle;
import py.com.spa.app.entities.Ventas;
import py.com.spa.app.entities.VentasDetalle;
import py.com.spa.app.reportes.DetalleVentaReportInterface;
import py.com.spa.app.reportes.RankingP;
import py.com.spa.app.reportes.VentaEncabezadoReportInterface;
import py.com.spa.app.services.CategoriaService;
import py.com.spa.app.services.CompraDetalleService;
import py.com.spa.app.services.CompraService;
import py.com.spa.app.services.ProductoService;
import py.com.spa.app.services.VentaDetalleService;
import py.com.spa.app.services.VentaService;

@RestController
@RequestMapping("/detalles-venta")
@CrossOrigin(origins = "*")
public class VentaDetalleRESTController {

	@Autowired
	public VentaDetalleService ventaDetalleService;

	@Autowired
	public VentaService ventaService;

	@Autowired
	public ProductoService productoService;

	@GetMapping("/listar")
	public List<VentasDetalle> listarDetalles() {
		return ventaDetalleService.findAll();
	}

	@GetMapping("/ranking")
	public List<RankingP> listarRanking() {
		return (List<RankingP>) ventaDetalleService.rankingProductos();
	}

	@PostMapping("/agregar")
	public void agregarDetalle(@RequestBody VentasDetalle detalle) {
		ventaDetalleService.addDetalle(detalle);
	}

	@GetMapping("/encontrar/{id}")
	public VentasDetalle obtenerVentasId(@PathVariable(value = "id") Integer id) {
		return (VentasDetalle) ventaDetalleService.findByVentaDetalleId(id);
	}

	@GetMapping("/encontrar-detalles/{id}")
	public List<VentasDetalle> obtenerPorVentasId(@PathVariable Integer id) {
		Ventas v = ventaService.findByVentasId(id);
		return (List<VentasDetalle>) ventaDetalleService.findByVenta(v);
	}

	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificarDetalle(@PathVariable(value = "id") Integer id,
			@RequestBody VentasDetalle detalle) {
		VentasDetalle vd = ventaDetalleService.findByVentaDetalleId(id);
		if (vd != null) {
			vd.setCantidad(detalle.getCantidad());
			vd.setMonto(detalle.getMonto());
			vd.setVentas(detalle.getVentas());
			vd.setPrecio(detalle.getPrecio());
			// -c.setEstado(compra.getEstado());
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarVenta(@PathVariable(value = "id") Integer id) {
		VentasDetalle vd = ventaDetalleService.findByVentaDetalleId(id);
		if (vd != null) {
			ventaDetalleService.deleteDetalles(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

	}
	

}
