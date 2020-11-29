/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.spa.app.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lore
 */
@Entity
@Table(name = "ventas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ventas.findAll", query = "SELECT v FROM Ventas v"),
    @NamedQuery(name = "Ventas.findByVentasId", query = "SELECT v FROM Ventas v WHERE v.ventasId = :ventasId"),
    @NamedQuery(name = "Ventas.findByNumeroComprobante", query = "SELECT v FROM Ventas v WHERE v.numeroComprobante = :numeroComprobante"),
    @NamedQuery(name = "Ventas.findByFecha", query = "SELECT v FROM Ventas v WHERE v.fecha = :fecha"),
    @NamedQuery(name = "Ventas.findByMontoTotal", query = "SELECT v FROM Ventas v WHERE v.montoTotal = :montoTotal"),
    @NamedQuery(name = "Ventas.findByEstado", query = "SELECT v FROM Ventas v WHERE v.estado = :estado")})
public class Ventas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ventas_id")
    private Integer ventasId;
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Column(name = "numero_comprobante")
    private long numeroComprobante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto_total")
    private int montoTotal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "estado")
    private String estado;
    /*@OneToOne(cascade = CascadeType.ALL, mappedBy = "ventas")
    private VentasDetalle ventasDetalle;*/
    /**/
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    @ManyToOne(optional = false)
    private Usuario usuarioId;
    /**/
    @JoinColumn(name = "medio_pago_id", referencedColumnName = "medio_pago_id")
    @ManyToOne(optional = false)
    private MediosPago medioPagoId;
    /**/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ventas")  
    private Collection<VentasDetalle> ventasDetalleCollection;
    /**/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ventas")
    private Collection<PlanPago> planPagoCollection;

    public Ventas() {
    }

    public Ventas(Integer ventasId) {
        this.ventasId = ventasId;
    }


    public Ventas(Integer ventasId, @NotNull int numerocomprobante, @NotNull Date fecha, @NotNull int montoTotal,
			@NotNull @Size(min = 1, max = 2147483647) String estado, Usuario usuarioId, MediosPago medioPagoId,
			Collection<VentasDetalle> ventasDetalleCollection, Collection<PlanPago> planPagoCollection) {
		super();
		this.ventasId = ventasId;
		this.numeroComprobante = numerocomprobante;
		this.fecha = fecha;
		this.montoTotal = montoTotal;
		this.estado = estado;
		this.usuarioId = usuarioId;
		this.medioPagoId = medioPagoId;
		this.ventasDetalleCollection = ventasDetalleCollection;
		this.planPagoCollection = planPagoCollection;
	}
    
    

	public Collection<VentasDetalle> getVentasDetalleCollection() {
		return ventasDetalleCollection;
	}

	public void setVentasDetalleCollection(Collection<VentasDetalle> ventasDetalleCollection) {
		this.ventasDetalleCollection = ventasDetalleCollection;
	}

	public Integer getVentasId() {
        return ventasId;
    }

    public void setVentasId(Integer ventasId) {
        this.ventasId = ventasId;
    }

    public long getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(int numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(int montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public MediosPago getMedioPagoId() {
        return medioPagoId;
    }

    public void setMedioPagoId(MediosPago medioPagoId) {
        this.medioPagoId = medioPagoId;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }

    @XmlTransient
    public Collection<PlanPago> getPlanPagoCollection() {
        return planPagoCollection;
    }

    public void setPlanPagoCollection(Collection<PlanPago> planPagoCollection) {
        this.planPagoCollection = planPagoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ventasId != null ? ventasId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ventas)) {
            return false;
        }
        Ventas other = (Ventas) object;
        if ((this.ventasId == null && other.ventasId != null) || (this.ventasId != null && !this.ventasId.equals(other.ventasId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spa.Ventas[ ventasId=" + ventasId + " ]";
    }
    
}
