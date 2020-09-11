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

/**
 *
 * @author PC
 */
@Embeddable
public class PagosDetallePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "pago_detalle_id")
    private int pagoDetalleId;

    @Basic(optional = false)
    @Column(name = "pago_id")
    private int pagoId;

    public PagosDetallePK() {
    }

    public PagosDetallePK(int pagoDetalleId, int pagoId) {
        this.pagoDetalleId = pagoDetalleId;
        this.pagoId = pagoId;
    }

    public int getPagoDetalleId() {
        return pagoDetalleId;
    }

    public void setPagoDetalleId(int pagoDetalleId) {
        this.pagoDetalleId = pagoDetalleId;
    }

    public int getPagoId() {
        return pagoId;
    }

    public void setPagoId(int pagoId) {
        this.pagoId = pagoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pagoDetalleId;
        hash += (int) pagoId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagosDetallePK)) {
            return false;
        }
        PagosDetallePK other = (PagosDetallePK) object;
        if (this.pagoDetalleId != other.pagoDetalleId) {
            return false;
        }
        if (this.pagoId != other.pagoId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PagosDetallePK[ pagoDetalleId=" + pagoDetalleId + ", pagoId=" + pagoId + " ]";
    }

}
