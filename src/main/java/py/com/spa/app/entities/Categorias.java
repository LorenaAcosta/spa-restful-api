/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.spa.app.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

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
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 *
 * @author Lore
 */
@Entity
@Table(name = "categorias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categorias.findAll", query = "SELECT c FROM Categorias c"),
    @NamedQuery(name = "Categorias.obtenerPorTipo", query = "SELECT c FROM Categorias c WHERE c.dataType = :tipo"),
    @NamedQuery(name = "Categorias.findByCategoriaId", query = "SELECT c FROM Categorias c WHERE c.categoriaId = :categoriaId"),
    @NamedQuery(name = "Categorias.findByCodigo", query = "SELECT c FROM Categorias c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Categorias.findByDescripcion", query = "SELECT c FROM Categorias c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Categorias.findByDataType", query = "SELECT c FROM Categorias c WHERE c.dataType = :dataType"),
    @NamedQuery(name = "Categorias.findByImageName", query = "SELECT c FROM Categorias c WHERE c.imageName = :imageName")})
public class Categorias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "categoria_id")
    private Integer categoriaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "data_type")
    private String dataType;
    @Size(max = 2147483647)
    @Column(name = "image_name")
    private String imageName;
    
    /**/
     //private String imagen;
     /**/
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoriaId")
    private Collection<Servicios> serviciosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoriaId")
    private Collection<Productos> productosCollection;

    public Categorias() {
    }

    public Categorias(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Categorias(Integer categoriaId, String codigo, String descripcion, String dataType) {
        this.categoriaId = categoriaId;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.dataType = dataType;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
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

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /*public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}*/

	@JsonBackReference(value="servicios-cat")
    @XmlTransient
    public Collection<Servicios> getServiciosCollection() {
        return serviciosCollection;
    }

    public void setServiciosCollection(Collection<Servicios> serviciosCollection) {
        this.serviciosCollection = serviciosCollection;
    }

    @JsonBackReference(value="productos-cat")
    @XmlTransient
    public Collection<Productos> getProductosCollection() {
        return productosCollection;
    }

    public void setProductosCollection(Collection<Productos> productosCollection) {
        this.productosCollection = productosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoriaId != null ? categoriaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorias)) {
            return false;
        }
        Categorias other = (Categorias) object;
        if ((this.categoriaId == null && other.categoriaId != null) || (this.categoriaId != null && !this.categoriaId.equals(other.categoriaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
    	return "com.spa.entities.Categorias[ categoriaId=" + categoriaId + " ]";
    }
    
}
