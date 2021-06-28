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
import javax.persistence.OneToMany;
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
/*@NamedQueries({
    @NamedQuery(name = "Ventas.findAll", query = "SELECT v FROM Ventas v"),
    @NamedQuery(name = "Ventas.findByVentasId", query = "SELECT v FROM Ventas v WHERE v.ventasId = :ventasId"),
    @NamedQuery(name = "Ventas.findByNumeroComprobante", query = "SELECT v FROM Ventas v WHERE v.numeroComprobante = :numeroComprobante"),
    @NamedQuery(name = "Ventas.findByFecha", query = "SELECT v FROM Ventas v WHERE v.fecha = :fecha"),
    @NamedQuery(name = "Ventas.findByMontoTotal", query = "SELECT v FROM Ventas v WHERE v.montoTotal = :montoTotal"),
    @NamedQuery(name = "Ventas.findByIvaCinco", query = "SELECT v FROM Ventas v WHERE v.ivaCinco = :ivaCinco"),
    @NamedQuery(name = "Ventas.findByIvaDiez", query = "SELECT v FROM Ventas v WHERE v.ivaDiez = :ivaDiez"),
    @NamedQuery(name = "Ventas.findByIvaTotal", query = "SELECT v FROM Ventas v WHERE v.ivaTotal = :ivaTotal"),
    @NamedQuery(name = "Ventas.findByEstado", query = "SELECT v FROM Ventas v WHERE v.estado = :estado")})*/
