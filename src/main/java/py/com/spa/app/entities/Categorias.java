/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.spa.app.entities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "categorias")
@XmlRootElement
@JsonIgnoreProperties("inspection")
@NamedQueries({
    @NamedQuery(name = "Categorias.findAll", query = "SELECT c FROM Categorias c"),
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
    @Column(name = "codigo")
    private String codigo;

    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;

    @Basic(optional = false)
    @Column(name = "data_type")
    private String dataType;

    @Column(name = "image_name")
    private String imageName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoriaId")
    private List<Servicios> serviciosList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoriaId")
    private List<Productos> productosList;

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

    //@JsonBackReference
    @XmlTransient
    public List<Servicios> getServiciosList() {
        return serviciosList;
    }

    public void setServiciosList(List<Servicios> serviciosList) {
        this.serviciosList = serviciosList;
    }

    @JsonBackReference(value="producto-list")
    @XmlTransient
    public List<Productos> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Productos> productosList) {
        this.productosList = productosList;
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
        return "entities.Categorias[ categoriaId=" + categoriaId + " ]";
    }

}
