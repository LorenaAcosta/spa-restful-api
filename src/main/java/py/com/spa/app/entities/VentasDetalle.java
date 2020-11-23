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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 *
 * @author Lore
 */
@Entity
@Table(name = "ventas_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentasDetalle.findAll", query = "SELECT v FROM VentasDetalle v"),
    @NamedQuery(name = "VentasDetalle.findByVentasId", query = "SELECT v FROM VentasDetalle v WHERE v.ventasId = :ventasId"),
    @NamedQuery(name = "VentasDetalle.findByMonto", query = "SELECT v FROM VentasDetalle v WHERE v.monto = :monto"),
    @NamedQuery(name = "VentasDetalle.findByCantidad", query = "SELECT v FROM VentasDetalle v WHERE v.cantidad = :cantidad"),
    @NamedQuery(name = "VentasDetalle.findBySubtotal", query = "SELECT v FROM VentasDetalle v WHERE v.subtotal = :subtotal"),
    @NamedQuery(name = "VentasDetalle.findByDescuento", query = "SELECT v FROM VentasDetalle v WHERE v.descuento = :descuento")})
public class VentasDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ventas_id")
    private Integer ventasId;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "descuento")
    private int descuento;
    @JoinColumn(name = "producto", referencedColumnName = "producto_id")
    @ManyToOne
    private Productos producto;
    @JoinColumn(name = "reserva", referencedColumnName = "reserva_id")
    @ManyToOne
    private ReservaDetalle reserva;
    @JoinColumn(name = "ventas_id", referencedColumnName = "ventas_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Ventas ventas;

    public VentasDetalle() {
    }

    public VentasDetalle(Integer ventasId) {
        this.ventasId = ventasId;
    }

    public VentasDetalle(Integer ventasId, int monto, int cantidad, int subtotal, int descuento) {
        this.ventasId = ventasId;
        this.monto = monto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.descuento = descuento;
    }

    public Integer getVentasId() {
        return ventasId;
    }

    public void setVentasId(Integer ventasId) {
        this.ventasId = ventasId;
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

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    @JsonBackReference(value="ventas")
    public ReservaDetalle getReserva() {
        return reserva;
    }

    public void setReserva(ReservaDetalle reserva) {
        this.reserva = reserva;
    }

    @JsonManagedReference(value="ventas")
    public Ventas getVentas() {
        return ventas;
    }

    public void setVentas(Ventas ventas) {
        this.ventas = ventas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ventasId != null ? ventasId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentasDetalle)) {
            return false;
        }
        VentasDetalle other = (VentasDetalle) object;
        if ((this.ventasId == null && other.ventasId != null) || (this.ventasId != null && !this.ventasId.equals(other.ventasId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.spa.app.entities.VentasDetalle[ ventasId=" + ventasId + " ]";
    }
    
}
