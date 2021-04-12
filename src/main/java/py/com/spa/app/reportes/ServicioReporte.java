package py.com.spa.app.reportes;

import java.sql.Time;

public interface ServicioReporte {
	
	Integer getItem();
	String getNombre();
	String getDescripcion();
	String getCategoria();
	Time getDuracion();
	Integer getCosto();
	String getImpuesto();
	String getImagen();
	String getEstado();
}
