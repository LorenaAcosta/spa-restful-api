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
@Table(name = "carrito_cabecera")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CarritoCabecera.findAll", query = "SELECT s FROM Servicios s"),
    @NamedQuery(name = "CarritoCabecera.findByBoxesId", query = "SELECT s FROM Boxes s WHERE s.boxesId = :boxesId"),
    @NamedQuery(name = "CarritoCabecera.findByNombre", query = "SELECT s FROM Boxes s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "CarritoCabecera.findByEstado", query = "SELECT s FROM Boxes s WHERE s.estado = :estado")})
public class CarritoCabecera implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "carrito_cabecera_id")
    private Integer carritoCabeceraId;
    @Column(name = "orden")
    private Integer orden;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE ) 
    private Date fecha;
    @Column(name = "total")
    private Integer total;
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    @ManyToOne(optional = false)
    private Usuario usuarioId;
    
    public CarritoCabecera() {
    	
    }

   

	public CarritoCabecera(Integer carritoCabeceraId, Integer orden, Date fecha, Integer total, Usuario usuarioId) {
		super();
		this.carritoCabeceraId = carritoCabeceraId;
		this.orden = orden;
		this.fecha = fecha;
		this.total = total;
		this.usuarioId = usuarioId;
	}


	public Usuario getUsuarioId() {
		return usuarioId;
	}



	public void setUsuarioId(Usuario usuarioId) {
		this.usuarioId = usuarioId;
	}





	public Integer getCarritoCabeceraId() {
		return carritoCabeceraId;
	}

	public void setCarritoCabeceraId(Integer carritoCabeceraId) {
		this.carritoCabeceraId = carritoCabeceraId;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
	
    
    

    
}
