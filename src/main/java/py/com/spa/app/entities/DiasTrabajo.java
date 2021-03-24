package py.com.spa.app.entities;

import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import py.com.spa.result.SqlTimeDeserializer;
/**
 *
 * @author Lore
 */
@Entity
@Table(name = "dias_trabajo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiasTrabajo.findAll", query = "SELECT h FROM Horario h"),
    @NamedQuery(name = "DiasTrabajo.findByHorarioId", query = "SELECT h FROM Horario h WHERE h.horarioId = :horarioId"),
    @NamedQuery(name = "DiasTrabajo.findByHoraInicio", query = "SELECT h FROM Horario h WHERE h.horaInicio = :horaInicio"),
    @NamedQuery(name = "DiasTrabajo.findByHoraFin", query = "SELECT h FROM Horario h WHERE h.horaFin = :horaFin")})
public class DiasTrabajo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dias_trabajo_id")
    private Integer diasTrabajoId;
    @Basic(optional = false)
    @Column(name = "lunes")
    private String lunes;
    @Basic(optional = false)
    @Column(name = "martes")
    private String martes;
    @Basic(optional = false)
    @Column(name = "miercoles")
    private String miercoles;
    @Basic(optional = false)
    @Column(name = "jueves")
    private String jueves;
    @Basic(optional = false)
    @Column(name = "viernes")
    private String viernes;
    @Basic(optional = false)
    @Column(name = "sabado")
    private String sabado;
    @Basic(optional = false)
    @Column(name = "domingo")
    private String domingo;
    @JoinColumn(name = "empleado_id", referencedColumnName = "empleado_id")
    @ManyToOne(optional = false)
    private Empleados empleadoId;
    
    
    public DiasTrabajo() {
    }


	public DiasTrabajo(Integer diasTrabajoId, String lunes, String martes, String miercoles, String jueves,
			String viernes, String sabado, String domingo, Empleados empleadoId) {
		super();
		this.diasTrabajoId = diasTrabajoId;
		this.lunes = lunes;
		this.martes = martes;
		this.miercoles = miercoles;
		this.jueves = jueves;
		this.viernes = viernes;
		this.sabado = sabado;
		this.domingo = domingo;
		this.empleadoId = empleadoId;
	}


	public Integer getDiasTrabajoId() {
		return diasTrabajoId;
	}


	public void setDiasTrabajoId(Integer diasTrabajoId) {
		this.diasTrabajoId = diasTrabajoId;
	}


	public String getLunes() {
		return lunes;
	}


	public void setLunes(String lunes) {
		this.lunes = lunes;
	}


	public String getMartes() {
		return martes;
	}


	public void setMartes(String martes) {
		this.martes = martes;
	}


	public String getMiercoles() {
		return miercoles;
	}


	public void setMiercoles(String miercoles) {
		this.miercoles = miercoles;
	}


	public String getJueves() {
		return jueves;
	}


	public void setJueves(String jueves) {
		this.jueves = jueves;
	}


	public String getViernes() {
		return viernes;
	}


	public void setViernes(String viernes) {
		this.viernes = viernes;
	}


	public String getSabado() {
		return sabado;
	}


	public void setSabado(String sabado) {
		this.sabado = sabado;
	}


	public String getDomingo() {
		return domingo;
	}


	public void setDomingo(String domingo) {
		this.domingo = domingo;
	}


	public Empleados getEmpleadoId() {
		return empleadoId;
	}


	public void setEmpleadoId(Empleados empleadoId) {
		this.empleadoId = empleadoId;
	}


	@Override
	public String toString() {
		return "DiasTrabajo [diasTrabajoId=" + diasTrabajoId + ", lunes=" + lunes + ", martes=" + martes
				+ ", miercoles=" + miercoles + ", jueves=" + jueves + ", viernes=" + viernes + ", sabado=" + sabado
				+ ", domingo=" + domingo + ", empleadoId=" + empleadoId + "]";
	}
    
    
    
    

}