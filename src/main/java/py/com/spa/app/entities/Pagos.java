/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.spa.app.entities;

import java.io.Serializable;
import java.util.List;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "pagos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagos.findAll", query = "SELECT p FROM Pagos p"),
    @NamedQuery(name = "Pagos.findByPagoId", query = "SELECT p FROM Pagos p WHERE p.pagoId = :pagoId"),
    @NamedQuery(name = "Pagos.findByFecha", query = "SELECT p FROM Pagos p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Pagos.findByTotal", query = "SELECT p FROM Pagos p WHERE p.total = :total")})
public class Pagos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pago_id")
    private Integer pagoId;

    @Basic(optional = false)
    @Column(name = "fecha")
    private String fecha;

    @Basic(optional = false)
    @Column(name = "total")
    private int total;
    
    @Basic(optional = false)
    @Column(name = "usuario_id")
    private String usuarioId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pagos")
    private List<PagosDetalle> pagosDetalleList;

    @JoinColumn(name = "medio_pago_id", referencedColumnName = "medio_pago_id")
    @ManyToOne(optional = false)
    private MediosPago medioPagoId;
    
    /*@JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    @ManyToOne(optional = false)
    private Usuario usuarioId;*/

    public Pagos() {
    }

    public Pagos(Integer pagoId) {
        this.pagoId = pagoId;
    }

    public Pagos(Integer pagoId, String fecha, int total) {
        this.pagoId = pagoId;
        this.fecha = fecha;
        this.total = total;
    }

    public Integer getPagoId() {
        return pagoId;
    }

    public void setPagoId(Integer pagoId) {
        this.pagoId = pagoId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @XmlTransient
    public List<PagosDetalle> getPagosDetalleList() {
        return pagosDetalleList;
    }

    public void setPagosDetalleList(List<PagosDetalle> pagosDetalleList) {
        this.pagosDetalleList = pagosDetalleList;
    }

    public MediosPago getMedioPagoId() {
        return medioPagoId;
    }

    public void setMedioPagoId(MediosPago medioPagoId) {
        this.medioPagoId = medioPagoId;
    }

    public String getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (pagoId != null ? pagoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagos)) {
            return false;
        }
        Pagos other = (Pagos) object;
        if ((this.pagoId == null && other.pagoId != null) || (this.pagoId != null && !this.pagoId.equals(other.pagoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Pagos[ pagoId=" + pagoId + " ]";
    }

}
