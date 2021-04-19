package py.com.spa.app.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonFormat;

import py.com.spa.app.enumeraciones.EstadoComprobante;

/**
 *
 * @author Lore
 */
@Entity
@Table(name = "comprobante")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Comprobante.findAll", query = "SELECT i FROM Comprobante i"),
		@NamedQuery(name = "Comprobante.findByComprobanteId", query = "SELECT i FROM Comprobante i WHERE i.comprobanteId = :comprobanteId"),
		@NamedQuery(name = "Comprobante.findByTipoComprobanteId", query = "SELECT i FROM Comprobante i WHERE i.tipoComprobanteId = :tipoComprobanteId"),
		@NamedQuery(name = "Comprobante.findByTimbrado", query = "SELECT i FROM Comprobante i WHERE i.timbrado = :timbrado"),
		@NamedQuery(name = "Comprobante.findByInicioVigencia", query = "SELECT i FROM Comprobante i WHERE i.inicioVigencia = :inicioVigencia"),
		@NamedQuery(name = "Comprobante.findByfinVigencia", query = "SELECT i FROM Comprobante i WHERE i.finVigencia = :finVigencia"),
		@NamedQuery(name = "Comprobante.findByNumeroInicial", query = "SELECT i FROM Comprobante i WHERE i.numeroInicial = :numeroInicial"),
		@NamedQuery(name = "Comprobante.findByNumeroFinal", query = "SELECT i FROM Comprobante i WHERE i.numeroFinal = :numeroFinal"),
		@NamedQuery(name = "Comprobante.findByNumeroActual", query = "SELECT i FROM Comprobante i WHERE i.numeroActual = :numeroActual") })
public class Comprobante implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "comprobante_id")
	private Integer comprobanteId;
	/**/
	@JoinColumn(name = "tipo_comprobante_id", referencedColumnName = "tipo_comprobante_id")
	@ManyToOne(optional = false)
	private TipoComprobante tipoComprobanteId;
	/**/
	@Basic(optional = false)
	@NotNull
	@Size(min = 8, max = 8)
	@Column(name = "timbrado")
	private String timbrado;
	/**/
    @Basic(optional = false)
    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="America/Asuncion")
    @Column(name = "inicio_vigencia")
    private Date inicioVigencia;
    /**/
    @Basic(optional = false)
    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="America/Asuncion")
    @Column(name = "fin_vigencia")
    private Date finVigencia;
    /**/
	@Basic(optional = false)
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "numero_inicial")
	private int numeroInicial;
	/**/
	@Basic(optional = false)
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "numero_final")
	private int numeroFinal;
	/**/
	@Basic(optional = false)
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "numero_actual")
	@Value("${numero_actual:0}")
	private int numeroActual;
	/**/
	@JoinColumn(name = "punto_expedicion_id", referencedColumnName = "punto_expedicion_id")
	@ManyToOne(optional = false)
	private PuntoExpedicion puntoExpedicionId;
	/**/
    @Size(min = 1, max = 2147483647)
    @Column(name = "punto_expedicion_codigo" )
    private String puntoExpedicionCodigo;
    /**/
    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 8)
    private EstadoComprobante estado;
    /**/

	public Comprobante() {
	}

	public Comprobante(Integer comprobanteId) {
		this.comprobanteId = comprobanteId;
	}

	public Comprobante(Integer comprobanteId, TipoComprobante tipoComprobanteId,
			@NotNull @Size(min = 1, max = 8) String timbrado, @NotNull Date inicioVigencia, @NotNull Date finVigencia,
			@NotNull int numeroInicial, @NotNull int numeroFinal, @NotNull int numeroActual,
			PuntoExpedicion puntoExpedicionId, @Size(min = 1, max = 2147483647) String punto_expedicion_codigo,
			@NotNull EstadoComprobante estado) {
		super();
		this.comprobanteId = comprobanteId;
		this.tipoComprobanteId = tipoComprobanteId;
		this.timbrado = timbrado;
		this.inicioVigencia = inicioVigencia;
		this.finVigencia = finVigencia;
		this.numeroInicial = numeroInicial;
		this.numeroFinal = numeroFinal;
		this.numeroActual = numeroActual;
		this.puntoExpedicionId = puntoExpedicionId;
		this.puntoExpedicionCodigo = punto_expedicion_codigo;
		this.estado = estado;
	}

	public String getPuntoExpedicionCodigo() {
		return puntoExpedicionCodigo;
	}

	public void setPuntoExpedicionCodigo(String puntoExpedicionCodigo) {
		this.puntoExpedicionCodigo = puntoExpedicionCodigo;
	}

	public PuntoExpedicion getPuntoExpedicionId() {
		return puntoExpedicionId;
	}

	public void setPuntoExpedicionId(PuntoExpedicion puntoExpedicionId) {
		this.puntoExpedicionId = puntoExpedicionId;
	}

	public EstadoComprobante getEstado() {
		return estado;
	}

	public void setEstado(EstadoComprobante estado) {
		this.estado = estado;
	}

	public Date getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

	public Date getFinVigencia() {
		return finVigencia;
	}

	public void setFinVigencia(Date finVigencia) {
		this.finVigencia = finVigencia;
	}

	public Integer getComprobanteId() {
		return comprobanteId;
	}

	public void setComprobanteId(Integer comprobanteId) {
		this.comprobanteId = comprobanteId;
	}

	public TipoComprobante getTipoComprobanteId() {
		return tipoComprobanteId;
	}

	public void setTipoComprobanteId(TipoComprobante tipoComprobanteId) {
		this.tipoComprobanteId = tipoComprobanteId;
	}

	public String getTimbrado() {
		return timbrado;
	}

	public void setTimbrado(String timbrado) {
		this.timbrado = timbrado;
	}

	public int getNumeroInicial() {
		return numeroInicial;
	}

	public void setNumeroInicial(int numeroInicial) {
		this.numeroInicial = numeroInicial;
	}

	public int getNumeroFinal() {
		return numeroFinal;
	}

	public void setNumeroFinal(int numeroFinal) {
		this.numeroFinal = numeroFinal;
	}

	public int getNumeroActual() {
		return numeroActual;
	}

	public void setNumeroActual(int numeroActual) {
		this.numeroActual = numeroActual;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (comprobanteId != null ? comprobanteId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Comprobante)) {
			return false;
		}
		Comprobante other = (Comprobante) object;
		if ((this.comprobanteId == null && other.comprobanteId != null)
				|| (this.comprobanteId != null && !this.comprobanteId.equals(other.comprobanteId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.spa.Comprobante[ comprobanteId=" + comprobanteId + " ]";
	}

}