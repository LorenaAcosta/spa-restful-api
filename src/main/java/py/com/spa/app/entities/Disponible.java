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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 *
 * @author Lore
 */
@Entity
@Table(name = "disponible")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Disponible.findAll", query = "SELECT d FROM Disponible d"),
    @NamedQuery(name = "Disponible.findByDisponibleId", query = "SELECT d FROM Disponible d WHERE d.disponibleId = :disponibleId"),
    @NamedQuery(name = "Disponible.findByComision", query = "SELECT d FROM Disponible d WHERE d.comision = :comision")})
public class Disponible implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "disponible_id")
    private Integer disponibleId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "comision")
    private float comision;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "disponibleId")
    private Collection<ReservaDetalle> reservaDetalleCollection;
    @JoinColumn(name = "empleado_id", referencedColumnName = "empleado_id")
    @ManyToOne(optional = false)
    private Empleados empleadoId;
    @JoinColumn(name = "servicio_id", referencedColumnName = "servicio_id")
    @ManyToOne(optional = false)
    private Servicios servicioId;

    public Disponible() {
    }

    public Disponible(Integer disponibleId) {
        this.disponibleId = disponibleId;
    }

    public Disponible(Integer disponibleId, float comision) {
        this.disponibleId = disponibleId;
        this.comision = comision;
    }


    public Integer getDisponibleId() {
        return disponibleId;
    }

    public void setDisponibleId(Integer disponibleId) {
        this.disponibleId = disponibleId;
    }

    public float getComision() {
        return comision;
    }

    public void setComision(float comision) {
        this.comision = comision;
    }

    @JsonBackReference(value="disponible-reserva")
    @XmlTransient
    public Collection<ReservaDetalle> getReservaDetalleCollection() {
        return reservaDetalleCollection;
    }

    public void setReservaDetalleCollection(Collection<ReservaDetalle> reservaDetalleCollection) {
        this.reservaDetalleCollection = reservaDetalleCollection;
    }
    

   
    public Empleados getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Empleados empleadoId) {
        this.empleadoId = empleadoId;
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
        hash += (disponibleId != null ? disponibleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Disponible)) {
            return false;
        }
        Disponible other = (Disponible) object;
        if ((this.disponibleId == null && other.disponibleId != null) || (this.disponibleId != null && !this.disponibleId.equals(other.disponibleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.spa.app.Disponible[ disponibleId=" + disponibleId + " ]";
    }
    
}
