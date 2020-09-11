/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.spa.app.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "reserva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r"),
    @NamedQuery(name = "Reserva.findByReservaId", query = "SELECT r FROM Reserva r WHERE r.reservaId = :reservaId"),
    @NamedQuery(name = "Reserva.findByHoraInicio", query = "SELECT r FROM Reserva r WHERE r.horaInicio = :horaInicio")})
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "reserva_id")
    private String reservaId;

    @Basic(optional = false)
    @Column(name = "hora_inicio")
    private String horaInicio;
    
    @Basic(optional = false)
    @Column(name = "usuario_id")
    private Integer usuarioId;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "reserva")
    private ReservaDetalle reservaDetalle;
    

    public Reserva() {
    }

    public Reserva(String reservaId) {
        this.reservaId = reservaId;
    }

    public Reserva(String reservaId, String horaInicio) {
        this.reservaId = reservaId;
        this.horaInicio = horaInicio;
    }

    public String getReservaId() {
        return reservaId;
    }

    public void setReservaId(String reservaId) {
        this.reservaId = reservaId;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public ReservaDetalle getReservaDetalle() {
        return reservaDetalle;
    }

    public void setReservaDetalle(ReservaDetalle reservaDetalle) {
        this.reservaDetalle = reservaDetalle;
    }

    public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (reservaId != null ? reservaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.reservaId == null && other.reservaId != null) || (this.reservaId != null && !this.reservaId.equals(other.reservaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Reserva[ reservaId=" + reservaId + " ]";
    }

}
