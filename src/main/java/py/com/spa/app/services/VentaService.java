package py.com.spa.app.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

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
import py.com.spa.app.dao.ICategoriaDao;
import py.com.spa.app.dao.IVentasDao;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Ventas;
import py.com.spa.app.entities.VentasDetalle;
import py.com.spa.app.reportes.DetalleVentaReportInterface;
import py.com.spa.app.reportes.VentaEncabezadoReportInterface;
import py.com.spa.app.reportes.VentaFooterReportInterface;

@Service
public class VentaService {
	@Autowired
	private IVentasDao ventasDao;
	
	private final Path rootReportes = Paths.get("reportes/ventas");
	
	@Transactional(readOnly=true)
	public List<Ventas> findAll(){
		//return (List<Ventas>) ventasDao.findAll();
		return (List<Ventas>) ventasDao.ventasActivas();
		
	}
	
	@Transactional
	public void addVentas(Ventas venta) {
		ventasDao.save(venta);
	}
	
	@Transactional(readOnly=true)
	public Ventas findByVentasId(Integer id) {
		return (Ventas) ventasDao.findById(id).orElse(null);
	}
	
	@Transactional(readOnly=true)
	public Integer getNextId() {
		return (Integer) ventasDao.getNextIdVal();
	}

	@Transactional
	public void updateVentas(Ventas venta) {
		ventasDao.save(venta);
	}
	
	@Transactional
	public void deleteVentas(Integer id) {
		ventasDao.deleteById(id);
	}
	
	public String exportReport(Integer ventaId) throws FileNotFoundException, JRException {
		
		
        
        List<DetalleVentaReportInterface> listItems = (List<DetalleVentaReportInterface>)ventasDao.getDetallesPorVentaReport(ventaId);

        List <VentaEncabezadoReportInterface> listItems1 = (List <VentaEncabezadoReportInterface>)ventasDao.getVentasEncabezadoReport(ventaId);
        String outputFile = "reportes/ventas/" + "factura.pdf";
        List <VentaFooterReportInterface> listItems2 = (List <VentaFooterReportInterface>)ventasDao.getVentasFooterReport(ventaId);
        
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItems);
        JRBeanCollectionDataSource itemsJRBean1 = new JRBeanCollectionDataSource(listItems1);
        JRBeanCollectionDataSource itemsJRBean2 = new JRBeanCollectionDataSource(listItems2);

        /* Map to hold Jasper report Parameters */
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("CollectionBeanParam", itemsJRBean);
        parameters.put("CollectionBeanParam1", itemsJRBean1);
        parameters.put("CollectionBeanParam2", itemsJRBean2);
        
        //read jrxml file and creating jasperdesign object
        InputStream input = new FileInputStream(new File("reportes/ventas/factura.jrxml"));
                            
        JasperDesign jasperDesign = JRXmlLoader.load(input);
        
        /*compiling jrxml with help of JasperReport class*/
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        /* Using jasperReport object to generate PDF */
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        /*call jasper engine to display report in jasperviewer window*/
        //JasperViewer.viewReport(jasperPrint);
        
        /* outputStream to create PDF */
        OutputStream outputStream = new FileOutputStream(new File(outputFile));
        
        /* Write content to PDF file */
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        
        JasperExportManager.exportReportToHtmlFile(jasperPrint, "reportes/ventas/factura.html");

        System.out.println("File Generated");	
        
        load("factura.pdf");
        
        return "File generado";
      
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


}
