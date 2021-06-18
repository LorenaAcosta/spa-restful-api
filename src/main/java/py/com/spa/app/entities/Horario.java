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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import py.com.spa.result.SqlTimeDeserializer;
/**
 *
 * @author Lore
 */
@Entity
@Table(name = "horario" , uniqueConstraints=@UniqueConstraint(columnNames={"dia_trabajo", "empleado_id"}))
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h"),
    @NamedQuery(name = "Horario.findByHorarioId", query = "SELECT h FROM Horario h WHERE h.horarioId = :horarioId"),
    @NamedQuery(name = "Horario.findByHoraInicio", query = "SELECT h FROM Horario h WHERE h.horaInicio = :horaInicio"),
    @NamedQuery(name = "Horario.findByHoraFin", query = "SELECT h FROM Horario h WHERE h.horaFin = :horaFin")})
public class Horario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "horario_id")
    private Integer horarioId;
    
    @Basic(optional = false)
    @Column(name = "dia_trabajo")
    private String diaTrabajo; 
    
    @Basic(optional = false)
    @JsonFormat(pattern="HH:mm:ss")
    @JsonDeserialize(using = SqlTimeDeserializer.class)
    @Column(name = "hora_inicio")
    private Time horaInicio;
    
    @Basic(optional = false)
    @JsonFormat(pattern="HH:mm:ss")
    @JsonDeserialize(using = SqlTimeDeserializer.class)
    @Column(name = "hora_fin")
    private Time horaFin; 
    
    @Basic(optional = false)
    @JsonFormat(pattern="HH:mm:ss")
    @JsonDeserialize(using = SqlTimeDeserializer.class)
    @Column(name = "hora_inicio_descanso")
    private Time horaInicioDescanso; 
    
    @Basic(optional = false)
    @JsonFormat(pattern="HH:mm:ss")
    @JsonDeserialize(using = SqlTimeDeserializer.class)
    @Column(name = "hora_fin_descanso")
    private Time horaFinDescanso; 
    
   
    @JoinColumn(name = "empleado_id", referencedColumnName = "empleado_id")
    @ManyToOne(optional = false)
    private Empleados empleadoId;

    
    
    public Horario() {
    }

    public Horario(Integer horarioId) {
        this.horarioId = horarioId;
    }



	public Horario(Empleados empleadoId) {
		super();
		this.empleadoId = empleadoId;
	}

	public Horario(Integer horarioId, String diaTrabajo, Time horaInicio, Time horaFin, Time horaInicioDescanso,
			Time horaFinDescanso) {
		super();
		this.horarioId = horarioId;
		this.diaTrabajo = diaTrabajo;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.horaInicioDescanso = horaInicioDescanso;
		this.horaFinDescanso = horaFinDescanso;
	}

	public Integer getHorarioId() {
		return horarioId;
	}

	public void setHorarioId(Integer horarioId) {
		this.horarioId = horarioId;
	}

	public String getDiaTrabajo() {
		return diaTrabajo;
	}

	public void setDiaTrabajo(String diaTrabajo) {
		this.diaTrabajo = diaTrabajo;
	}

	public Time getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Time getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Time horaFin) {
		this.horaFin = horaFin;
	}

	public Time getHoraInicioDescanso() {
		return horaInicioDescanso;
	}

	public void setHoraInicioDescanso(Time horaInicioDescanso) {
		this.horaInicioDescanso = horaInicioDescanso;
	}

	public Time getHoraFinDescanso() {
		return horaFinDescanso;
	}

	public void setHoraFinDescanso(Time horaFinDescanso) {
		this.horaFinDescanso = horaFinDescanso;
	}

	public Empleados getEmpleadoId() {
		return empleadoId;
	}

	public void setEmpleadoId(Empleados empleadoId) {
		this.empleadoId = empleadoId;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (horarioId != null ? horarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horario)) {
            return false;
        }
        Horario other = (Horario) object;
        if ((this.horarioId == null && other.horarioId != null) || (this.horarioId != null && !this.horarioId.equals(other.horarioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spa.Horario[ horarioId=" + horarioId + " ]";
    }
    
}