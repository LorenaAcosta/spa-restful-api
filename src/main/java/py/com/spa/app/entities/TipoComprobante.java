package py.com.spa.app.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lore
 */
@Entity
@Table(name = "tipo_comprobante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoComprobante.findAll", query = "SELECT i FROM TipoComprobante i"),
    @NamedQuery(name = "TipoComprobante.findByTipoComprobanteId", query = "SELECT i FROM TipoComprobante i WHERE i.tipoComprobanteId = :tipoComprobanteId"),
    @NamedQuery(name = "TipoComprobante.findByDescripcion", query = "SELECT i FROM TipoComprobante i WHERE i.descripcion = :descripcion")})
public class TipoComprobante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_comprobante_id")
    private Integer tipoComprobanteId;
    /**/
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion", unique=true )
    private String descripcion;
    /**/
    
    
    public TipoComprobante() {
    }
    
    public TipoComprobante(Integer tipoComprobanteId) {
        this.tipoComprobanteId = tipoComprobanteId;
    }

    public TipoComprobante(Integer tipoComprobanteId, @NotNull @Size(min = 1, max = 2147483647) String descripcion) {
    	super();
    	this.tipoComprobanteId = tipoComprobanteId;
    	this.descripcion = descripcion;
    }
    
     
	public Integer getTipoComprobanteId() {
		return tipoComprobanteId;
	}

	public void setTipoComprobanteId(Integer tipoComprobanteId) {
		this.tipoComprobanteId = tipoComprobanteId;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoComprobanteId != null ? tipoComprobanteId.hashCode() : 0);
        return hash;
    }


    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoComprobante)) {
            return false;
        }
        TipoComprobante other = (TipoComprobante) object;
        if ((this.tipoComprobanteId == null && other.tipoComprobanteId != null) || (this.tipoComprobanteId != null && !this.tipoComprobanteId.equals(other.tipoComprobanteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spa.TipoComprobante[ tipoComprobanteId=" + tipoComprobanteId + " ]";
    }
    
}