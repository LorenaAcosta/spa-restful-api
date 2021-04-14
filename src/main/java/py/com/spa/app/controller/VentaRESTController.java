package py.com.spa.app.controller;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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
import py.com.spa.app.entities.Comprobante;
import py.com.spa.app.entities.Ventas;
import py.com.spa.app.services.ComprobanteService;
import py.com.spa.app.services.VentaService;
import py.com.spa.app.util.NumeroALetra;

@RestController
@RequestMapping("/ventas")
@CrossOrigin(origins = "*")
public class VentaRESTController {

	@Autowired
	public VentaService ventaService;
	
	@Autowired
	public ComprobanteService comprobanteService;
	
	@GetMapping("/listar")
	public List<Ventas> listarVentas(){
		return ventaService.findAll();
	}
	

	@GetMapping("/next-id")
	public Integer getNextId() {
		return (Integer) ventaService.getNextId();
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregarVenta(@RequestBody Ventas venta) {
		Comprobante comp = comprobanteService.findByComprobanteId(venta.getComprobanteId().getComprobanteId());
		comp.setNumeroActual(comprobanteService.numeroActual());
		venta.setNumeroComprobante(comprobanteService.numeroActual());
		venta.setIvaCinco(Math.round(venta.getIvaCinco()) * 1L);
		venta.setIvaDiez(Math.round(venta.getIvaDiez()) * 1L);
		venta.setIvaTotal(Math.round(venta.getIvaTotal()) * 1L);
		venta.setTotalDescuento(0L);
		venta.setSubTotalCinco(Math.round(venta.getSubTotalCinco()) * 1L);
		venta.setSubTotalDiez(Math.round(venta.getSubTotalDiez()) * 1L);
		venta.setSubTotalExenta(Math.round(venta.getSubTotalExenta()) * 1L);
		venta.setSubTotalTotal(Math.round(venta.getSubTotalTotal()) * 1L);
		//formatear numero de factura
		@SuppressWarnings("resource")
		Formatter fmt = new Formatter();
		venta.setComprobanteCompleto("001-001-" + fmt.format("%07d", venta.getNumeroComprobante()).toString());
		System.out.println("El numero formateado " + fmt);
		
		//monto a letras
		NumeroALetra NumLetra = new NumeroALetra();
		String montoString = String.valueOf(venta.getMontoTotal());
		String numero = montoString;
		System.out.println( numero  + " literal = "  + NumLetra.Convertir(numero,true));
		venta.setMontoTotalLetras(NumLetra.Convertir(numero,true));
		
		ventaService.addVentas(venta);
		return new ResponseEntity<Ventas>(venta, HttpStatus.OK);
	}
	
	@GetMapping("/encontrar/{id}")
	public Ventas obtenerVentasId(@PathVariable(value="id") Integer id) {
		return (Ventas) ventaService.findByVentasId(id);
	}
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificarVenta(@PathVariable(value="id") Integer id, @RequestBody Ventas venta) {
		Ventas c = ventaService.findByVentasId(id);
		System.out.println(venta.toString());
		if(c!=null) {
			c.setFecha(venta.getFecha());
			c.setMontoTotal(venta.getMontoTotal());
			c.setIvaCinco(venta.getIvaCinco());
			c.setIvaDiez(venta.getIvaDiez());
			c.setIvaTotal(venta.getIvaTotal());
			c.setSubTotalTotal(venta.getSubTotalTotal());
			c.setMedioPagoId(venta.getMedioPagoId());
			c.setEstado(venta.getEstado());
			ventaService.updateVentas(c);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	// en realidad cambia estado a anulado
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarVenta(@PathVariable(value="id") Integer id) {
		Ventas v = ventaService.findByVentasId(id);
		if (v!=null) {
			v.setEstado("anulado");
			ventaService.updateVentas(v);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
	}
	
    @GetMapping("/report/{ventaId}")
    public String generateReport(@PathVariable Integer ventaId) throws FileNotFoundException, JRException {
        return ventaService.exportReport(ventaId);
    }
    
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename){
        Resource file = ventaService.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\""+file.getFilename() + "\"").body(file);
    }

	
}
