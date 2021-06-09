package py.com.spa.app.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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
import py.com.spa.app.dao.IReservaDetalleDao;
import py.com.spa.app.entities.ReservaDetalle;
import py.com.spa.app.entities.Servicios;
import py.com.spa.app.reportes.ReservaReporte;
import py.com.spa.app.reportes.ServiciosReservadosPorClienteFecha;

@Service
public class ReservaDetalleService {
	
	@Autowired
	private IReservaDetalleDao reservaDao;
	
	private final Path rootReportes = Paths.get("reportes/reservas");
	
	
	@Transactional(readOnly=true)
	public List<ReservaDetalle> findAll(){
		return (List<ReservaDetalle>) reservaDao.findAll();
	}
	
	@Transactional(readOnly=true)
	public List<ReservaDetalle> misReservas(Integer usuarioId){
		return (List<ReservaDetalle>) reservaDao.findByUsuarioId(usuarioId);
	}
	
	@Transactional
	public void addReservaDetalle(ReservaDetalle reserva) {
		System.out.println(reserva.getFechaReserva());
		reservaDao.save(reserva);
	}


	@Transactional
	public void updateReserva(ReservaDetalle reserva) {
		reservaDao.save(reserva);
	}
	
	@Transactional
	public void deleteReserva(Integer id) {
		reservaDao.deleteById(id);
	}
	
	@Transactional
	public List<ReservaDetalle> findByEmpleado(Integer empleado){
		return (List<ReservaDetalle>) reservaDao.findByEmpleado(empleado);
	}
	

	@Transactional
	public List<ReservaDetalle> findAllByEmpleadoAndFechaReservaOrderByHoraAsc(Integer empleado, Date date) {
		return (List<ReservaDetalle>) reservaDao.findAllByEmpleadoAndFechaReservaOrderByHoraAsc(empleado, date);
	}
	
	@Transactional(readOnly=true)
	public ReservaDetalle findByReservaDetalleId(Integer id) {
		return (ReservaDetalle) reservaDao.findById(id).orElse(null);
	}
	
	@Transactional(readOnly=true)
	public List<ReservaDetalle> findByFechaReserva(Date fechaReserva) {
		return (List<ReservaDetalle>) reservaDao.findByFechaReserva(fechaReserva);
	}

	
	@Transactional(readOnly=true)
	public List<ReservaDetalle> busquedaReservas (String termino){
		return reservaDao.busquedaReservas(termino);
	}
	
	@Transactional(readOnly=true)
	public List<ReservaDetalle> findByFechaReservaAndHora (Date fecha, Time hora){
		return reservaDao.findByFechaReservaAndHora(fecha, hora);
	}
	
	@Transactional(readOnly=true)
	public List<ServiciosReservadosPorClienteFecha> serviciosReservadosPorClienteFecha(Integer id){
		return (List<ServiciosReservadosPorClienteFecha>) reservaDao.serviciosReservadosPorClienteFecha(id);
	}
	
	@Transactional
	public void cambiarEstadoPagado(Integer reservaId) {
		reservaDao.cambiarEstadoPagado(reservaId);
	}
	
	
	/*Reporte*/
	public String exportReport(Date fecha) throws FileNotFoundException, JRException {
		
		
        
        List<ReservaReporte> listItems = (List<ReservaReporte>)reservaDao.getPorFechaReporte(fecha);

  
        String outputFile = "reportes/reservas/" + "reservas_por_fecha.pdf";
        
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItems);

        /* Map to hold Jasper report Parameters */
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("CollectionBeanParam", itemsJRBean);
        
        //read jrxml file and creating jasperdesign object
        InputStream input = new FileInputStream(new File("reportes/reservas/reservas.jrxml"));
                            
        JasperDesign jasperDesign = JRXmlLoader.load(input);
        
        /*compiling jrxml with help of JasperReport class*/
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        /* Using jasperReport object to generate PDF */
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        
        /* outputStream to create PDF */
        OutputStream outputStream = new FileOutputStream(new File(outputFile));
        
        /* Write content to PDF file */
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        
        JasperExportManager.exportReportToHtmlFile(jasperPrint, "reportes/reservas/reservas_por_fecha.html");

        System.out.println("File Generated");	
        
        //load("factura.pdf");
        
        return "Reporte generado";
      
    }
	
    public Resource load(String filename) {
        try {
            Path file = rootReportes.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()){
                return resource;
            }else{
                throw new RuntimeException("No se puede leer el archivo ");
            }

        }catch (MalformedURLException e){
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
    
    @Transactional(readOnly=true)
	public List<ReservaDetalle> reservasConfirmadas (Integer empleadoId, Integer mes){
		return reservaDao.reservasConfirmadas(empleadoId, mes);
	}
	

	

}
