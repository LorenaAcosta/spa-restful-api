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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author Lore
 */
@Entity
@Table(name = "usuario")
@JsonIgnoreProperties("inspection")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByUsuarioId", query = "SELECT u FROM Usuario u WHERE u.usuarioId = :usuarioId"),
    @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Usuario.findByUsername", query = "SELECT u FROM Usuario u WHERE u.username = :username"),
    @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password"),
    @NamedQuery(name = "Usuario.findByApellido", query = "SELECT u FROM Usuario u WHERE u.apellido = :apellido"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
    @NamedQuery(name = "Usuario.findByCedula", query = "SELECT u FROM Usuario u WHERE u.cedula = :cedula"),
    @NamedQuery(name = "Usuario.findByTelefono", query = "SELECT u FROM Usuario u WHERE u.telefono = :telefono"),
    @NamedQuery(name = "Usuario.findBySexo", query = "SELECT u FROM Usuario u WHERE u.sexo = :sexo"),
    @NamedQuery(name = "Usuario.findByEstado", query = "SELECT u FROM Usuario u WHERE u.estado = :estado")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usuario_id")
    private Integer usuarioId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "apellido")
    private String apellido;
    /**/
	@Column(unique = true, length = 20)
	private String username;
	/**/
	@Column(length = 60)
	private String password;
    /**/
	@Column(unique = true, length = 100)
	@Email
	private String email;
	/**/
	private Boolean enabled = true;
	/**/
    @Basic(optional = false)
    @NotNull
    @Column(name = "cedula")
    private int cedula;
  
    @Size(min = 1, max = 2147483647)
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "nacionalidad")
    private String nacionalidad;
    
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "sexo")
    private String sexo;
    
    @Column(name = "ruc")
    private String ruc;
    @Column(name = "tarjeta")
    private String tarjeta;
    @Column(name = "fecha_nac")
    private String fechaNac;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    /**/
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="usuarios_roles", joinColumns = @JoinColumn(name="usuario_id"),
	inverseJoinColumns = @JoinColumn(name="rol_id"),
	uniqueConstraints = { @UniqueConstraint (columnNames = {"usuario_id", "rol_id"})})
	private List <Rol> roles;
	/**/
	@JsonBackReference(value="ventas-usuario")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioId")
    private Collection<Ventas> ventasCollection;
    /**/
    @JsonBackReference(value="reservas-usuario")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioId")
    private Collection<ReservaDetalle> reservaDetalleCollection;
    /**/

    public Usuario() {
    }

    public Usuario(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

	public Usuario(Integer usuarioId, @NotNull String nombre, @NotNull @Size(min = 1, max = 2147483647) String apellido,
			String username, String password, @Email String email, Boolean enabled, @NotNull int cedula,
			@Size(min = 1, max = 2147483647) String direccion, String ciudad, String nacionalidad,
			@NotNull @Size(min = 1, max = 2147483647) String telefono,
			@NotNull @Size(min = 1, max = 2147483647) String sexo, String ruc, String tarjeta, String fechaNac,
			@NotNull int estado, List<Rol> roles) {
		super();
		this.usuarioId = usuarioId;
		this.nombre = nombre;
		this.apellido = apellido;
		this.username = username;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
		this.cedula = cedula;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.sexo = sexo;
		this.ruc = ruc;
		this.tarjeta = tarjeta;
		this.fechaNac = fechaNac;
		this.estado = estado;
		this.roles = roles;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public Collection<Ventas> getVentasCollection() {
		return ventasCollection;
	}

	public void setVentasCollection(Collection<Ventas> ventasCollection) {
		this.ventasCollection = ventasCollection;
	}

	public Collection<ReservaDetalle> getReservaDetalleCollection() {
		return reservaDetalleCollection;
	}

	public void setReservaDetalleCollection(Collection<ReservaDetalle> reservaDetalleCollection) {
		this.reservaDetalleCollection = reservaDetalleCollection;
	}




}
