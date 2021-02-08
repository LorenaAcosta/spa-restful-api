/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.spa.app.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Lore
 */
@Embeddable
public class PlanPagoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ventas_id")
    private int ventasId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_cuota")
    private int numeroCuota;

    public PlanPagoPK() {
    }

    public PlanPagoPK(int ventasId, int numeroCuota) {
        this.ventasId = ventasId;
        this.numeroCuota = numeroCuota;
    }

    public int getVentasId() {
        return ventasId;
    }

    public void setVentasId(int ventasId) {
        this.ventasId = ventasId;
    }

    public int getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(int numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) ventasId;
        hash += (int) numeroCuota;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanPagoPK)) {
            return false;
        }
        PlanPagoPK other = (PlanPagoPK) object;
        if (this.ventasId != other.ventasId) {
            return false;
        }
        if (this.numeroCuota != other.numeroCuota) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.spa.app.entities.PlanPagoPK[ ventasId=" + ventasId + ", numeroCuota=" + numeroCuota + " ]";
    }
    
}
