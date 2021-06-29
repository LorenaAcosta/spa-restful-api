package py.com.spa.app.controller;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
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
import py.com.spa.app.entities.PuntoExpedicion;
import py.com.spa.app.entities.Ventas;
import py.com.spa.app.services.ComprobanteService;
import py.com.spa.app.services.PuntoExpedicionService;
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
	
	@Autowired
	public PuntoExpedicionService puntoExpedicionService;
	
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
		comp.setNumeroActual(comprobanteService.numeroActualPorPunto(comp.getPuntoExpedicionId().getPuntoExpedicionId()));
		PuntoExpedicion pex = puntoExpedicionService.findByPuntoExpedicionId(comp.getPuntoExpedicionId().getPuntoExpedicionId());
		venta.setNumeroComprobante(comprobanteService.numeroActualPorPunto(comp.getPuntoExpedicionId().getPuntoExpedicionId()));
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
		venta.setComprobanteCompleto("001-"+pex.getCodigo() + "-" + fmt.format("%07d", venta.getNumeroComprobante()).toString());
		System.out.println("El numero formateado " + fmt);
		
		//monto a letras
		NumeroALetra NumLetra = new NumeroALetra();
		String montoString = String.valueOf(venta.getMontoTotal());
		String numero = montoString;
		System.out.println( numero  + " literal = "  + NumLetra.Convertir(numero,true));
		venta.setMontoTotalLetras(NumLetra.Convertir(numero,true));
		
		ventaService.addVentas(venta);
		comprobanteService.updateComprobante(comp);
		return new ResponseEntity<Ventas>(venta, HttpStatus.OK);
	}
	
	@GetMapping("/encontrar/{id}")
	public Ventas obtenerVentasId(@PathVariable(value="id") Integer id) {
		return (Ventas) ventaService.findByVentasId(id);
	}
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificarVenta(@PathVariable(value="id") Integer id, @RequestBody String estado) {
		Ventas c = ventaService.findByVentasId(id);
		if(c!=null) {
			c.setEstado(estado);
			ventaService.updateVentas(c);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	

	@GetMapping("/busqueda-ventas/{id}")
	public ResponseEntity<?>  busquedaVentas(@PathVariable(value="id") String termino)  {
		List<Ventas> lista = null;
		Map<String, Object> response = new HashMap<>();
		try {
			lista= ventaService.busquedaVentas(termino);
		}catch( DataAccessException e ){
			response.put("mensaje",  "No se encontraron datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (lista==null) {
			response.put("mensaje",  "No hay datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Ventas>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/listarporfecha/{fecha}")
	public List<Ventas> findByFechaReserva(@PathVariable(value="fecha") String fecha) throws ParseException {
		Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
		return (List<Ventas>) ventaService.findByFechaReserva(date1);
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
	
	@GetMapping("/listar/{id}")
	public List<Ventas> getVentasPorPuntoExpedicion(@PathVariable(value="id") Integer id) {
		return ventaService.getVentasPorPuntoExpedicion(id);		
	}
	
	@GetMapping("/get-total/{id}")
	public Long getTotalVentasPorArqueo(@PathVariable(value="id") Integer id) {
		return ventaService.getTotalVentasPorArqueo(id);		
	}
	
	//reportes
	
    @GetMapping("/report/{ventaId}")
    public void generateReport(@PathVariable Integer ventaId) throws FileNotFoundException, JRException {
        String r = ventaService.exportReport(ventaId);
        System.out.print(r);
    }
    
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename){
        Resource file = ventaService.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\""+file.getFilename() + "\"").body(file);
    }

	
}
