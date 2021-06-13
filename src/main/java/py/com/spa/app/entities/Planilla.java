/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.spa.app.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

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
@Table(name = "planilla", uniqueConstraints=@UniqueConstraint(columnNames={"mes_pago", "anho_pago"}))
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Planilla.findAll", query = "SELECT p FROM Planilla p"),
    @NamedQuery(name = "Planilla.findByPlanillaId", query = "SELECT p FROM Planilla p WHERE p.planillaId = :planillaId")})
public class Planilla implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "planilla_id")
    private Integer planillaId;
    
    @Column(name = "numero_patronal")
    private Integer numeroPatronal;
    
    @Column(name = "numero_patronalips")
    private Integer numeroPatronalips;
    
    @Column(name = "fecha_pago")
    private Date fechaPago;
    
    @Column(name = "mes_pago")
    private String mesPago;
    
    @Column(name = "anho_pago")
    private Integer anhoPago;
    
    @Column(name = "total")
    private Integer total;
    
    @Column(name = "descuento")
    private Integer descuento;
    
    @Column(name = "saldo")
    private Integer saldo;
    
  
    @JoinColumn(name = "empleado_id", referencedColumnName = "empleado_id")
    @ManyToOne(optional = false)
    private Empleados empleadoId;
    
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id",  nullable = false)
    @ManyToOne()
    private Usuario usuarioId;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "planilla")  
    private Collection<PlanillaDetalle>  planillaDetalleCollection;

    public Planilla() {
    }
    
    
    
	public Planilla(Integer planillaId) {
		super();
		this.planillaId = planillaId;
	}



	public Planilla(Integer planillaId, Integer numeroPatronal, Integer numeroPatronalips, Date fechaPago,
			String mesPago, Integer anhoPago, Integer total, Integer descuento, Integer saldo, Empleados empleadoId,
			Usuario usuarioId) {
		super();
		this.planillaId = planillaId;
		this.numeroPatronal = numeroPatronal;
		this.numeroPatronalips = numeroPatronalips;
		this.fechaPago = fechaPago;
		this.mesPago = mesPago;
		this.anhoPago = anhoPago;
		this.total = total;
		this.descuento = descuento;
		this.saldo = saldo;
		this.empleadoId = empleadoId;
		this.usuarioId = usuarioId;
	}



	public Planilla(Integer planillaId, Integer numeroPatronal, Integer numeroPatronalips, Date fechaPago,
			String mesPago, Integer anhoPago, Integer total, Integer descuento, Integer saldo, Empleados empleadoId,
			Usuario usuarioId, Collection<PlanillaDetalle> planillaDetalleCollection) {
		super();
		this.planillaId = planillaId;
		this.numeroPatronal = numeroPatronal;
		this.numeroPatronalips = numeroPatronalips;
		this.fechaPago = fechaPago;
		this.mesPago = mesPago;
		this.anhoPago = anhoPago;
		this.total = total;
		this.descuento = descuento;
		this.saldo = saldo;
		this.empleadoId = empleadoId;
		this.usuarioId = usuarioId;
		this.planillaDetalleCollection = planillaDetalleCollection;
	}

	public Integer getPlanillaId() {
		return planillaId;
	}

	public void setPlanillaId(Integer planillaId) {
		this.planillaId = planillaId;
	}

	public Integer getNumeroPatronal() {
		return numeroPatronal;
	}

	public void setNumeroPatronal(Integer numeroPatronal) {
		this.numeroPatronal = numeroPatronal;
	}

	public Integer getNumeroPatronalips() {
		return numeroPatronalips;
	}

	public void setNumeroPatronalips(Integer numeroPatronalips) {
		this.numeroPatronalips = numeroPatronalips;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getMesPago() {
		return mesPago;
	}

	public void setMesPago(String mesPago) {
		this.mesPago = mesPago;
	}

	public Integer getAnhoPago() {
		return anhoPago;
	}

	public void setAnhoPago(Integer anhoPago) {
		this.anhoPago = anhoPago;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getDescuento() {
		return descuento;
	}

	public void setDescuento(Integer descuento) {
		this.descuento = descuento;
	}

	public Integer getSaldo() {
		return saldo;
	}

	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}

	public Empleados getEmpleadoId() {
		return empleadoId;
	}

	public void setEmpleadoId(Empleados empleadoId) {
		this.empleadoId = empleadoId;
	}

	public Usuario getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Usuario usuarioId) {
		this.usuarioId = usuarioId;
	}



	public void setPlanillaDetalleCollection(Collection<PlanillaDetalle> planillaDetalleCollection) {
		this.planillaDetalleCollection = planillaDetalleCollection;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (planillaId != null ? planillaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planilla)) {
            return false;
        }
        Planilla other = (Planilla) object;
        if ((this.planillaId == null && other.planillaId != null) || (this.planillaId != null && !this.planillaId.equals(other.planillaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.spa.app.entities.Planilla[ planillaId=" + planillaId + " ]";
    }
    
}
