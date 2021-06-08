package py.com.spa.result;

import java.util.List;

import py.com.spa.app.entities.Disponible;
import py.com.spa.app.entities.Horario;

public class DisponibleDatosResult { 
	
	public Disponible disponible;
	public List<Horario> horario;
	
	public DisponibleDatosResult() {
		
	}

	public Disponible getDisponible() {
		return disponible;
	}
	public void setDisponible(Disponible disponible) {
		this.disponible = disponible;
	}

	public List<Horario> getHorario() {
		return horario;
	}

	public void setHorario(List<Horario> horario) {
		this.horario = horario;
	}


}
