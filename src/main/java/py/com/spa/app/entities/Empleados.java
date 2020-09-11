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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "empleados")
@XmlRootElement
@JsonIgnoreProperties("inspection")
@NamedQueries({
    @NamedQuery(name = "Empleados.findAll", query = "SELECT e FROM Empleados e"),
    @NamedQuery(name = "Empleados.findByEmpleadoId", query = "SELECT e FROM Empleados e WHERE e.empleadoId = :empleadoId"),
    @NamedQuery(name = "Empleados.findByCedula", query = "SELECT e FROM Empleados e WHERE e.cedula = :cedula"),
    @NamedQuery(name = "Empleados.findByNombre", query = "SELECT e FROM Empleados e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Empleados.findByApellido", query = "SELECT e FROM Empleados e WHERE e.apellido = :apellido"),
    @NamedQuery(name = "Empleados.findByDireccion", query = "SELECT e FROM Empleados e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "Empleados.findByTelefono", query = "SELECT e FROM Empleados e WHERE e.telefono = :telefono"),
    @NamedQuery(name = "Empleados.findByFechaNac", query = "SELECT e FROM Empleados e WHERE e.fechaNac = :fechaNac"),
    @NamedQuery(name = "Empleados.findByImageName", query = "SELECT e FROM Empleados e WHERE e.imageName = :imageName")})
public class Empleados implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "empleado_id")
    private Integer empleadoId;

    @Basic(optional = false)
    @Column(name = "cedula")
    private int cedula;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "apellido")
    private String apellido;

    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;

    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;

    @Basic(optional = false)
    @Column(name = "fecha_nac")
    private String fechaNac;

    @Column(name = "image_name")
    private String imageName;
    
    
    // relaciones
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="servicios_empleados", joinColumns = @JoinColumn(name="empleado_id"),
	inverseJoinColumns = @JoinColumn(name="servicio_id"),
	uniqueConstraints = { @UniqueConstraint (columnNames = {"empleado_id", "servicio_id"})})
	private List <Servicios> serviciosList;
	
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planillaId")
    private List<Planilla> planillaList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservaId")
    private List<ReservaDetalle> reservaDetalleList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "horarioId")
    private List<Horario> horarioList;
    

    public Empleados() {
    }

    public Empleados(Integer empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Empleados(Integer empleadoId, int cedula, String nombre, String apellido, String direccion, String telefono, String fechaNac) {
        this.empleadoId = empleadoId;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaNac = fechaNac;
    }

    // Getters and setters relaciones
    @JsonBackReference
	public List<Servicios> getServiciosList() {
		return serviciosList;
	}

	public void setServiciosList(List<Servicios> serviciosList) {
		this.serviciosList = serviciosList;
	}

	public List<Planilla> getPlanillaList() {
		return planillaList;
	}

	public void setPlanillaList(List<Planilla> planillaList) {
		this.planillaList = planillaList;
	}

	public List<ReservaDetalle> getReservaDetalleList() {
		return reservaDetalleList;
	}

	public void setReservaDetalleList(List<ReservaDetalle> reservaDetalleList) {
		this.reservaDetalleList = reservaDetalleList;
	}

	public List<Horario> getHorarioList() {
		return horarioList;
	}

	public void setHorarioList(List<Horario> horarioList) {
		this.horarioList = horarioList;
	}

	public void setEmpleadoId(Integer empleadoId) {
        this.empleadoId = empleadoId;
    }
	
    public Integer getEmpleadoId() {
        return empleadoId;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empleadoId != null ? empleadoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleados)) {
            return false;
        }
        Empleados other = (Empleados) object;
        if ((this.empleadoId == null && other.empleadoId != null) || (this.empleadoId != null && !this.empleadoId.equals(other.empleadoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Empleados[ empleadoId=" + empleadoId + " ]";
    }

}
