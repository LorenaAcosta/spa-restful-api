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
@Table(name = "tipos_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposProducto.findAll", query = "SELECT t FROM TiposProducto t"),
    @NamedQuery(name = "TiposProducto.findByTipoProductoId", query = "SELECT t FROM TiposProducto t WHERE t.tipoProductoId = :tipoProductoId"),
    @NamedQuery(name = "TiposProducto.findByCodigo", query = "SELECT t FROM TiposProducto t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TiposProducto.findByDescripcion", query = "SELECT t FROM TiposProducto t WHERE t.descripcion = :descripcion")})
public class TiposProducto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_producto_id")
    private Integer tipoProductoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoProductoId")
    @JsonBackReference
    private List<Productos> productosList;

    public TiposProducto() {
    }

    public TiposProducto(Integer tipoProductoId) {
        this.tipoProductoId = tipoProductoId;
    }

    public TiposProducto(Integer tipoProductoId, String codigo, String descripcion) {
        this.tipoProductoId = tipoProductoId;
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public Integer getTipoProductoId() {
        return tipoProductoId;
    }

    public void setTipoProductoId(Integer tipoProductoId) {
        this.tipoProductoId = tipoProductoId;
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

    @XmlTransient
    public List<Productos> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Productos> productosList) {
        this.productosList = productosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoProductoId != null ? tipoProductoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposProducto)) {
            return false;
        }
        TiposProducto other = (TiposProducto) object;
        if ((this.tipoProductoId == null && other.tipoProductoId != null) || (this.tipoProductoId != null && !this.tipoProductoId.equals(other.tipoProductoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TiposProducto[ tipoProductoId=" + tipoProductoId + " ]";
    }
    
}
