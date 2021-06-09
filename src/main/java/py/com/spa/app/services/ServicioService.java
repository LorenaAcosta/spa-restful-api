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
import py.com.spa.app.dao.ICategoriaDao;
import py.com.spa.app.dao.IServicioDao;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Productos;
import py.com.spa.app.entities.Servicios;

import py.com.spa.app.enumeraciones.EstadoServicio;
import py.com.spa.app.reportes.ServicioReporte;


@Service
public class ServicioService {
	
	@Autowired
	private IServicioDao servicioDao;
	
	@Autowired
	private ICategoriaDao categoriaDao;


	private final Path rootReportes = Paths.get("reportes/servicios");

	@Transactional(readOnly=true)
	public List<Servicios> findAll(){
		return (List<Servicios>) servicioDao.findAll(); 
	}
	
	@Transactional(readOnly=true)
	public List<Servicios> findActivos(){
		return (List<Servicios>) servicioDao.findActivos(); 
	}
	
	@Transactional
	public Servicios busquedaPorNombre(String nombre) {
		return servicioDao.busquedaPorNombre(nombre);
	}
	
	
	@Transactional(readOnly=true)
	public Servicios findServicioById(Integer id){
		return (Servicios) servicioDao.findById(id).orElse(null);
	}
	
	@Transactional(readOnly=true)
	public Categorias findByCategoriaId(Integer id) {
		return (Categorias) categoriaDao.findById(id).orElse(null);
	}


	@Transactional
	public void deleteServicio(Integer id) {
		servicioDao.deleteById(id);
	}
	
	@Transactional
	public Servicios updateServicio(Servicios p) {
		return servicioDao.save(p);
	} 

	@Transactional
	public Servicios guardarServicio(Servicios p) {
		return servicioDao.save(p);
	} 
	
	public List<Servicios> findAllByCategoriaId(Categorias categoria) {
		return (List<Servicios>) servicioDao.findAllByCategoriaId(categoria);
	}

	public List<Servicios> getServiciosByEstado(String estado) {
		return (List<Servicios>) servicioDao.getServiciosByEstado(EstadoServicio.valueOf(estado.toUpperCase()));
		//return (List<Categorias>) categoriaDao.findByDataType(TipoCategoria.valueOf(id.toUpperCase()));
	}


	public List<Servicios> getServiciosActivos(Categorias categoriaId){
		//EstadoServicio estado1= EstadoServicio.valueOf(estado.toUpperCase());
		return (List<Servicios>) servicioDao.getServiciosActivos(categoriaId.getCategoriaId());
		
	}


	public List<Servicios> getServiciosDisponibles(Integer empleadoId) {
		return servicioDao.getBoxesDisponibles(empleadoId);
	
	}
	
	   

	public List<Servicios> busquedaServicios (String termino){
		return servicioDao.busquedaServicios(termino);
	}
	
	
	
	/*Reporte*/
	public String exportReport() throws FileNotFoundException, JRException {
		
		
        
        List<ServicioReporte> listItems = (List<ServicioReporte>)servicioDao.getAllActivosReporte();

  
        String outputFile = "reportes/servicios/" + "servicios.pdf";
        
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItems);

        /* Map to hold Jasper report Parameters */
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("CollectionBeanParam", itemsJRBean);
        
        //read jrxml file and creating jasperdesign object
        InputStream input = new FileInputStream(new File("reportes/servicios/servicios.jrxml"));
                            
        JasperDesign jasperDesign = JRXmlLoader.load(input);
        
        /*compiling jrxml with help of JasperReport class*/
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        /* Using jasperReport object to generate PDF */
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        
        /* outputStream to create PDF */
        OutputStream outputStream = new FileOutputStream(new File(outputFile));
        
        /* Write content to PDF file */
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        
        JasperExportManager.exportReportToHtmlFile(jasperPrint, "reportes/servicios/servicios.html");

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



}
