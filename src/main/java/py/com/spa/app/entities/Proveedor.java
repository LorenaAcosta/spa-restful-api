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
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "empresa")
    private String empresa;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre_proveedor")
    private String nombreProveedor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "telefono")
    private String telefono;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "direccion")
    private String direccion;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "correo")
    private String correo;

    @Basic(optional = false)
    @NotNull
    @Column(name = "ruc")
    private String ruc;

    @Column(nullable = true)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedorId")
    private Collection<Compras> comprasCollection;

    public Proveedor() {
    }

    public Proveedor(Integer proveedorId) {
        this.proveedorId = proveedorId;
    }


    public Proveedor(Integer proveedorId, @NotNull @Size(min = 1, max = 2147483647) String empresa,
			@NotNull @Size(min = 1, max = 2147483647) String nombreProveedor, @NotNull String telefono,
			@NotNull String direccion, @NotNull String correo, @NotNull String ruc,
			Collection<Compras> comprasCollection) {
		super();
		this.proveedorId = proveedorId;
		this.empresa = empresa;
		this.nombreProveedor = nombreProveedor;
		this.telefono = telefono;
		this.direccion = direccion;
		this.correo = correo;
		this.ruc = ruc;
		this.comprasCollection = comprasCollection;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public Integer getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Integer proveedorId) {
        this.proveedorId = proveedorId;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    @JsonBackReference(value="compras-proveedor")
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
