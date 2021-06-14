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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.enumeraciones.TipoCategoria;
import py.com.spa.app.reportes.CategoriaReporte;
import py.com.spa.app.reportes.Datos;

@Service
public class CategoriaService {
	
	@Autowired
	private ICategoriaDao categoriaDao;
	
	private final Path rootReportes = Paths.get("reportes/categorias");
	
	@Transactional(readOnly=true)
	public List<Categorias> findAll(){
		return (List<Categorias>) categoriaDao.findAll();
	}
	
	//listar con paginado
	@Transactional(readOnly=true)
	public Page<Categorias> findAll(Pageable pageable){
		return (Page<Categorias>) categoriaDao.findAll(pageable);
	}
	
	@Transactional(readOnly=true)
	public List<String> findAllByDescripcion(){
		return (List<String>) categoriaDao.findAllByDescripcion(); 
	}
	
	@Transactional
	public Categorias addCategoria(Categorias categoria) {
		return categoriaDao.save(categoria);
	}
	
	@Transactional(readOnly=true)
	public Categorias findByCategoriaId(Integer id) {
		return (Categorias) categoriaDao.findById(id).orElse(null);
	}

	@Transactional
	public Categorias updateCategoria(Categorias categoria) {
		return categoriaDao.save(categoria);
	}
	
	@Transactional
	public void deleteCategoria(Integer id) {
		categoriaDao.deleteById(id);
	}
	
	@Transactional
	public List<Categorias> obtenerCategorias(String id) {
		return (List<Categorias>) categoriaDao.findByDataType(TipoCategoria.valueOf(id.toUpperCase()));
	}
	
	public String exportReport() throws FileNotFoundException, JRException {
		
		
        
        List<CategoriaReporte> listItems = (List<CategoriaReporte>)categoriaDao.getAllReporte();

  
        String outputFile = "reportes/categorias/" + "categorias.pdf";
        
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItems);

        /* Map to hold Jasper report Parameters */
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("CollectionBeanParamCategoria", itemsJRBean);
        
        //read jrxml file and creating jasperdesign object
        InputStream input = new FileInputStream(new File("reportes/categorias/categorias.jrxml"));
                            
        JasperDesign jasperDesign = JRXmlLoader.load(input);
        
        /*compiling jrxml with help of JasperReport class*/
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        /* Using jasperReport object to generate PDF */
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        
        /* outputStream to create PDF */
        OutputStream outputStream = new FileOutputStream(new File(outputFile));
        
        /* Write content to PDF file */
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        
        JasperExportManager.exportReportToHtmlFile(jasperPrint, "reportes/categorias/categorias.html");
        

        System.out.println("File Generated");	
        
        //load("factura.pdf");
        
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
    
	@Transactional(readOnly=true)
	public List<Categorias> busquedaCategorias (String termino){
		return categoriaDao.busquedaCategorias(termino);
	}
	
	@Transactional(readOnly=true)
	public List<Categorias> findByDataTypee (String dataType){
		return categoriaDao.findByDataTypee(TipoCategoria.valueOf(dataType.toUpperCase()));
	}

	@Transactional(readOnly=true)
	public List<Datos> getdatos() {
		return categoriaDao.getdatos();
	}



}







