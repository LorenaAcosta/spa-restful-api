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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NamedQuery(name = "ReservaDetalle.findByReservaDetalleId", query = "SELECT r FROM ReservaDetalle r WHERE r.reservaDetalleId = :reservaDetalleId"),
    @NamedQuery(name = "ReservaDetalle.findByFecha", query = "SELECT r FROM ReservaDetalle r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "ReservaDetalle.findByHoraInicio", query = "SELECT r FROM ReservaDetalle r WHERE r.horaInicio = :horaInicio"),
    @NamedQuery(name = "ReservaDetalle.findByHoraFin", query = "SELECT r FROM ReservaDetalle r WHERE r.horaFin = :horaFin"),
    @NamedQuery(name = "ReservaDetalle.findByEstadoItem", query = "SELECT r FROM ReservaDetalle r WHERE r.estadoItem = :estadoItem")})
public class ReservaDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "reserva_detalle_id")
    private String reservaDetalleId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "fecha")
    private String fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "hora_inicio")
    private String horaInicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "hora_fin")
    private String horaFin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "estado_item")
    private String estadoItem;
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id")
    @ManyToOne(optional = false)
    private Clientes clienteId;
    @JoinColumn(name = "medio_pago_id", referencedColumnName = "medio_pago_id")
    @ManyToOne(optional = false)
    private MediosPago medioPagoId;
    @JoinColumn(name = "servicio_id", referencedColumnName = "servicio_id")
    @ManyToOne(optional = false)
    private Servicios servicioId;

    public ReservaDetalle() {
    }

    public ReservaDetalle(String reservaDetalleId) {
        this.reservaDetalleId = reservaDetalleId;
    }

    public ReservaDetalle(String reservaDetalleId, String fecha, String horaInicio, String horaFin, String estadoItem) {
        this.reservaDetalleId = reservaDetalleId;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estadoItem = estadoItem;
    }

    public String getReservaDetalleId() {
        return reservaDetalleId;
    }

    public void setReservaDetalleId(String reservaDetalleId) {
        this.reservaDetalleId = reservaDetalleId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getEstadoItem() {
        return estadoItem;
    }

    public void setEstadoItem(String estadoItem) {
        this.estadoItem = estadoItem;
    }

    public Clientes getClienteId() {
        return clienteId;
    }

    public void setClienteId(Clientes clienteId) {
        this.clienteId = clienteId;
    }

    public MediosPago getMedioPagoId() {
        return medioPagoId;
    }

    public void setMedioPagoId(MediosPago medioPagoId) {
        this.medioPagoId = medioPagoId;
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
        hash += (reservaDetalleId != null ? reservaDetalleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservaDetalle)) {
            return false;
        }
        ReservaDetalle other = (ReservaDetalle) object;
        if ((this.reservaDetalleId == null && other.reservaDetalleId != null) || (this.reservaDetalleId != null && !this.reservaDetalleId.equals(other.reservaDetalleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.spa.app.entities.ReservaDetalle[ reservaDetalleId=" + reservaDetalleId + " ]";
    }
    
}
