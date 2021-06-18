/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 *
 * @author Lore
 */
@Entity
@Table(name = "proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"),
    @NamedQuery(name = "Proveedor.findByProveedorId", query = "SELECT p FROM Proveedor p WHERE p.proveedorId = :proveedorId"),
    @NamedQuery(name = "Proveedor.findByNombreProveedor", query = "SELECT p FROM Proveedor p WHERE p.nombreProveedor = :nombreProveedor"),
    @NamedQuery(name = "Proveedor.findByTelefono", query = "SELECT p FROM Proveedor p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "Proveedor.findByEmpresa", query = "SELECT p FROM Proveedor p WHERE p.empresa = :empresa")})
public class Proveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "proveedor_id")
    private Integer proveedorId;
    @NotNull
    @Column(name = "razon_social", unique=true) 
    private String razonSocial;
    @NotNull
    @Column(name = "empresa", unique=true) 
    private String empresa;
    @NotNull
    @Column(name = "ruc", unique=true) 
    private String ruc;
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "direccion")
    private String direccion;
    @NotNull
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "rubro")
    private String rubro;
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @Column(name = "telefono")
    private int telefono;
    @Column(name = "nombre_gerente")
    private String nombreGerente;
    @Basic(optional = true)
    @Column(name = "nombre_proveedor") 
    private String nombreProveedor;
    @Column(name = "cargo") 
    private String cargo;
    @Column(name = "telefono_contacto") 
    private int telefonoContacto;
    @Column(name = "estado") 
    private String estado;
    @JsonBackReference
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "proveedorId")
    private Collection<Compras> comprasCollection;

    public Proveedor() {
    }
    
    
    public Proveedor( String razonSocial, int telefono, String empresa, String direccion, String ciudad) {
        this.razonSocial = razonSocial;
        this.telefono = telefono;
        this.empresa = empresa;
        this.direccion = direccion;
        this.ciudad=  ciudad;
    }


	public Proveedor(Integer proveedorId, String razonSocial, String empresa, String ruc,
			@Size(min = 1, max = 2147483647) String direccion, String ciudad, String rubro, String correo,
			@NotNull int telefono, String nombreGerente, @NotNull @Size(min = 1, max = 2147483647) String nombreProveedor,
			String cargo, int telefonoContacto, String estado) {
		super();
		this.proveedorId = proveedorId;
		this.razonSocial = razonSocial;
		this.empresa = empresa;
		this.ruc = ruc;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.rubro = rubro;
		this.correo = correo;
		this.telefono = telefono;
		this.nombreGerente = nombreGerente;
		this.nombreProveedor = nombreProveedor;
		this.cargo = cargo;
		this.telefonoContacto = telefonoContacto;
		this.estado = estado;
	}
	

    public Proveedor(Integer proveedorId) {
        this.proveedorId = proveedorId;
    }

	
	public Integer getProveedorId() {
		return proveedorId;
	}




	public void setProveedorId(Integer proveedorId) {
		this.proveedorId = proveedorId;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
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

	public String getRubro() {
		return rubro;
	}

	public void setRubro(String rubro) {
		this.rubro = rubro;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getNombreGerente() {
		return nombreGerente;
	}

	public void setNombreGerente(String nombreGerente) {
		this.nombreGerente = nombreGerente;
	}

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public int getTelefonoContacto() {
		return telefonoContacto;
	}

	public void setTelefonoContacto(int telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@XmlTransient
    public Collection<Compras> getComprasCollection() {
        return comprasCollection;
    }

    public void setComprasCollection(Collection<Compras> comprasCollection) {
        this.comprasCollection = comprasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proveedorId != null ? proveedorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.proveedorId == null && other.proveedorId != null) || (this.proveedorId != null && !this.proveedorId.equals(other.proveedorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.spa.app.entities.Proveedor[ proveedorId=" + proveedorId + " ]";
    }
    
}
