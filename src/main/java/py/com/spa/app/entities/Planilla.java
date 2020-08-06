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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lore
 */
@Entity
@Table(name = "planilla")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Planilla.findAll", query = "SELECT p FROM Planilla p"),
    @NamedQuery(name = "Planilla.findByCedula", query = "SELECT p FROM Planilla p WHERE p.cedula = :cedula"),
    @NamedQuery(name = "Planilla.findBySalarioBase", query = "SELECT p FROM Planilla p WHERE p.salarioBase = :salarioBase"),
    @NamedQuery(name = "Planilla.findByBonificacionVentas", query = "SELECT p FROM Planilla p WHERE p.bonificacionVentas = :bonificacionVentas"),
    @NamedQuery(name = "Planilla.findByLiquidoCobrado", query = "SELECT p FROM Planilla p WHERE p.liquidoCobrado = :liquidoCobrado")})
public class Planilla implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cedula")
    private Integer cedula;
    @Basic(optional = false)
    @NotNull
    @Column(name = "salario_base")
    private int salarioBase;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bonificacion_ventas")
    private int bonificacionVentas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquido_cobrado")
    private int liquidoCobrado;
    @JoinColumn(name = "cedula", referencedColumnName = "cedula", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Empleados empleados;

    public Planilla() {
    }

    public Planilla(Integer cedula) {
        this.cedula = cedula;
    }

    public Planilla(Integer cedula, int salarioBase, int bonificacionVentas, int liquidoCobrado) {
        this.cedula = cedula;
        this.salarioBase = salarioBase;
        this.bonificacionVentas = bonificacionVentas;
        this.liquidoCobrado = liquidoCobrado;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public int getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(int salarioBase) {
        this.salarioBase = salarioBase;
    }

    public int getBonificacionVentas() {
        return bonificacionVentas;
    }

    public void setBonificacionVentas(int bonificacionVentas) {
        this.bonificacionVentas = bonificacionVentas;
    }

    public int getLiquidoCobrado() {
        return liquidoCobrado;
    }

    public void setLiquidoCobrado(int liquidoCobrado) {
        this.liquidoCobrado = liquidoCobrado;
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
        hash += (cedula != null ? cedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planilla)) {
            return false;
        }
        Planilla other = (Planilla) object;
        if ((this.cedula == null && other.cedula != null) || (this.cedula != null && !this.cedula.equals(other.cedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.spa.app.entities.Planilla[ cedula=" + cedula + " ]";
    }
    
}
