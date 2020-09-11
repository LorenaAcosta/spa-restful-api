/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  py.com.spa.app.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lore
 */
@Entity
@Table(name = "empleado_disponible")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpleadoDisponible.findAll", query = "SELECT e FROM EmpleadoDisponible e"),
    @NamedQuery(name = "EmpleadoDisponible.findByEmpleadoId", query = "SELECT e FROM EmpleadoDisponible e WHERE e.empleadoId = :empleadoId")})
public class EmpleadoDisponible implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "empleado_id")
    private Integer empleadoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadoId")
    private Collection<ReservaDetalle> reservaDetalleCollection;
    @JoinColumn(name = "empleado_id", referencedColumnName = "empleado_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Empleados empleados;
    @JoinColumn(name = "servicio_id", referencedColumnName = "servicio_id")
    @ManyToOne(optional = false)
    private Servicios servicioId;

    public EmpleadoDisponible() {
    }

    public EmpleadoDisponible(Integer empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Integer getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Integer empleadoId) {
        this.empleadoId = empleadoId;
    }

    @XmlTransient
    public Collection<ReservaDetalle> getReservaDetalleCollection() {
        return reservaDetalleCollection;
    }

    public void setReservaDetalleCollection(Collection<ReservaDetalle> reservaDetalleCollection) {
        this.reservaDetalleCollection = reservaDetalleCollection;
    }

    public Empleados getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
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
        hash += (empleadoId != null ? empleadoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpleadoDisponible)) {
            return false;
        }
        EmpleadoDisponible other = (EmpleadoDisponible) object;
        if ((this.empleadoId == null && other.empleadoId != null) || (this.empleadoId != null && !this.empleadoId.equals(other.empleadoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.EmpleadoDisponible[ empleadoId=" + empleadoId + " ]";
    }
    
}
