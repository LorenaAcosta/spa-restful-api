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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonBackReference;

import py.com.spa.app.enumeraciones.TipoCategoria;
import py.com.spa.app.util.FileModel;

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
    //@NamedQuery(name = "Categorias.findByCodigo", query = "SELECT c FROM Categorias c WHERE c.codigo = :codigo"),
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
    @Column(name = "descripcion", unique=true )
    private String descripcion;

    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 8)
    private TipoCategoria dataType;
    
    
    @Size(max = 2147483647)
    @Column(name = "image_name")
    private String imageName;
       
    
    @OneToMany( mappedBy = "categoriaId", cascade = CascadeType.PERSIST)
    private Collection<Servicios> serviciosCollection;
    
    @OneToMany( mappedBy = "categoriaId", cascade = CascadeType.PERSIST)
    private Collection<Productos> productosCollection;
    
    

	public Categorias() {
    }

    public Categorias(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Categorias(Integer categoriaId, String descripcion, TipoCategoria dataType) {
        this.categoriaId = categoriaId;
        this.descripcion = descripcion;
        this.dataType = dataType;
    }

    public Categorias(Integer categoriaId,
			@NotNull @Size(min = 1, max = 2147483647) String descripcion, @NotNull TipoCategoria dataType,
			@Size(max = 2147483647) String imageName, Collection<Servicios> serviciosCollection,
			Collection<Productos> productosCollection) {
		super();
		this.categoriaId = categoriaId;
		this.descripcion = descripcion;
		this.dataType = dataType;
		this.imageName = imageName;
		this.serviciosCollection = serviciosCollection;
		this.productosCollection = productosCollection;
	}

    @JsonBackReference(value="image_name")
	public String getImage_name() {
		return imageName;
	}
    
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	@JsonBackReference(value="categoria_id")
    public Integer getCategoria_id() {
		return categoriaId;
	}

    public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @JsonBackReference(value="datatype_id")
    public String getData_type() {
        return dataType.toString();
    }
    
    public TipoCategoria getDataType() {
        return dataType;
    }

    public void setDataType(TipoCategoria dataType) {
        this.dataType = dataType;
    }


	@JsonBackReference(value="servicios")
    @XmlTransient
    public Collection<Servicios> getServiciosCollection() {
        return serviciosCollection;
    }

    public void setServiciosCollection(Collection<Servicios> serviciosCollection) {
        this.serviciosCollection = serviciosCollection;
    }

    @JsonBackReference(value="productos")
    @XmlTransient
    public Collection<Productos> getProductosCollection() {
        return productosCollection;
    }

    public void setProductosCollection(Collection<Productos> productosCollection) {
        this.productosCollection = productosCollection;
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
        return "com.spa.Categorias[ categoriaId=" + categoriaId + " ]";
    }
    
}
