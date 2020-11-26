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
    @Column(name = "detalle_id")
    private Integer detalleId;
    /**/
    @NotNull
    @Column(name = "ventas_id")
    private int ventasId;
    /**/
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    /**/
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto")
    private int monto;
    /**/
    @Basic(optional = false)
    @NotNull
    @Column(name = "subtotal")
    private int subtotal;
    /**/
    @Basic(optional = false)
    @NotNull
    @Column(name = "descuento")
    private int descuento;
    /**/
    @JoinColumn(name = "producto_id", referencedColumnName = "producto_id")
    @ManyToOne(optional = false)
    private Productos productoId;
    /**/
    @JoinColumn(name = "servicio_id", referencedColumnName = "servicio_id")
    @ManyToOne(optional = false)
    private Servicios servicioId;
    /**/
    @JsonBackReference(value="detalle-compras")
    @JoinColumn(name = "compras_id", referencedColumnName = "compras_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ventas ventas;
    /*
    @JoinColumn(name = "ventas_id", referencedColumnName = "ventas_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Ventas ventas;
*/
    public VentasDetalle() {
    }

    public VentasDetalle(Integer ventasId) {
        this.ventasId = ventasId;
    }


    public VentasDetalle(Integer detalleId, @NotNull int ventasId, @NotNull int cantidad, @NotNull int monto,
			@NotNull int subtotal, @NotNull int descuento, Productos productoId, Servicios servicioId) {
		super();
		this.detalleId = detalleId;
		this.ventasId = ventasId;
		this.cantidad = cantidad;
		this.monto = monto;
		this.subtotal = subtotal;
		this.descuento = descuento;
		this.productoId = productoId;
		this.servicioId = servicioId;
	}

	public Integer getDetalleId() {
		return detalleId;
	}

	public void setDetalleId(Integer detalleId) {
		this.detalleId = detalleId;
	}


    public int getVentasId() {
		return ventasId;
	}

	public void setVentasId(int ventasId) {
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


    public Ventas getVentas() {
		return ventas;
	}

	public void setVentas(Ventas ventas) {
		this.ventas = ventas;
	}

	@Override
    public String toString() {
        return "com.spa.VentasDetalle[ ventasId=" + ventasId + " ]";
    }
    
}
