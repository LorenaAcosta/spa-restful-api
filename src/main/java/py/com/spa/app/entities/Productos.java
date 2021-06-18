/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.spa.app.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import py.com.spa.app.enumeraciones.EstadoProducto;

/**
 *
 * @author Lore
 */
@Entity
@Table(name = "productos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productos.findAll", query = "SELECT p FROM Productos p"),
    @NamedQuery(name = "Productos.findByProductoId", query = "SELECT p FROM Productos p WHERE p.productoId = :productoId"),
    //@NamedQuery(name = "Productos.findByCodigo", query = "SELECT p FROM Productos p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Productos.findByDescripcion", query = "SELECT p FROM Productos p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Productos.findByCosto", query = "SELECT p FROM Productos p WHERE p.costo = :costo"),
    @NamedQuery(name = "Productos.findByPrecioVenta", query = "SELECT p FROM Productos p WHERE p.precioVenta = :precioVenta"),
    @NamedQuery(name = "Productos.findByStockActual", query = "SELECT p FROM Productos p WHERE p.stockActual = :stockActual"),
    @NamedQuery(name = "Productos.findByImageName", query = "SELECT p FROM Productos p WHERE p.imageName = :imageName"),
    @NamedQuery(name = "Productos.findByEstado", query = "SELECT p FROM Productos p WHERE p.estado = :estado")})
public class Productos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "producto_id")
    private Integer productoId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "codigo",  unique=true)
    private String codigo;
 
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion", unique=true)
    private String descripcion;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo")
    private int costo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_venta")
    private int precioVenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stock_actual")
    private int stockActual;
    /*@Size(max = 2147483647)
    @Column(name = "image_name")
    private String imageName;*/
    
    @Size(max = 2147483647)
    @Column(name = "image_name")
    private String imageName;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 8)
    private EstadoProducto estado;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "productoId")
    private Collection<VentasDetalle> ventasDetalleCollection;
    /**/
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "productoId")
    private Collection<ComprasDetalle> comprasDetalleCollection;
    /**/
    @JoinColumn(name = "categoria_id", referencedColumnName = "categoria_id")
    @ManyToOne(optional = false)
    private Categorias categoriaId;
    /**/
    @JoinColumn(name = "impuesto_id", referencedColumnName = "impuesto_id")
    @ManyToOne(optional = false)
    private Impuesto impuestoId;
    /**/

    public Productos() {
    }

    public Productos(Integer productoId) {
        this.productoId = productoId;
    }

    public Productos(Integer productoId, @NotNull @Size(min = 1, max = 2147483647) String codigo,
			@NotNull @Size(min = 1, max = 2147483647) String descripcion, @NotNull int costo, @NotNull int precioVenta,
			@NotNull int stockActual, @NotNull EstadoProducto estado) {
		super();
		this.productoId = productoId;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.costo = costo;
		this.precioVenta = precioVenta;
		this.stockActual = stockActual;
		this.estado = estado;
	}

	public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(int precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public EstadoProducto getEstado() {
		return estado;
	}

	public void setEstado(EstadoProducto estado) {
		this.estado = estado;
	}

	@JsonBackReference(value="ventasdetalle-productos")
    @XmlTransient
    public Collection<VentasDetalle> getVentasDetalleCollection() {
        return ventasDetalleCollection;
    }

    public void setVentasDetalleCollection(Collection<VentasDetalle> ventasDetalleCollection) {
        this.ventasDetalleCollection = ventasDetalleCollection;
    }
    @JsonBackReference(value="getComprasDetalleCollection")
    @XmlTransient
    public Collection<ComprasDetalle> getComprasDetalleCollection() {
        return comprasDetalleCollection;
    }

    public void setComprasDetalleCollection(Collection<ComprasDetalle> comprasDetalleCollection) {
        this.comprasDetalleCollection = comprasDetalleCollection;
    }

 
    public Categorias getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Categorias categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Impuesto getImpuestoId() {
		return impuestoId;
	}

	public void setImpuestoId(Impuesto impuestoId) {
		this.impuestoId = impuestoId;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (productoId != null ? productoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productos)) {
            return false;
        }
        Productos other = (Productos) object;
        if ((this.productoId == null && other.productoId != null) || (this.productoId != null && !this.productoId.equals(other.productoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spa.Productos[ productoId=" + productoId + " ]";
    }
    
}
