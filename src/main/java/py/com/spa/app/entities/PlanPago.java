/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.spa.app.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 *
 * @author Lore
 */
@Entity
@Table(name = "plan_pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanPago.findAll", query = "SELECT p FROM PlanPago p"),
    @NamedQuery(name = "PlanPago.findByVentasId", query = "SELECT p FROM PlanPago p WHERE p.planPagoPK.ventasId = :ventasId"),
    @NamedQuery(name = "PlanPago.findByNumeroCuota", query = "SELECT p FROM PlanPago p WHERE p.planPagoPK.numeroCuota = :numeroCuota"),
    @NamedQuery(name = "PlanPago.findByMontoCuota", query = "SELECT p FROM PlanPago p WHERE p.montoCuota = :montoCuota"),
    @NamedQuery(name = "PlanPago.findByVencimiento", query = "SELECT p FROM PlanPago p WHERE p.vencimiento = :vencimiento"),
    @NamedQuery(name = "PlanPago.findByFechaPago", query = "SELECT p FROM PlanPago p WHERE p.fechaPago = :fechaPago")})
public class PlanPago implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PlanPagoPK planPagoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto_cuota")
    private int montoCuota;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vencimiento")
    @Temporal(TemporalType.DATE)
    private Date vencimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_pago")
    @Temporal(TemporalType.DATE)
    private Date fechaPago;
    @JoinColumn(name = "ventas_id", referencedColumnName = "ventas_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ventas ventas;

    public PlanPago() {
    }

    public PlanPago(PlanPagoPK planPagoPK) {
        this.planPagoPK = planPagoPK;
    }

    public PlanPago(PlanPagoPK planPagoPK, int montoCuota, Date vencimiento, Date fechaPago) {
        this.planPagoPK = planPagoPK;
        this.montoCuota = montoCuota;
        this.vencimiento = vencimiento;
        this.fechaPago = fechaPago;
    }

    public PlanPago(int ventasId, int numeroCuota) {
        this.planPagoPK = new PlanPagoPK(ventasId, numeroCuota);
    }

    public PlanPagoPK getPlanPagoPK() {
        return planPagoPK;
    }

    public void setPlanPagoPK(PlanPagoPK planPagoPK) {
        this.planPagoPK = planPagoPK;
    }

    public int getMontoCuota() {
        return montoCuota;
    }

    public void setMontoCuota(int montoCuota) {
        this.montoCuota = montoCuota;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    @JsonBackReference(value="planpago")
    public Ventas getVentas() {
        return ventas;
    }

    public void setVentas(Ventas ventas) {
        this.ventas = ventas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (planPagoPK != null ? planPagoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanPago)) {
            return false;
        }
        PlanPago other = (PlanPago) object;
        if ((this.planPagoPK == null && other.planPagoPK != null) || (this.planPagoPK != null && !this.planPagoPK.equals(other.planPagoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.spa.app.entities.PlanPago[ planPagoPK=" + planPagoPK + " ]";
    }
    
}
