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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lore
 */
@Entity
@Table(name = "pagos_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PagosDetalle.findAll", query = "SELECT p FROM PagosDetalle p"),
    @NamedQuery(name = "PagosDetalle.findByPagoDetalleId", query = "SELECT p FROM PagosDetalle p WHERE p.pagoDetalleId = :pagoDetalleId"),
    @NamedQuery(name = "PagosDetalle.findByMonto", query = "SELECT p FROM PagosDetalle p WHERE p.monto = :monto"),
    @NamedQuery(name = "PagosDetalle.findByCantidad", query = "SELECT p FROM PagosDetalle p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "PagosDetalle.findBySubtotal", query = "SELECT p FROM PagosDetalle p WHERE p.subtotal = :subtotal")})
public class PagosDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pago_detalle_id")
    private Integer pagoDetalleId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto")
    private int monto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "subtotal")
    private int subtotal;
    @JoinColumn(name = "pago_id", referencedColumnName = "pago_id")
    @ManyToOne(optional = false)
    private Pagos pagoId;
    @JoinColumn(name = "producto_id", referencedColumnName = "producto_id")
    @ManyToOne(optional = false)
    private Productos productoId;
    @JoinColumn(name = "servicio_id", referencedColumnName = "servicio_id")
    @ManyToOne(optional = false)
    private Servicios servicioId;

    public PagosDetalle() {
    }

    public PagosDetalle(Integer pagoDetalleId) {
        this.pagoDetalleId = pagoDetalleId;
    }

    public PagosDetalle(Integer pagoDetalleId, int monto, int cantidad, int subtotal) {
        this.pagoDetalleId = pagoDetalleId;
        this.monto = monto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public Integer getPagoDetalleId() {
        return pagoDetalleId;
    }

    public void setPagoDetalleId(Integer pagoDetalleId) {
        this.pagoDetalleId = pagoDetalleId;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public Pagos getPagoId() {
        return pagoId;
    }

    public void setPagoId(Pagos pagoId) {
        this.pagoId = pagoId;
    }

    public Productos getProductoId() {
        return productoId;
    }

    public void setProductoId(Productos productoId) {
        this.productoId = productoId;
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
        hash += (pagoDetalleId != null ? pagoDetalleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagosDetalle)) {
            return false;
        }
        PagosDetalle other = (PagosDetalle) object;
        if ((this.pagoDetalleId == null && other.pagoDetalleId != null) || (this.pagoDetalleId != null && !this.pagoDetalleId.equals(other.pagoDetalleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.spa.app.entities.PagosDetalle[ pagoDetalleId=" + pagoDetalleId + " ]";
    }
    
}
