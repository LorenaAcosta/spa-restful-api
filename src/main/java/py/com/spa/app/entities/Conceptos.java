package py.com.spa.app.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
*
* @author Lore
*/
@Entity
@Table(name = "conceptos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conceptos.findAll", query = "SELECT c FROM Conceptos c")})
public class Conceptos implements Serializable {
	
	 private static final long serialVersionUID = 1L;
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Basic(optional = false)
	    @Column(name = "conceptos_id")
	    private Integer conceptosId;

	    @Basic(optional = false)
	    @NotNull
	    @Size(min = 1, max = 2147483647)
	    @Column(name = "descripcion", unique=true )
	    private String descripcion;
	    
	    @Column(name = "tipo" )
	    private String tipo;
	    
	    @Column(name = "valor" )
	    private Integer valor;
	   
	    
	    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "ventas")  
	    private Collection<VentasDetalle> ventasDetalleCollection;

		public Conceptos() {
			super();
		}



		public Integer getConceptosId() {
			return conceptosId;
		}

		public void setConceptosId(Integer conceptosId) {
			this.conceptosId = conceptosId;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}

		public Integer getValor() {
			return valor;
		}

		public void setValor(Integer valor) {
			this.valor = valor;
		}
	    
	    
	    

}
