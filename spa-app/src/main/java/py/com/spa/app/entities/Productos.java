/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.spa.app.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    @NamedQuery(name = "Productos.findByDescripcion", query = "SELECT p FROM Productos p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Productos.findByFechaVigencia", query = "SELECT p FROM Productos p WHERE p.fechaVigencia = :fechaVigencia"),
    @NamedQuery(name = "Productos.findByCodigo", query = "SELECT p FROM Productos p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Productos.findByEstado", query = "SELECT p FROM Productos p WHERE p.estado = :estado"),
    @NamedQuery(name = "Productos.findByDuracion", query = "SELECT p FROM Productos p WHERE p.duracion = :duracion"),
    @NamedQuery(name = "Productos.findByCosto", query = "SELECT p FROM Productos p WHERE p.costo = :costo")})
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
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "fecha_vigencia")
    private String fechaVigencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "duracion")
    private String duracion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo")
    private int costo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoId")
    private List<PagosDetalle> pagosDetalleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoId")
    @JsonBackReference
    private List<Reservas> reservasList;
    @JoinColumn(name = "categoria_id", referencedColumnName = "categoria_id")
    @ManyToOne(optional = false)
    private Categorias categoriaId;
    @JoinColumn(name = "tipo_producto_id", referencedColumnName = "tipo_producto_id")
    @ManyToOne(optional = false)
    private TiposProducto tipoProductoId;

    public Productos() {
    }

    public Productos(Integer productoId) {
        this.productoId = productoId;
    }

    public Productos(Integer productoId, String descripcion, String fechaVigencia, String codigo, String estado, String duracion, int costo) {
        this.productoId = productoId;
        this.descripcion = descripcion;
        this.fechaVigencia = fechaVigencia;
        this.codigo = codigo;
        this.estado = estado;
        this.duracion = duracion;
        this.costo = costo;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(String fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    @XmlTransient
    public List<PagosDetalle> getPagosDetalleList() {
        return pagosDetalleList;
    }

    public void setPagosDetalleList(List<PagosDetalle> pagosDetalleList) {
        this.pagosDetalleList = pagosDetalleList;
    }

    @XmlTransient
    public List<Reservas> getReservasList() {
        return reservasList;
    }

    public void setReservasList(List<Reservas> reservasList) {
        this.reservasList = reservasList;
    }

    public Categorias getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Categorias categoriaId) {
        this.categoriaId = categoriaId;
    }

    public TiposProducto getTipoProductoId() {
        return tipoProductoId;
    }

    public void setTipoProductoId(TiposProducto tipoProductoId) {
        this.tipoProductoId = tipoProductoId;
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
        return "entities.Productos[ productoId=" + productoId + " ]";
    }
    
}
