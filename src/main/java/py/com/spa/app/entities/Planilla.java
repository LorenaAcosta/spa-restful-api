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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 *
 * @author Lore
 */
@Entity
@Table(name = "planilla")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Planilla.findAll", query = "SELECT p FROM Planilla p"),
    @NamedQuery(name = "Planilla.findByPlanillaId", query = "SELECT p FROM Planilla p WHERE p.planillaId = :planillaId"),
    @NamedQuery(name = "Planilla.findBySalarioBase", query = "SELECT p FROM Planilla p WHERE p.salarioBase = :salarioBase"),
    @NamedQuery(name = "Planilla.findByBonificacionVentas", query = "SELECT p FROM Planilla p WHERE p.bonificacionVentas = :bonificacionVentas"),
    @NamedQuery(name = "Planilla.findByLiquidoCobrado", query = "SELECT p FROM Planilla p WHERE p.liquidoCobrado = :liquidoCobrado")})
public class Planilla implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "planilla_id")
    private Integer planillaId;
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
    @JoinColumn(name = "empleado_id", referencedColumnName = "empleado_id")
    @ManyToOne(optional = false)
    private Empleados empleadoId;

    public Planilla() {
    }

    public Planilla(Integer planillaId) {
        this.planillaId = planillaId;
    }

    public Planilla(Integer planillaId, int salarioBase, int bonificacionVentas, int liquidoCobrado) {
        this.planillaId = planillaId;
        this.salarioBase = salarioBase;
        this.bonificacionVentas = bonificacionVentas;
        this.liquidoCobrado = liquidoCobrado;
    }

    public Integer getPlanillaId() {
        return planillaId;
    }

    public void setPlanillaId(Integer planillaId) {
        this.planillaId = planillaId;
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

    @JsonBackReference(value="planilla-empleado")
    public Empleados getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Empleados empleadoId) {
        this.empleadoId = empleadoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (planillaId != null ? planillaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planilla)) {
            return false;
        }
        Planilla other = (Planilla) object;
        if ((this.planillaId == null && other.planillaId != null) || (this.planillaId != null && !this.planillaId.equals(other.planillaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.spa.app.entities.Planilla[ planillaId=" + planillaId + " ]";
    }
    
}
