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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 *
 * @author Lore
 */
@Entity
@Table(name = "servicios_empleados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiciosEmpleados.findAll", query = "SELECT s FROM ServiciosEmpleados s"),
    @NamedQuery(name = "ServiciosEmpleados.findByEmpleadoId", query = "SELECT s FROM ServiciosEmpleados s WHERE s.empleadoId = :empleadoId")})
public class ServiciosEmpleados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "empleado_id")
    private Integer empleadoId;
    
    @JsonManagedReference(value="serv-emp")
    @JoinColumn(name = "categoria_id", referencedColumnName = "categoria_id")
    @ManyToOne(optional = false)
    private Categorias categoriaId;
    
    @JoinColumn(name = "empleado_id", referencedColumnName = "cedula", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Empleados empleados;

    public ServiciosEmpleados() {
    }

    public ServiciosEmpleados(Integer empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Integer getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Integer empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Categorias getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Categorias categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Empleados getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
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
        if (!(object instanceof ServiciosEmpleados)) {
            return false;
        }
        ServiciosEmpleados other = (ServiciosEmpleados) object;
        if ((this.empleadoId == null && other.empleadoId != null) || (this.empleadoId != null && !this.empleadoId.equals(other.empleadoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.spa.app.entities.ServiciosEmpleados[ empleadoId=" + empleadoId + " ]";
    }
    
}
