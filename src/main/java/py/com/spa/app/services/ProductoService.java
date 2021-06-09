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
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
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
import py.com.spa.app.dao.IProductoDao;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Productos;
import py.com.spa.app.enumeraciones.EstadoProducto;
import py.com.spa.app.reportes.ProductoReporte;
import py.com.spa.params.PaginadoParam;
import py.com.spa.result.PaginadoResult;

@Service
public class ProductoService {
	
	private final Path rootReportes = Paths.get("reportes/productos");
	
	@Autowired
	private IProductoDao productoDao;
	@Autowired
	private ICategoriaDao categoriaDao;
	

	@Transactional(readOnly=true)
	public List<Productos> findAll(){
		return (List<Productos>) productoDao.findAll(); 
	}
	

	@Transactional(readOnly=true)
	public List<Productos> findByEstado(){
		 EstadoProducto e = null;
		return (List<Productos>) productoDao.findActivos(); 
	}
	
	@Transactional
	public Productos addProducto(Productos producto) {
		return productoDao.save(producto);
	}
	
	@Transactional
	public Productos busquedaPorNombre(String nombre) {
		return productoDao.busquedaPorNombre(nombre);
	}
	
	
	@Transactional(readOnly=true)
	public Categorias findByCategoriaId(Integer id) {
		return (Categorias) categoriaDao.findById(id).orElse(null);
	}


	@Transactional(readOnly=true)
	public Productos findProductoById(Integer id){
		return (Productos) productoDao.findById(id).orElse(null);
	}
	
	@Transactional
	public Productos updateProducto(Productos producto) {
		return productoDao.save(producto);
	} 
	
	@Transactional
	public void deleteProducto(Integer id) {
		productoDao.deleteById(id);
	}
	
	
	public List<Productos> findAllByCategoriaId(Categorias categoria) {
		return (List<Productos>) productoDao.findAllByCategoriaId(categoria);
	}
	
	  
	
	@Transactional(readOnly=true)
	public PaginadoResult<Productos> listar (PaginadoParam<Productos> param) {
		
		
		ExampleMatcher matcher = ExampleMatcher.matching()
	            .withStringMatcher(StringMatcher.CONTAINING);
	        Example<Productos> example = Example.of(param.getFiltros(),matcher);
	            Page<Productos> lista = productoDao.findAll(example,
	                PageRequest.of(
	                        param.getPagina(), 
	                        param.getCantidad(), 
	                        Sort.by(
	                                param.getOrderDir().equals("ASC")? Sort.Direction.ASC:Sort.Direction.DESC,
	                                param.getOrderBy())
	                        ));
	                PaginadoResult<Productos> result = new PaginadoResult<>(lista);
	                return result;
		
	}
	
    
	@Transactional(readOnly=true)
	public List<Productos> busquedaProductos (String termino){
		return productoDao.busquedaProductos(termino);
	}
	
	
	/*Reporte*/
	public String exportReport() throws FileNotFoundException, JRException {
		
		
        
        List<ProductoReporte> listItems = (List<ProductoReporte>)productoDao.getAllActivosReporte();

  
        String outputFile = "reportes/productos/" + "productos.pdf";
        
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItems);

        /* Map to hold Jasper report Parameters */
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("CollectionBeanParam", itemsJRBean);
        
        //read jrxml file and creating jasperdesign object
        InputStream input = new FileInputStream(new File("reportes/productos/productos.jrxml"));
                            
        JasperDesign jasperDesign = JRXmlLoader.load(input);
        
        /*compiling jrxml with help of JasperReport class*/
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        /* Using jasperReport object to generate PDF */
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        
        /* outputStream to create PDF */
        OutputStream outputStream = new FileOutputStream(new File(outputFile));
        
        /* Write content to PDF file */
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        
        JasperExportManager.exportReportToHtmlFile(jasperPrint, "reportes/productos/productos.html");

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
