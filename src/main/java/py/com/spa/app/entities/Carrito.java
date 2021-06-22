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

import py.com.spa.app.enumeraciones.EstadoBoxes;
import py.com.spa.app.enumeraciones.EstadoProducto;
import py.com.spa.app.enumeraciones.EstadoServicio;
import py.com.spa.result.SqlTimeDeserializer;

/**
 *
 * @author Lore
 */
@Entity
@Table(name = "carrito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carrito.findAll", query = "SELECT s FROM Servicios s"),
    @NamedQuery(name = "Carrito.findByBoxesId", query = "SELECT s FROM Boxes s WHERE s.boxesId = :boxesId"),
    @NamedQuery(name = "Carrito.findByNombre", query = "SELECT s FROM Boxes s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Carrito.findByEstado", query = "SELECT s FROM Boxes s WHERE s.estado = :estado")})
public class Carrito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "carrito_id")
    private Integer carritoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "producto_id", unique=true)
    private Integer productoId;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "cant")
    private Integer cant;
    @Column(name = "subtotal")
    private Integer subtotal;
    @Column(name = "usuario_id")
    private Integer usuarioId;
    
    
    
    
	public Carrito(Integer carritoId, @NotNull Integer productoId, String nombre, Integer cant, Integer subtotal,
			Integer usuarioId) {
		super();
		this.carritoId = carritoId;
		this.productoId = productoId;
		this.nombre = nombre;
		this.cant = cant;
		this.subtotal = subtotal;
		this.usuarioId = usuarioId;
	}
	public Integer getCarritoId() {
		return carritoId;
	}
	public void setCarritoId(Integer carritoId) {
		this.carritoId = carritoId;
	}
	public Integer getProductoId() {
		return productoId;
	}
	public void setProductoId(Integer productoId) {
		this.productoId = productoId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getCant() {
		return cant;
	}
	public void setCant(Integer cant) {
		this.cant = cant;
	}
	public Integer getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Integer subtotal) {
		this.subtotal = subtotal;
	}
	public Integer getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
 

 
    
}
