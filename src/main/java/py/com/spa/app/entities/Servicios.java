/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.spa.app.entities;

import java.io.Serializable;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import py.com.spa.app.enumeraciones.EstadoProducto;
import py.com.spa.app.enumeraciones.EstadoServicio;
import py.com.spa.app.enumeraciones.TipoCategoria;
import py.com.spa.result.SqlTimeDeserializer;

/**
 *
 * @author Lore
 */
@Entity
@Table(name = "servicios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicios.findAll", query = "SELECT s FROM Servicios s"),
    @NamedQuery(name = "Servicios.findByServicioId", query = "SELECT s FROM Servicios s WHERE s.servicioId = :servicioId"),
    @NamedQuery(name = "Servicios.findByNombre", query = "SELECT s FROM Servicios s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Servicios.findByEstado", query = "SELECT s FROM Servicios s WHERE s.estado = :estado"),
    @NamedQuery(name = "Servicios.findByDescripcion", query = "SELECT s FROM Servicios s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "Servicios.findByCosto", query = "SELECT s FROM Servicios s WHERE s.costo = :costo"),
    @NamedQuery(name = "Servicios.findByImageName", query = "SELECT s FROM Servicios s WHERE s.imageName = :imageName"),
    @NamedQuery(name = "Servicios.findByDuracion", query = "SELECT s FROM Servicios s WHERE s.duracion = :duracion")})
public class Servicios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "servicio_id")
    private Integer servicioId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre", unique=true)
    private String nombre;
    
    
    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 8)
    private EstadoServicio estado;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo")
    private int costo;
    @Size(max = 2147483647)
    @Column(name = "image_name")
    private String imageName;
    @Basic(optional = false)
    @NotNull
    @JsonFormat(pattern="HH:mm")
    @JsonDeserialize(using = SqlTimeDeserializer.class)
    @Column(name = "duracion")
    private Time  duracion;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "servicioId")
    private Collection<VentasDetalle> ventasDetalleCollection;
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "servicioId")
    private Collection<EmpleadoDisponible> empleadoDisponibleCollection;*/
    @JoinColumn(name = "categoria_id", referencedColumnName = "categoria_id")
    @ManyToOne(optional = false)
    private Categorias categoriaId;

    /**/
    @JoinColumn(name = "impuesto_id", referencedColumnName = "impuesto_id")
    @ManyToOne(optional = false)
    private Impuesto impuestoId;
    /**/

    @JsonBackReference(value="disponible")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicioId")
    private Collection<Disponible> disponibleCollection;
    
    @JsonBackReference(value="disponibleBoxes")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicioId")
    private Collection<Disponible> disponibleBoxesCollection;

    public Servicios() {
    }

    public Servicios(Integer servicioId) {
        this.servicioId = servicioId;
    }

    public Servicios(Integer servicioId, @NotNull @Size(min = 1, max = 2147483647) String nombre,
			@NotNull EstadoServicio estado, @NotNull @Size(min = 1, max = 2147483647) String descripcion,
			@NotNull int costo, @NotNull Time duracion) {
		super();
		this.servicioId = servicioId;
		this.nombre = nombre;
		this.estado = estado;
		this.descripcion = descripcion;
		this.costo = costo;
		this.duracion = duracion;
	}

	public Integer getServicioId() {
        return servicioId;
    }

    public void setServicioId(Integer servicioId) {
        this.servicioId = servicioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   

    public EstadoServicio getEstado() {
		return estado;
	}

	public void setEstado(EstadoServicio estado) {
		this.estado = estado;
	}

	public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Time getDuracion() {
        return duracion;
    }

    public void setDuracion(Time duracion) {
        this.duracion = duracion;
    }

    @JsonBackReference(value="ventas-detalle")
    @XmlTransient
    public Collection<VentasDetalle> getVentasDetalleCollection() {
        return ventasDetalleCollection;
    }

    public void setVentasDetalleCollection(Collection<VentasDetalle> ventasDetalleCollection) {
        this.ventasDetalleCollection = ventasDetalleCollection;
    }
    

   /* @JsonBackReference(value="empleado")
    @XmlTransient
    public Collection<EmpleadoDisponible> getEmpleadoDisponibleCollection() {
        return empleadoDisponibleCollection;
    }

    public void setEmpleadoDisponibleCollection(Collection<EmpleadoDisponible> empleadoDisponibleCollection) {
        this.empleadoDisponibleCollection = empleadoDisponibleCollection;
    }*/

    
    public Categorias getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Categorias categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Impuesto getImpuestoId() {
		return impuestoId;
	}

	public void setImpuestoId(Impuesto impuestoId) {
		this.impuestoId = impuestoId;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (servicioId != null ? servicioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicios)) {
            return false;
        }
        Servicios other = (Servicios) object;
        if ((this.servicioId == null && other.servicioId != null) || (this.servicioId != null && !this.servicioId.equals(other.servicioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spa.Servicios[ servicioId=" + servicioId + " ]";
    }
    
}
