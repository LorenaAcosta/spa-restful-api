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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joel
 */
@Entity
@Table(name = "punto_expedicion", uniqueConstraints=@UniqueConstraint(columnNames={"usuario_id"}))
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PuntoExpedicion.findAll", query = "SELECT i FROM PuntoExpedicion i"),
    @NamedQuery(name = "PuntoExpedicion.findByPuntoExpedicionId", query = "SELECT i FROM PuntoExpedicion i WHERE i.puntoExpedicionId = :puntoExpecionId"),
    @NamedQuery(name = "PuntoExpedicion.findByDescripcion", query = "SELECT i FROM PuntoExpedicion i WHERE i.descripcion = :descripcion")})
public class PuntoExpedicion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "punto_expedicion_id")
    private Integer puntoExpedicionId;
    /**/
    @Size(min = 1, max = 2147483647)
    @Column(name = "codigo", unique=true )
    private String codigo;
    /**/
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion", unique=true )
    private String descripcion;
    /**/
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    @ManyToOne(optional = true)
    private Usuario usuarioId;
    
    public PuntoExpedicion(Integer puntoExpedicionId, @NotNull @Size(min = 1, max = 2147483647) String codigo,
			@NotNull @Size(min = 1, max = 2147483647) String descripcion, Usuario usuarioId) {
		super();
		this.puntoExpedicionId = puntoExpedicionId;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.usuarioId = usuarioId;
	}
   
	public PuntoExpedicion(Integer puntoExpedicionId) {
		this.puntoExpedicionId = puntoExpedicionId;
	}

	public PuntoExpedicion() {
	}

	public Integer getPuntoExpedicionId() {
		return puntoExpedicionId;
	}

	public void setPuntoExpedicionId(Integer puntoExpedicionId) {
		this.puntoExpedicionId = puntoExpedicionId;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Usuario getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Usuario usuarioId) {
		this.usuarioId = usuarioId;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (puntoExpedicionId != null ? puntoExpedicionId.hashCode() : 0);
        return hash;
    }

	@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PuntoExpedicion)) {
            return false;
        }
        PuntoExpedicion other = (PuntoExpedicion) object;
        if ((this.puntoExpedicionId == null && other.puntoExpedicionId != null) || (this.puntoExpedicionId != null && !this.puntoExpedicionId.equals(other.puntoExpedicionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spa.TipoComprobante[ tipoComprobanteId=" + puntoExpedicionId + " ]";
    }
    
}