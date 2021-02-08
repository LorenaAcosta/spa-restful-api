/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.spa.app.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "compras")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compras.findAll", query = "SELECT c FROM Compras c"),
    @NamedQuery(name = "Compras.findByComprasId", query = "SELECT c FROM Compras c WHERE c.comprasId = :comprasId"),
    @NamedQuery(name = "Compras.findByFecha", query = "SELECT c FROM Compras c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Compras.findByMontoTotal", query = "SELECT c FROM Compras c WHERE c.montoTotal = :montoTotal")})
public class Compras implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "compras_id")
    private Integer comprasId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha" )
    @Temporal(TemporalType.DATE ) 
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "monto_total")
    private String montoTotal;
    @JoinColumn(name = "proveedor_id", referencedColumnName = "proveedor_id")
    @ManyToOne(optional = false)
    private Proveedor proveedorId;
    @Column(nullable = true)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compras")  
    private Collection<ComprasDetalle> detallesCollection;

    public Compras() {
    }

    public Compras(Integer comprasId) {
        this.comprasId = comprasId;
    }

    public Compras(Integer comprasId, Date fecha, String montoTotal) {
        this.comprasId = comprasId;
        this.fecha = fecha;
        this.montoTotal = montoTotal;
    }

    public Integer getComprasId() {
        return comprasId;
    }

    public void setComprasId(Integer comprasId) {
        this.comprasId = comprasId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(String montoTotal) {
        this.montoTotal = montoTotal;
    }
    public Proveedor getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Proveedor proveedorId) {
        this.proveedorId = proveedorId;
    }
    //@JsonBackReference(value="compras-detalle")
    @XmlTransient
    public Collection<ComprasDetalle> getDetallesCollection() {
        return detallesCollection;
    }
    public void setComprasCollection(Collection<ComprasDetalle> detallesCollection) {
        this.detallesCollection = detallesCollection;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (comprasId != null ? comprasId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compras)) {
            return false;
        }
        Compras other = (Compras) object;
        if ((this.comprasId == null && other.comprasId != null) || (this.comprasId != null && !this.comprasId.equals(other.comprasId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.spa.app.entities.Compras[ comprasId=" + comprasId + " ]";
    }
    
}
