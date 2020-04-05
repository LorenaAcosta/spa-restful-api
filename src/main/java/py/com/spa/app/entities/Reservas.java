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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "reservas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservas.findAll", query = "SELECT r FROM Reservas r"),
    @NamedQuery(name = "Reservas.findByReservaId", query = "SELECT r FROM Reservas r WHERE r.reservaId = :reservaId"),
    @NamedQuery(name = "Reservas.findByFecha", query = "SELECT r FROM Reservas r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "Reservas.findByEstado", query = "SELECT r FROM Reservas r WHERE r.estado = :estado")})
public class Reservas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reserva_id")
    private Integer reservaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "fecha")
    private String fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id")
    @ManyToOne(optional = false)
    private Clientes clienteId;
    @JoinColumn(name = "cedula", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Empleados cedula;
    @JoinColumn(name = "medio_pago_id", referencedColumnName = "medio_pago_id")
    @ManyToOne(optional = false)
    private MediosPago medioPagoId;
    @JoinColumn(name = "servicio_id", referencedColumnName = "servicio_id")
    @ManyToOne(optional = false)
    private Servicios servicioId;

    public Reservas() {
    }

    public Reservas(Integer reservaId) {
        this.reservaId = reservaId;
    }

    public Reservas(Integer reservaId, String fecha, String estado) {
        this.reservaId = reservaId;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Integer getReservaId() {
        return reservaId;
    }

    public void setReservaId(Integer reservaId) {
        this.reservaId = reservaId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Clientes getClienteId() {
        return clienteId;
    }

    public void setClienteId(Clientes clienteId) {
        this.clienteId = clienteId;
    }

    public Empleados getCedula() {
        return cedula;
    }

    public void setCedula(Empleados cedula) {
        this.cedula = cedula;
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
        hash += (reservaId != null ? reservaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservas)) {
            return false;
        }
        Reservas other = (Reservas) object;
        if ((this.reservaId == null && other.reservaId != null) || (this.reservaId != null && !this.reservaId.equals(other.reservaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spa.app.py.Reservas[ reservaId=" + reservaId + " ]";
    }
    
}
