/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.spa.app.entities;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 *
 * @author Lore
 */
@Entity
@Table(name = "compras_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComprasDetalle.findAll", query = "SELECT c FROM ComprasDetalle c"),
    @NamedQuery(name = "ComprasDetalle.findByDetalleId", query = "SELECT c FROM ComprasDetalle c WHERE c.detalleId = :detalleId"),
    @NamedQuery(name = "ComprasDetalle.findByCantidad", query = "SELECT c FROM ComprasDetalle c WHERE c.cantidad = :cantidad"),
    @NamedQuery(name = "ComprasDetalle.findByPrecioCompra", query = "SELECT c FROM ComprasDetalle c WHERE c.precioCompra = :precioCompra")})
public class ComprasDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "detalle_id")
    private Integer detalleId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compras_id")
    private int comprasId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_compra")
    private int precioCompra;
    @JsonBackReference(value="detalle-compras")
    @JoinColumn(name = "compras_id", referencedColumnName = "compras_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Compras compras;
    @JoinColumn(name = "producto_id", referencedColumnName = "producto_id")
    @ManyToOne(optional = false)
    private Productos productoId;
    
    /*
     * 	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="usuarios_roles", joinColumns = @JoinColumn(name="usuario_id"),
	inverseJoinColumns = @JoinColumn(name="rol_id"),
	uniqueConstraints = { @UniqueConstraint (columnNames = {"detalle_id", "compra_id"})})
	private List <Rol> roles;
     * */

    public ComprasDetalle() {
    }

    public ComprasDetalle(Integer comprasId) {
        this.detalleId = comprasId;
    }

	public ComprasDetalle(Integer detalleId, int cantidad, int comprasId,  int precioCompra) {
		this.detalleId = detalleId;
        this.cantidad = cantidad;
        this.comprasId = comprasId;
        this.precioCompra = precioCompra;
    }

    public Integer getDetalleId() {
		return detalleId;
	}

	public void setDetalleId(Integer detalleId) {
		this.detalleId = detalleId;
	}

    public int getComprasId() {
		return comprasId;
	}

	public void setComprasId(int comprasId) {
		this.comprasId = comprasId;
	}

	public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(int precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Compras getCompras() {
        return compras;
    }

    public void setCompras(Compras compras) {
        this.compras = compras;
    }

    public Productos getProductoId() {
        return productoId;
    }

    public void setProductoId(Productos productoId) {
        this.productoId = productoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleId != null ? detalleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComprasDetalle)) {
            return false;
        }
        ComprasDetalle other = (ComprasDetalle) object;
        if ((this.detalleId == null && other.detalleId != null) || (this.detalleId != null && !this.detalleId.equals(other.detalleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spa.ComprasDetalle[ comprasId=" + detalleId + " ]";
    }
    
}
