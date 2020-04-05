/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.spa.app.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
    @NamedQuery(name = "Servicios.findByDescripcion", query = "SELECT s FROM Servicios s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "Servicios.findByFechaVigenciaIni", query = "SELECT s FROM Servicios s WHERE s.fechaVigenciaIni = :fechaVigenciaIni"),
    @NamedQuery(name = "Servicios.findByFechaVigenciaFin", query = "SELECT s FROM Servicios s WHERE s.fechaVigenciaFin = :fechaVigenciaFin"),
    @NamedQuery(name = "Servicios.findByEstado", query = "SELECT s FROM Servicios s WHERE s.estado = :estado"),
    @NamedQuery(name = "Servicios.findByDuracion", query = "SELECT s FROM Servicios s WHERE s.duracion = :duracion"),
    @NamedQuery(name = "Servicios.findByCosto", query = "SELECT s FROM Servicios s WHERE s.costo = :costo"),
    @NamedQuery(name = "Servicios.findByPorcComision", query = "SELECT s FROM Servicios s WHERE s.porcComision = :porcComision")})
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
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "fecha_vigencia_ini")
    private String fechaVigenciaIni;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "fecha_vigencia_fin")
    private String fechaVigenciaFin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "duracion")
    private String duracion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo")
    private int costo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "porc_comision")
    private BigInteger porcComision;
    @JoinColumn(name = "categoria_id", referencedColumnName = "categoria_id")
    @ManyToOne(optional = false)
    private Categorias categoriaId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicioId")
    private List<PagosDetalle> pagosDetalleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicioId")
    private List<Reservas> reservasList;

    public Servicios() {
    }

    public Servicios(Integer servicioId) {
        this.servicioId = servicioId;
    }

    public Servicios(Integer servicioId, String nombre, String descripcion, String fechaVigenciaIni, String fechaVigenciaFin, String estado, String duracion, int costo, BigInteger porcComision) {
        this.servicioId = servicioId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaVigenciaIni = fechaVigenciaIni;
        this.fechaVigenciaFin = fechaVigenciaFin;
        this.estado = estado;
        this.duracion = duracion;
        this.costo = costo;
        this.porcComision = porcComision;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaVigenciaIni() {
        return fechaVigenciaIni;
    }

    public void setFechaVigenciaIni(String fechaVigenciaIni) {
        this.fechaVigenciaIni = fechaVigenciaIni;
    }

    public String getFechaVigenciaFin() {
        return fechaVigenciaFin;
    }

    public void setFechaVigenciaFin(String fechaVigenciaFin) {
        this.fechaVigenciaFin = fechaVigenciaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public BigInteger getPorcComision() {
        return porcComision;
    }

    public void setPorcComision(BigInteger porcComision) {
        this.porcComision = porcComision;
    }

    public Categorias getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Categorias categoriaId) {
        this.categoriaId = categoriaId;
    }

    @XmlTransient
    public List<PagosDetalle> getPagosDetalleList() {
        return pagosDetalleList;
    }

    public void setPagosDetalleList(List<PagosDetalle> pagosDetalleList) {
        this.pagosDetalleList = pagosDetalleList;
    }

    @XmlTransient
    public List<Reservas> getReservasList() {
        return reservasList;
    }

    public void setReservasList(List<Reservas> reservasList) {
        this.reservasList = reservasList;
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
        return "com.spa.app.py.Servicios[ servicioId=" + servicioId + " ]";
    }
    
}
