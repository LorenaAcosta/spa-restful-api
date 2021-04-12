package py.com.spa.app.reportes;

import java.sql.Time;

public interface ReservaReporte {
	
	Integer getItem();
	String getServicio();
	String getCliente();
	String getFecha();
	Time getHora();
	String getBox();
	String getTerapista();
	String getEstado();
}
