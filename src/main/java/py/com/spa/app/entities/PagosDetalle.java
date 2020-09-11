/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.spa.app.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "pagos_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PagosDetalle.findAll", query = "SELECT p FROM PagosDetalle p"),
    @NamedQuery(name = "PagosDetalle.findByPagoDetalleId", query = "SELECT p FROM PagosDetalle p WHERE p.pagosDetallePK.pagoDetalleId = :pagoDetalleId"),
    @NamedQuery(name = "PagosDetalle.findByPagoId", query = "SELECT p FROM PagosDetalle p WHERE p.pagosDetallePK.pagoId = :pagoId"),
    @NamedQuery(name = "PagosDetalle.findByMonto", query = "SELECT p FROM PagosDetalle p WHERE p.monto = :monto"),
    @NamedQuery(name = "PagosDetalle.findByCantidad", query = "SELECT p FROM PagosDetalle p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "PagosDetalle.findBySubtotal", query = "SELECT p FROM PagosDetalle p WHERE p.subtotal = :subtotal")})
public class PagosDetalle implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected PagosDetallePK pagosDetallePK;

    @Basic(optional = false)
    @Column(name = "monto")
    private int monto;

    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;

    @Basic(optional = false)
    @Column(name = "subtotal")
    private int subtotal;

    @JoinColumn(name = "pago_id", referencedColumnName = "pago_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pagos pagos;

    public PagosDetalle() {
    }

    public PagosDetalle(PagosDetallePK pagosDetallePK) {
        this.pagosDetallePK = pagosDetallePK;
    }

    public PagosDetalle(PagosDetallePK pagosDetallePK, int monto, int cantidad, int subtotal) {
        this.pagosDetallePK = pagosDetallePK;
        this.monto = monto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public PagosDetalle(int pagoDetalleId, int pagoId) {
        this.pagosDetallePK = new PagosDetallePK(pagoDetalleId, pagoId);
    }

    public PagosDetallePK getPagosDetallePK() {
        return pagosDetallePK;
    }

    public void setPagosDetallePK(PagosDetallePK pagosDetallePK) {
        this.pagosDetallePK = pagosDetallePK;
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

    public Pagos getPagos() {
        return pagos;
    }

    public void setPagos(Pagos pagos) {
        this.pagos = pagos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pagosDetallePK != null ? pagosDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagosDetalle)) {
            return false;
        }
        PagosDetalle other = (PagosDetalle) object;
        if ((this.pagosDetallePK == null && other.pagosDetallePK != null) || (this.pagosDetallePK != null && !this.pagosDetallePK.equals(other.pagosDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PagosDetalle[ pagosDetallePK=" + pagosDetallePK + " ]";
    }

}
