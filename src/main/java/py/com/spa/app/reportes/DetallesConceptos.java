package py.com.spa.app.reportes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public interface DetallesConceptos {
	
	  Integer getdebe();
	  Integer gethaber();
	  String getdescripcion();
	 
	 

}
