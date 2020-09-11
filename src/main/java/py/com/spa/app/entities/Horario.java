/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.spa.app.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "horario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h"),
    @NamedQuery(name = "Horario.findByHorarioId", query = "SELECT h FROM Horario h WHERE h.horarioId = :horarioId"),
    @NamedQuery(name = "Horario.findByHoraInicio", query = "SELECT h FROM Horario h WHERE h.horaInicio = :horaInicio"),
    @NamedQuery(name = "Horario.findByHoraFin", query = "SELECT h FROM Horario h WHERE h.horaFin = :horaFin")})
public class Horario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "horario_id")
    private Integer horarioId;

    @Basic(optional = false)
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;

    @Basic(optional = false)
    @Column(name = "hora_fin")
    @Temporal(TemporalType.TIME)
    private Date horaFin;
    
    @JoinColumn(name = "empleado_id", referencedColumnName = "empleado_id")
    @ManyToOne(optional = false)
    private Empleados empleadoId;

    public Horario() {
    }

    public Horario(Integer horarioId) {
        this.horarioId = horarioId;
    }

    public Horario(Integer horarioId, Date horaInicio, Date horaFin) {
        this.horarioId = horarioId;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public Integer getHorarioId() {
        return horarioId;
    }

    public Empleados getEmpleadoId() {
		return empleadoId;
	}

	public void setEmpleadoId(Empleados empleadoId) {
		this.empleadoId = empleadoId;
	}

	public void setHorarioId(Integer horarioId) {
        this.horarioId = horarioId;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
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
        return "entities.Horario[ horarioId=" + horarioId + " ]";
    }

}
