/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.spa.app.entities;

import java.io.Serializable;
import java.sql.Time;
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
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import py.com.spa.result.SqlTimeDeserializer;

/**
 *
 * @author Lore
 */
@Entity
@Table(name = "empleados")
@XmlRootElement
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "empleado_id")
    private Integer empleadoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cedula", unique=true)
    private String cedula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "fecha_nac")
    private String fechaNac;
    @Size(max = 2147483647)
    @Column(name = "image_name")
    private String imageName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "correo")
    private String correo;
    @Column(name = "estado_civil")
    private String estadoCivil;
    @Column(name = "estado")
    private String estado;
    @Column(name = "fecha_ingreso")
    private String fechaIngreso;
    @Column(name = "fecha_salida")
    private String fechaSalida;
    @Column(name = "celular")
    private String celular;
    @Column(name = "funcion")
    private String funcion;
    @Column(name = "sueldo")
    private Integer sueldo;
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "nacionalidad")
    private String nacionalidad;
    
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "empleadoId")
    private Collection<Horario> horarioCollection;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "empleadoId")
    private Collection<Planilla> planillaCollection;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "empleadoId")
    private Collection<Disponible> disponibleCollection;

    public Empleados() {
    }

    public Empleados(Integer empleadoId) {
        this.empleadoId = empleadoId;
    }






	public Empleados(Integer empleadoId, @NotNull String cedula, @NotNull @Size(min = 1, max = 2147483647) String nombre,
			@NotNull @Size(min = 1, max = 2147483647) String apellido,
			@NotNull @Size(min = 1, max = 2147483647) String direccion,
			@NotNull @Size(min = 1, max = 2147483647) String telefono,
			@NotNull @Size(min = 1, max = 2147483647) String fechaNac, @Size(max = 2147483647) String imageName,
			@NotNull @Size(min = 1, max = 2147483647) String correo, String estadoCivil, String estado,
			String fechaIngreso, String celular, String funcion, Integer sueldo, String ciudad, String nacionalidad) {
		super();
		this.empleadoId = empleadoId;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.telefono = telefono;
		this.fechaNac = fechaNac;
		this.imageName = imageName;
		this.correo = correo;
		this.estadoCivil = estadoCivil;
		this.estado = estado;
		this.fechaIngreso = fechaIngreso;
		this.celular = celular;
		this.funcion = funcion;
		this.sueldo = sueldo;
		this.ciudad = ciudad;
		this.nacionalidad = nacionalidad;
	}

	public Integer getEmpleadoId() {
		return empleadoId;
	}

	public void setEmpleadoId(Integer empleadoId) {
		this.empleadoId = empleadoId;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getFecha_salida() {
		return fechaSalida;
	}

	public void setFecha_salida(String fecha_salida) {
		this.fechaSalida = fecha_salida;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public Integer getSueldo() {
		return sueldo;
	}

	public void setSueldo(Integer sueldo) {
		this.sueldo = sueldo;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	@JsonBackReference(value="planilla-empleado")
    @XmlTransient
    public Collection<Planilla> getPlanillaCollection() {
        return planillaCollection;
    }

    public void setPlanillaCollection(Collection<Planilla> planillaCollection) {
        this.planillaCollection = planillaCollection;
    }

    @JsonBackReference(value="disponible-empleado")
    @XmlTransient
    public Collection<Disponible> getDisponibleCollection() {
        return disponibleCollection;
    }

    public void setDisponibleCollection(Collection<Disponible> disponibleCollection) {
        this.disponibleCollection = disponibleCollection;
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
        return "py.com.spa.app.Empleados[ empleadoId=" + empleadoId + " ]";
    }
    
}
