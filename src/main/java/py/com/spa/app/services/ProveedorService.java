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
import py.com.spa.app.dao.IProveedorDao;
import py.com.spa.app.entities.Proveedor;
import py.com.spa.app.reportes.ProveedorReporte;

@Service
public class ProveedorService {

	@Autowired
	private IProveedorDao proveedorDao;
	
	private final Path rootReportes = Paths.get("reportes/proveedores");
	
	@Transactional(readOnly=true)
	public List<Proveedor> findAll(){
		return (List<Proveedor>) proveedorDao.findAll();
	}
	
	@Transactional
	public Proveedor add(Proveedor proveedor) {
		return proveedorDao.save(proveedor);
	}
	
	@Transactional(readOnly=true)
	public Proveedor findById(Integer id) {
		return (Proveedor) proveedorDao.findById(id).orElse(null);
	}

	@Transactional
	public Proveedor update(Proveedor proveedor) {
		return proveedorDao.save(proveedor);
	}
	
	@Transactional
	public void delete(Integer id) {
		proveedorDao.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	public List<Proveedor> busquedaProveedores (String termino){
		return proveedorDao.busquedaProveedores(termino);
	}

	
	/********************Reporte*************************************/
	public String exportReport() throws FileNotFoundException, JRException {
        
        List<ProveedorReporte> listItems = (List<ProveedorReporte>)proveedorDao.getAllActivosReporte();

        String outputFile = "reportes/proveedores/" + "proveedores.pdf";
        
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItems);

        /* Map to hold Jasper report Parameters */
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("CollectionBeanParam", itemsJRBean);
        
        //read jrxml file and creating jasperdesign object
        InputStream input = new FileInputStream(new File("reportes/proveedores/proveedores.jrxml"));
                            
        JasperDesign jasperDesign = JRXmlLoader.load(input);
        
        /*compiling jrxml with help of JasperReport class*/
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        /* Using jasperReport object to generate PDF */
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        
        /* outputStream to create PDF */
        OutputStream outputStream = new FileOutputStream(new File(outputFile));
        
        /* Write content to PDF file */
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        
        JasperExportManager.exportReportToHtmlFile(jasperPrint, "reportes/proveedores/proveedores.html");
        
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
	
}
