/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.spa.app.entities;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import py.com.spa.result.SqlTimeDeserializer;

/**
 *
 * @author Lore
 */
@Entity
@Table(name = "reserva_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReservaDetalle.findAll", query = "SELECT r FROM ReservaDetalle r"),
    @NamedQuery(name = "ReservaDetalle.findByReservaId", query = "SELECT r FROM ReservaDetalle r WHERE r.reservaId = :reservaId"),
    @NamedQuery(name = "ReservaDetalle.findByEmpleado", query = "SELECT r FROM ReservaDetalle r WHERE r.empleado = :empleado"),
    @NamedQuery(name = "ReservaDetalle.findByFechaReserva", query = "SELECT r FROM ReservaDetalle r WHERE r.fechaReserva = :fechaReserva"),
    @NamedQuery(name = "ReservaDetalle.findByDisponibleId", query = "SELECT r FROM ReservaDetalle r WHERE r.disponibleId = :disponibleId"),
    @NamedQuery(name = "ReservaDetalle.findByHora", query = "SELECT r FROM ReservaDetalle r WHERE r.hora = :hora")})
public class ReservaDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reserva_id")
    private Integer reservaId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "empleado")
    private Integer empleado;
    
    @Basic(optional = false)
    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="America/Asuncion")
    @Column(name = "fecha_reserva")
   // @Temporal(TemporalType.DATE)
    private Date fechaReserva;
    
    @Basic(optional = false)
    @NotNull
    @JsonFormat(pattern="HH:mm")
    @JsonDeserialize(using = SqlTimeDeserializer.class)
    @Column(name = "hora")
    private Time hora;
    
    @JoinColumn(name = "disponible_id", referencedColumnName = "disponible_id")
    @ManyToOne(optional = false)
    private Disponible disponibleId;
    
    
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    @ManyToOne(optional = false)
    private Usuario usuarioId;

    public ReservaDetalle() {
    }

    public ReservaDetalle(Integer reservaId) {
        this.reservaId = reservaId;
    }

    public ReservaDetalle(Integer reservaId, Integer empleado, Date fechaReserva, Time hora, Disponible disponibleId,
    		Usuario usuarioId) {
        this.reservaId = reservaId;
        this.empleado = empleado;
        this.fechaReserva = fechaReserva;
        this.hora = hora;
        this.disponibleId= disponibleId;
        this.usuarioId =  usuarioId;
    }


	public Integer getReservaId() {
        return reservaId;
    }

    public void setReservaId(Integer reservaId) {
        this.reservaId = reservaId;
    }

    public int getEmpleado() {
        return empleado;
    }

    public void setEmpleado(int empleado) {
        this.empleado = empleado;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Disponible getDisponibleId() {
        return disponibleId;
    }

    public void setDisponibleId(Disponible disponibleId) {
        this.disponibleId = disponibleId;
    }


    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservaId != null ? reservaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservaDetalle)) {
            return false;
        }
        ReservaDetalle other = (ReservaDetalle) object;
        if ((this.reservaId == null && other.reservaId != null) || (this.reservaId != null && !this.reservaId.equals(other.reservaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.spa.app.ReservaDetalle[ reservaId=" + reservaId + " ]";
    }
    
}
