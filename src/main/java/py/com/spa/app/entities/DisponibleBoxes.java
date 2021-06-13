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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 *
 * @author Lore
 */
@Entity
@Table(name = "disponible_boxes", uniqueConstraints=@UniqueConstraint(columnNames={"boxes_id", "servicio_id"}))
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DisponibleBoxes.findAll", query = "SELECT d FROM DisponibleBoxes d"),
    @NamedQuery(name = "DisponibleBoxes.findByDisponibleBoxesId", query = "SELECT d FROM DisponibleBoxes d WHERE d.disponibleBoxesId = :disponibleBoxesId")})
public class DisponibleBoxes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "disponible_boxes_id")
    private Integer disponibleBoxesId;
    
    
    @JoinColumn(name = "servicio_id", referencedColumnName = "servicio_id")
    @ManyToOne(optional = false)
    private Servicios servicioId;
    
    @JoinColumn(name = "boxes_id", referencedColumnName = "boxes_id")
    @ManyToOne(optional = false)
    private Boxes boxesId;
    

    @JsonBackReference(value="dispo-reserva")
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "disponibleBoxesId")
    private Collection<ReservaDetalle> reservaDetalleCollection;
    
    
    
	public DisponibleBoxes() {
		super();
	}
	
	
	
	public DisponibleBoxes(Integer disponibleBoxesId) {
		super();
		this.disponibleBoxesId = disponibleBoxesId;
	}



	public DisponibleBoxes(Integer disponibleBoxesId, Servicios servicioId, Boxes boxesId) {
		super();
		this.disponibleBoxesId = disponibleBoxesId;
		this.servicioId = servicioId;
		this.boxesId = boxesId;
	}
	
	
	public Integer getDisponibleBoxesId() {
		return disponibleBoxesId;
	}
	public void setDisponibleBoxesId(Integer disponibleBoxesId) {
		this.disponibleBoxesId = disponibleBoxesId;
	}

	public Collection<ReservaDetalle> getReservaDetalleCollection() {
		return reservaDetalleCollection;
	}
	public void setReservaDetalleCollection(Collection<ReservaDetalle> reservaDetalleCollection) {
		this.reservaDetalleCollection = reservaDetalleCollection;
	}
	public Servicios getServicioId() {
		return servicioId;
	}
	public void setServicioId(Servicios servicioId) {
		this.servicioId = servicioId;
	}
	public Boxes getBoxesId() {
		return boxesId;
	}
	public void setBoxesId(Boxes boxesId) {
		this.boxesId = boxesId;
	}


   

    
}
