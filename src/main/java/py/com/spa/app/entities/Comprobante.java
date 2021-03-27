package py.com.spa.app.entities;

import java.io.Serializable;
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

import py.com.spa.app.enumeraciones.EstadoComprobante;
import py.com.spa.app.enumeraciones.TipoCategoria;

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
	@Size(min = 1, max = 2147483647)
	@Column(name = "timbrado", unique = true)
	private String timbrado;
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
			@NotNull @Size(min = 1, max = 2147483647) String timbrado, @NotNull int numeroInicial,
			@NotNull int numeroFinal, @NotNull int numeroActual, @NotNull EstadoComprobante estado) {
		super();
		this.comprobanteId = comprobanteId;
		this.tipoComprobanteId = tipoComprobanteId;
		this.timbrado = timbrado;
		this.numeroInicial = numeroInicial;
		this.numeroFinal = numeroFinal;
		this.numeroActual = numeroActual;
		this.estado = estado;
	}

	public EstadoComprobante getEstado() {
		return estado;
	}

	public void setEstado(EstadoComprobante estado) {
		this.estado = estado;
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