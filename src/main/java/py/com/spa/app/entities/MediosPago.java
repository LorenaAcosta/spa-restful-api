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

/**
 *
 * @author Lore
 */
@Entity
@Table(name = "medios_pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MediosPago.findAll", query = "SELECT m FROM MediosPago m"),
    @NamedQuery(name = "MediosPago.findByMedioPagoId", query = "SELECT m FROM MediosPago m WHERE m.medioPagoId = :medioPagoId"),
    @NamedQuery(name = "MediosPago.findByCodigo", query = "SELECT m FROM MediosPago m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "MediosPago.findByDescripcion", query = "SELECT m FROM MediosPago m WHERE m.descripcion = :descripcion")})
public class MediosPago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "medio_pago_id")
    private Integer medioPagoId;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medioPagoId")
    private Collection<Ventas> ventasCollection;

    public MediosPago() {
    }

    public MediosPago(Integer medioPagoId) {
        this.medioPagoId = medioPagoId;
    }

    public MediosPago(Integer medioPagoId, String codigo, String descripcion) {
        this.medioPagoId = medioPagoId;
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public Integer getMedioPagoId() {
        return medioPagoId;
    }

    public void setMedioPagoId(Integer medioPagoId) {
        this.medioPagoId = medioPagoId;
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
    public Collection<Ventas> getVentasCollection() {
        return ventasCollection;
    }

    public void setVentasCollection(Collection<Ventas> ventasCollection) {
        this.ventasCollection = ventasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medioPagoId != null ? medioPagoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MediosPago)) {
            return false;
        }
        MediosPago other = (MediosPago) object;
        if ((this.medioPagoId == null && other.medioPagoId != null) || (this.medioPagoId != null && !this.medioPagoId.equals(other.medioPagoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.spa.app.entities.MediosPago[ medioPagoId=" + medioPagoId + " ]";
    }
    
}
