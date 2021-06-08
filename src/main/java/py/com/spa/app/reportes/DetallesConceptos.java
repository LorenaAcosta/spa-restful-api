package py.com.spa.app.reportes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public interface DetallesConceptos {
	
	 public Integer debe();
	 public Integer haber();
	 public String descripcion();
	 
	 

}
