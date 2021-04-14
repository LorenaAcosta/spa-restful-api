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
import py.com.spa.app.dao.IEmpleadoDao;
import py.com.spa.app.dao.daoImpl;
import py.com.spa.app.entities.Empleados;
import py.com.spa.app.entities.Turnos;
import py.com.spa.app.reportes.EmpleadoReporte;

@Service
public class EmpleadoService {
	@Autowired
	private IEmpleadoDao empleadoDao;
	
	
	private final Path rootReportes = Paths.get("reportes/empleados");
	
	@Transactional(readOnly=true)
	public List<Empleados> findAll(){
		return (List<Empleados>) empleadoDao.findAll(); 
	}
	
	@Transactional
	public Empleados saveEmpleado(Empleados empleado) {
		return empleadoDao.save(empleado);
	}

	@Transactional
	public Empleados updateEmpleado(Empleados empleado) {
		return empleadoDao.save(empleado);
	}
	
	@Transactional
	public void deleteEmpleado(Integer id) {
		empleadoDao.deleteById(id);
	}
	

	@Transactional(readOnly=true)	
	public Empleados findEmpleadoById(Integer id) {
		return (Empleados) empleadoDao.findById(id).orElse(null);
	}

	

	@Transactional(readOnly=true)	
	public Empleados findEmpleadoCedula(Integer cedula) {
		return (Empleados) empleadoDao.findByCedula(cedula);
	}

	
	
    public Turnos obtenerTurnos(Integer empleadoId) {
   
        Turnos response = new Turnos();

        try {
            response = daoImpl.obtenerTurnos(empleadoId);
        } catch (Exception e) {
        	
        }
        return response;
    }
    

	@Transactional(readOnly=true)
	public List<Empleados> busquedaEmpleados (String termino){
		return empleadoDao.busquedaEmpleados(termino);
	}

	
	/*Reporte*/
	public String exportReport() throws FileNotFoundException, JRException {
		
		
        
        List<EmpleadoReporte> listItems = (List<EmpleadoReporte>)empleadoDao.getEmpleadosReporte();

  
        String outputFile = "reportes/empleados/" + "empleados.pdf";
        
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItems);

        /* Map to hold Jasper report Parameters */
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("CollectionBeanParam", itemsJRBean);
        
        //read jrxml file and creating jasperdesign object
        InputStream input = new FileInputStream(new File("reportes/empleados/empleados.jrxml"));
                            
        JasperDesign jasperDesign = JRXmlLoader.load(input);
        
        /*compiling jrxml with help of JasperReport class*/
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        /* Using jasperReport object to generate PDF */
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        
        /* outputStream to create PDF */
        OutputStream outputStream = new FileOutputStream(new File(outputFile));
        
        /* Write content to PDF file */
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        
        JasperExportManager.exportReportToHtmlFile(jasperPrint, "reportes/empleados/empleados.html");
        
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
