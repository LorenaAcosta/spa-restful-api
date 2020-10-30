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
    @NamedQuery(name = "ReservaDetalle.findByEmpleadoId", query = "SELECT r FROM ReservaDetalle r WHERE r.empleadoId = :empleadoId"),
    @NamedQuery(name = "ReservaDetalle.findByFechaReserva", query = "SELECT r FROM ReservaDetalle r WHERE r.fechaReserva = :fechaReserva"),
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
    @Column(name = "empleado_id")
    private int empleadoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_reserva")
    @Temporal(TemporalType.DATE)
    private Date fechaReserva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hora;
    @JoinColumn(name = "disponible_id", referencedColumnName = "disponible_id")
    @ManyToOne(optional = false)
    private EmpleadoDisponible disponibleId;
    @JoinColumn(name = "reserva_id", referencedColumnName = "reserva_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Reserva reserva;

    public ReservaDetalle() {
    }

    public ReservaDetalle(Integer reservaId) {
        this.reservaId = reservaId;
    }

    public ReservaDetalle(Integer reservaId, int empleadoId, Date fechaReserva, Date hora) {
        this.reservaId = reservaId;
        this.empleadoId = empleadoId;
        this.fechaReserva = fechaReserva;
        this.hora = hora;
    }

    public Integer getReservaId() {
        return reservaId;
    }

    public void setReservaId(Integer reservaId) {
        this.reservaId = reservaId;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public EmpleadoDisponible getDisponibleId() {
        return disponibleId;
    }

    public void setDisponibleId(EmpleadoDisponible disponibleId) {
        this.disponibleId = disponibleId;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
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
        return "com.spa.ReservaDetalle[ reservaId=" + reservaId + " ]";
    }
    
}
