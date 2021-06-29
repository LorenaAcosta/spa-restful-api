package py.com.spa.app.entities;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import py.com.spa.result.SqlTimeDeserializer;
/**
 *
 * @author Lore
 */
@Entity
@Table(name = "arqueo_caja")
@XmlRootElement
public class ArqueoCaja implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "arqueo_id")
    private Integer arqueoId;
    
    @Basic(optional = true)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="America/Asuncion")
    @Column(name = "fecha_apertura")
    private Date fechaApertura;
    
    @Basic(optional = true)
    @JsonFormat(pattern="HH:mm:ss")
    @JsonDeserialize(using = SqlTimeDeserializer.class)
    @Column(name = "hora_apertura")
    private Time horaApertura;
    
    @Basic(optional = true)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="America/Asuncion")
    @Column(name = "fecha_cierre")
    private Date fechaCierre;
    
    @Basic(optional = true)
    @JsonFormat(pattern="HH:mm:ss")
    @JsonDeserialize(using = SqlTimeDeserializer.class)
    @Column(name = "hora_cierre")
    private Time horaCierre;
    
    @Basic(optional = true)
    @Column(name = "total_ventas")
    private Long totalVentas;
    
    @Basic(optional = true)
    @Column(name = "total_caja")
    private Long totalCaja;
    
    @Basic(optional = true)
    @Column(name = "saldo_cierre")
    private Long saldoCierre;
    
    @Basic(optional = true)
    @Column(name = "estado")
    private String estado;
       
    @JoinColumn(name = "punto_expedicion_id", referencedColumnName = "punto_expedicion_id")
    @ManyToOne(optional = false)
    private PuntoExpedicion puntoExpedicionId;

    public ArqueoCaja() {
    }


	public ArqueoCaja(Integer arqueoId) {
		super();
		this.arqueoId = arqueoId;
	}
	
	public ArqueoCaja(Integer arqueoId, Date fechaApertura, Time horaApertura, Date fechaCierre, Time horaCierre,
			Long totalVentas, Long totalCaja, Long saldoCierre, String estado, PuntoExpedicion puntoExpedicionId) {
		super();
		this.arqueoId = arqueoId;
		this.fechaApertura = fechaApertura;
		this.horaApertura = horaApertura;
		this.fechaCierre = fechaCierre;
		this.horaCierre = horaCierre;
		this.totalVentas = totalVentas;
		this.totalCaja = totalCaja;
		this.saldoCierre = saldoCierre;
		this.estado = estado;
		this.puntoExpedicionId = puntoExpedicionId;
	}


	public Integer getArqueoId() {
		return arqueoId;
	}


	public void setArqueoId(Integer arqueoId) {
		this.arqueoId = arqueoId;
	}


	public Date getFechaApertura() {
		return fechaApertura;
	}


	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}


	public Time getHoraApertura() {
		return horaApertura;
	}


	public void setHoraApertura(Time horaApertura) {
		this.horaApertura = horaApertura;
	}


	public Date getFechaCierre() {
		return fechaCierre;
	}


	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}


	public Time getHoraCierre() {
		return horaCierre;
	}


	public void setHoraCierre(Time horaCierre) {
		this.horaCierre = horaCierre;
	}


	public Long getTotalVentas() {
		return totalVentas;
	}


	public void setTotalVentas(Long totalVentas) {
		this.totalVentas = totalVentas;
	}


	public Long getTotalCaja() {
		return totalCaja;
	}

	public Long getSaldoCierre() {
		return saldoCierre;
	}


	public void setSaldoCierre(Long saldoCierre) {
		this.saldoCierre = saldoCierre;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public void setTotalCaja(Long totalCaja) {
		this.totalCaja = totalCaja;
	}


	public PuntoExpedicion getPuntoExpedicionId() {
		return puntoExpedicionId;
	}


	public void setPuntoExpedicionId(PuntoExpedicion puntoExpedicionId) {
		this.puntoExpedicionId = puntoExpedicionId;
	}


	@Override
    public int hashCode() {
        int hash = 0;
        hash += (arqueoId != null ? arqueoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArqueoCaja)) {
            return false;
        }
        ArqueoCaja other = (ArqueoCaja) object;
        if ((this.arqueoId == null && other.arqueoId != null) || (this.arqueoId != null && !this.arqueoId.equals(other.arqueoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spa.Arqueo[ arqueoId=" + arqueoId + " ]";
    }
    
}