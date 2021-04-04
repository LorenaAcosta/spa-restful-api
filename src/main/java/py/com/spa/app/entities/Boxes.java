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
@Table(name = "boxes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Boxes.findAll", query = "SELECT s FROM Servicios s"),
    @NamedQuery(name = "Boxes.findByBoxesId", query = "SELECT s FROM Boxes s WHERE s.boxesId = :boxesId"),
    @NamedQuery(name = "Boxes.findByNombre", query = "SELECT s FROM Boxes s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Boxes.findByEstado", query = "SELECT s FROM Boxes s WHERE s.estado = :estado")})
public class Boxes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "boxes_id")
    private Integer boxesId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre", unique=true)
    private String nombre;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 8)
    private EstadoBoxes estado;

   

    public Boxes(Integer boxesId) {
		super();
		this.boxesId = boxesId;
	}


	public Boxes() {
    }


	public Boxes(Integer boxesId, @NotNull @Size(min = 1, max = 2147483647) String nombre,
			@NotNull EstadoBoxes estado) {
		super();
		this.boxesId = boxesId;
		this.nombre = nombre;
		this.estado = estado;
	}



	public Integer getBoxesId() {
		return boxesId;
	}



	public void setBoxesId(Integer boxesId) {
		this.boxesId = boxesId;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public EstadoBoxes getEstado() {
		return estado;
	}



	public void setEstado(EstadoBoxes estado) {
		this.estado = estado;
	}


	@Override
	public String toString() {
		return "Boxes [boxesId=" + boxesId + ", nombre=" + nombre + ", estado=" + estado + "]";
	}



 
    
}
