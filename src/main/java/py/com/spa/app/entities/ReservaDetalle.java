/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.spa.app.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "reserva_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReservaDetalle.findAll", query = "SELECT r FROM ReservaDetalle r"),
    @NamedQuery(name = "ReservaDetalle.findByReservaId", query = "SELECT r FROM ReservaDetalle r WHERE r.reservaId = :reservaId")})
public class ReservaDetalle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "reserva_id")
    private String reservaId;

    @JoinColumn(name = "reserva_id", referencedColumnName = "reserva_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Reserva reserva;

    @JoinColumn(name = "servicio_id", referencedColumnName = "servicio_id")
    @ManyToOne(optional = false)
    private Servicios servicioId;
    
    @JoinColumn(name = "empleado_id", referencedColumnName = "empleado_id")
    @ManyToOne(optional = false)
    private Empleados empleadoId;

    public ReservaDetalle() {
    }

    public ReservaDetalle(String reservaId) {
        this.reservaId = reservaId;
    }

    public Empleados getEmpleadoId() {
		return empleadoId;
	}

	public void setEmpleadoId(Empleados empleadoId) {
		this.empleadoId = empleadoId;
	}

	public String getReservaId() {
        return reservaId;
    }

    public void setReservaId(String reservaId) {
        this.reservaId = reservaId;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Servicios getServicioId() {
        return servicioId;
    }

    public void setServicioId(Servicios servicioId) {
        this.servicioId = servicioId;
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
        return "entities.ReservaDetalle[ reservaId=" + reservaId + " ]";
    }

}
