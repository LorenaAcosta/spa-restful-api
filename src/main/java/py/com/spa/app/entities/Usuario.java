package py.com.spa.app.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "usuarios")
@JsonIgnoreProperties("inspection")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true, length = 20)
	private String username;
	
	//@NotEmpty
	@Column(length = 60)
	private String password;
	
	@Column(unique = true, length = 100)
	@Email
	private String email;

	private String nombre;
	private String apellido;
	private Boolean enabled = true;
	
	@Column(unique = true, length = 20)
	private String ruc;
	
	@Column(unique = true, length = 20)
	private String telefono;
	
	@Column(unique = true, length = 20)
	private String sexo;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="usuarios_roles", joinColumns = @JoinColumn(name="usuario_id"),
	inverseJoinColumns = @JoinColumn(name="rol_id"),
	uniqueConstraints = { @UniqueConstraint (columnNames = {"usuario_id", "rol_id"})})
	private List <Rol> roles;
	
	// relaciones
	@OneToMany()
	@JoinTable(name="pagos", joinColumns = @JoinColumn(name="usuario_id"),
	inverseJoinColumns = @JoinColumn(name="pago_id"))
	private List <Pagos> pagosList;
	
	@OneToMany()
	@JoinTable(name="reserva", joinColumns = @JoinColumn(name="usuario_id"),
	inverseJoinColumns = @JoinColumn(name="reserva_id"))
	private List <Reserva> reservaList;
	

	public List<Rol> getRoles() {
		return roles;
	}

	public List<Pagos> getPagosList() {
		return pagosList;
	}

	public void setPagosList(List<Pagos> pagosList) {
		this.pagosList = pagosList;
	}

	public List<Reserva> getReservaList() {
		return reservaList;
	}

	public void setReservaList(List<Reserva> reservaList) {
		this.reservaList = reservaList;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private static final long serialVersionUID = 5220164949789046285L;

}