public class Ventas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ventas_id")
    private Integer ventasId;
    @Basic(optional = false)
    @NotNull
    //@GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Column(name = "numero_comprobante")
    private int numeroComprobante;
    /**/
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "comprobante_completo", unique=false )
    private String comprobanteCompleto;
    /**/
    @JoinColumn(name = "comprobante_id", referencedColumnName = "comprobante_id")
    @ManyToOne(optional = true)
    private Comprobante comprobanteId;
    /**/
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto_total")
    private Long montoTotal;
    /**/
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "monto_total_letras", unique=false )
    private String montoTotalLetras;
    /**/
    @Basic(optional = false)
    @NotNull
    @Column(name = "iva_cinco")
    private Long ivaCinco;
    /**/
    @Basic(optional = false)
    @NotNull
    @Column(name = "iva_diez")
    private Long ivaDiez;
    /**/
    @Basic(optional = false)
    @NotNull
    @Column(name = "iva_total")
    private Long ivaTotal;
    /**/
    @Basic(optional = false)
    @NotNull
    @Column(name = "sub_total_cinco")
    private Long subTotalCinco;
    /**/
    @Basic(optional = false)
    @NotNull
    @Column(name = "sub_total_diez")
    private Long subTotalDiez;
    /**/
    @Basic(optional = false)
    @NotNull
    @Column(name = "sub_total_exenta")
    private Long subTotalExenta;
    /**/
    @Basic(optional = false)
    @NotNull
    @Column(name = "sub_total_total")
    private Long subTotalTotal;
    /**/
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_descuento")
    private Long totalDescuento;
    /**/
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "estado")
    private String estado;

    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    @ManyToOne(optional = false)
    private Usuario usuarioId;
    /**/
    @JoinColumn(name = "medio_pago_id", referencedColumnName = "medio_pago_id")
    @ManyToOne(optional = false)
    private MediosPago medioPagoId;
    /**/
    //@JsonBackReference(value="ventas-detalle")
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "ventas")  
    private Collection<VentasDetalle> ventasDetalleCollection;
    /**/
    @JoinColumn(name = "arqueo_id", referencedColumnName = "arqueo_id")
    @ManyToOne(optional = true)
    private ArqueoCaja arqueoId;
    /**/
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "ventas")
    private Collection<PlanPago> planPagoCollection;

    public Ventas() {
    }

    public Ventas(Integer ventasId) {
        this.ventasId = ventasId;
    }

	public Ventas(Integer ventasId, @NotNull int numeroComprobante,
			@NotNull @Size(min = 1, max = 2147483647) String comprobanteCompleto, Comprobante comprobanteId,
			@NotNull Date fecha, @NotNull Long montoTotal,
			@NotNull @Size(min = 1, max = 2147483647) String montoTotalLetras, @NotNull Long ivaCinco,
			@NotNull Long ivaDiez, @NotNull Long ivaTotal, @NotNull Long subTotalCinco, @NotNull Long subTotalDiez,
			@NotNull Long subTotalExenta, @NotNull Long subTotalTotal, @NotNull Long totalDescuento,
			@NotNull @Size(min = 1, max = 2147483647) String estado, Usuario usuarioId, MediosPago medioPagoId) {
		super();
		this.ventasId = ventasId;
		this.numeroComprobante = numeroComprobante;
		this.comprobanteCompleto = comprobanteCompleto;
		this.comprobanteId = comprobanteId;
		this.fecha = fecha;
		this.montoTotal = montoTotal;
		this.montoTotalLetras = montoTotalLetras;
		this.ivaCinco = ivaCinco;
		this.ivaDiez = ivaDiez;
		this.ivaTotal = ivaTotal;
		this.subTotalCinco = subTotalCinco;
		this.subTotalDiez = subTotalDiez;
		this.subTotalExenta = subTotalExenta;
		this.subTotalTotal = subTotalTotal;
		this.totalDescuento = totalDescuento;
		this.estado = estado;
		this.usuarioId = usuarioId;
		this.medioPagoId = medioPagoId;
	}

	public Ventas(Integer ventasId, @NotNull int numeroComprobante,
			@NotNull @Size(min = 1, max = 2147483647) String comprobanteCompleto, Comprobante comprobanteId,
			@NotNull Date fecha, @NotNull Long montoTotal,
			@NotNull @Size(min = 1, max = 2147483647) String montoTotalLetras, @NotNull Long ivaCinco,
			@NotNull Long ivaDiez, @NotNull Long ivaTotal, @NotNull Long subTotalCinco, @NotNull Long subTotalDiez,
			@NotNull Long subTotalExenta, @NotNull Long subTotalTotal, @NotNull Long totalDescuento,
			@NotNull @Size(min = 1, max = 2147483647) String estado, ArqueoCaja arqueoId, Usuario usuarioId, MediosPago medioPagoId,
			Collection<VentasDetalle> ventasDetalleCollection, Collection<PlanPago> planPagoCollection) {
		super();
		this.ventasId = ventasId;
		this.numeroComprobante = numeroComprobante;
		this.comprobanteCompleto = comprobanteCompleto;
		this.comprobanteId = comprobanteId;
		this.fecha = fecha;
		this.montoTotal = montoTotal;
		this.montoTotalLetras = montoTotalLetras;
		this.ivaCinco = ivaCinco;
		this.ivaDiez = ivaDiez;
		this.ivaTotal = ivaTotal;
		this.subTotalCinco = subTotalCinco;
		this.subTotalDiez = subTotalDiez;
		this.subTotalExenta = subTotalExenta;
		this.subTotalTotal = subTotalTotal;
		this.totalDescuento = totalDescuento;
		this.estado = estado;
		this.arqueoId = arqueoId;
		this.usuarioId = usuarioId;
		this.medioPagoId = medioPagoId;
		this.ventasDetalleCollection = ventasDetalleCollection;
		this.planPagoCollection = planPagoCollection;
	}

	public Comprobante getComprobanteId() {
		return comprobanteId;
	}

	public void setComprobanteId(Comprobante comprobanteId) {
		this.comprobanteId = comprobanteId;
	}

	public ArqueoCaja getArqueoId() {
		return arqueoId;
	}

	public void setArqueoId(ArqueoCaja arqueoId) {
		this.arqueoId = arqueoId;
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

    public String getComprobanteCompleto() {
		return comprobanteCompleto;
	}

	public void setComprobanteCompleto(String comprobanteCompleto) {
		this.comprobanteCompleto = comprobanteCompleto;
	}

	public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Long montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getMontoTotalLetras() {
		return montoTotalLetras;
	}

	public void setMontoTotalLetras(String montoTotalLetras) {
		this.montoTotalLetras = montoTotalLetras;
	}

	public Long getIvaCinco() {
		return ivaCinco;
	}

	public void setIvaCinco(Long ivaCinco) {
		this.ivaCinco = ivaCinco;
	}

	public Long getIvaDiez() {
		return ivaDiez;
	}

	public void setIvaDiez(Long ivaDiez) {
		this.ivaDiez = ivaDiez;
	}

	public Long getIvaTotal() {
		return ivaTotal;
	}

	public void setIvaTotal(Long ivaTotal) {
		this.ivaTotal = ivaTotal;
	}

	public Long getSubTotalCinco() {
		return subTotalCinco;
	}

	public void setSubTotalCinco(Long subTotalCinco) {
		this.subTotalCinco = subTotalCinco;
	}

	public Long getSubTotalDiez() {
		return subTotalDiez;
	}

	public void setSubTotalDiez(Long subTotalDiez) {
		this.subTotalDiez = subTotalDiez;
	}

	public Long getSubTotalExenta() {
		return subTotalExenta;
	}

	public void setSubTotalExenta(Long subTotalExenta) {
		this.subTotalExenta = subTotalExenta;
	}

	public Long getSubTotalTotal() {
		return subTotalTotal;
	}

	public void setSubTotalTotal(Long subTotalTotal) {
		this.subTotalTotal = subTotalTotal;
	}

	public Long getTotalDescuento() {
		return totalDescuento;
	}

	public void setTotalDescuento(Long totalDescuento) {
		this.totalDescuento = totalDescuento;
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
		return "Ventas [ventasId=" + ventasId + ", numeroComprobante=" + numeroComprobante + ", comprobanteCompleto="
				+ comprobanteCompleto + ", comprobanteId=" + comprobanteId + ", fecha=" + fecha + ", montoTotal="
				+ montoTotal + ", montoTotalLetras=" + montoTotalLetras + ", ivaCinco=" + ivaCinco + ", ivaDiez="
				+ ivaDiez + ", ivaTotal=" + ivaTotal + ", subTotalCinco=" + subTotalCinco + ", subTotalDiez="
				+ subTotalDiez + ", subTotalExenta=" + subTotalExenta + ", subTotalTotal=" + subTotalTotal
				+ ", totalDescuento=" + totalDescuento + ", estado=" + estado + ", usuarioId=" + usuarioId
				+ ", medioPagoId=" + medioPagoId + ", ventasDetalleCollection=" + ventasDetalleCollection
				+ ", planPagoCollection=" + planPagoCollection + "]";
	}


    
}
