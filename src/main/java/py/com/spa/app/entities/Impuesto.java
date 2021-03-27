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
@Table(name = "impuesto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Impuesto.findAll", query = "SELECT i FROM Impuesto i"),
    @NamedQuery(name = "Impuesto.findByImpuestoId", query = "SELECT i FROM Impuesto i WHERE i.impuestoId = :impuestoId"),
    @NamedQuery(name = "Impuesto.findByDescripcion", query = "SELECT i FROM Impuesto i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "Impuesto.findByValor", query = "SELECT i FROM Impuesto i WHERE i.valor = :valor")})
public class Impuesto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "impuesto_id")
    private Integer impuestoId;
    /**/
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion", unique=true )
    private String descripcion;
    /**/
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private int valor;
    
    
    public Impuesto() {
    }
    
    public Impuesto(Integer impuestoId) {
        this.impuestoId = impuestoId;
    }

    public Impuesto(Integer impuestoId, @NotNull @Size(min = 1, max = 2147483647) String descripcion, @NotNull int valor) {
    	super();
    	this.impuestoId = impuestoId;
    	this.descripcion = descripcion;
    	this.valor = valor;
    }
    
     
	public Integer getImpuestoId() {
		return impuestoId;
	}

	public void setImpuestoId(Integer impuestoId) {
		this.impuestoId = impuestoId;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (impuestoId != null ? impuestoId.hashCode() : 0);
        return hash;
    }


    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Impuesto)) {
            return false;
        }
        Impuesto other = (Impuesto) object;
        if ((this.impuestoId == null && other.impuestoId != null) || (this.impuestoId != null && !this.impuestoId.equals(other.impuestoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spa.Impuesto[ impuestoId=" + impuestoId + " ]";
    }
    
}