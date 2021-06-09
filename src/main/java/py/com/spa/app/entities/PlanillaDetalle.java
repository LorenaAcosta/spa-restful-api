package py.com.spa.app.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
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
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "planilla_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanillaDetalle.findAll", query = "SELECT p FROM PlanillaDetalle p"),
    @NamedQuery(name = "PlanillaDetalle.findByPlanillaDetalleId", query = "SELECT v FROM PlanillaDetalle v WHERE v.planillaDetalleId = :planillaDetalleId")})
public class PlanillaDetalle implements Serializable {
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "planilla_detalle_id")
    private Integer planillaDetalleId;
   
    @Column(name = "monto_debe")
    private Integer montoDebe;
    
    @Column(name = "monto_haber")
    private Integer montoHaber;
    
    @Column(name = "concepto_id")
    private Integer conceptoId;

 
    @JoinColumn(name = "reserva_id", referencedColumnName = "reserva_id")
    @ManyToOne(optional = true)
    private ReservaDetalle reservas;


    @JsonBackReference(value="detalle-planilla")
    @JoinColumn(name = "planilla_id", referencedColumnName = "planilla_id")
    @ManyToOne(optional = false)
    private Planilla planilla;

	public PlanillaDetalle() {
		super();
	}

	public PlanillaDetalle(Integer planillaDetalleId, Integer montoDebe, Integer montoHaber, Integer conceptoId,
			ReservaDetalle reservas, Planilla planilla) {
		super();
		this.planillaDetalleId = planillaDetalleId;
		this.montoDebe = montoDebe;
		this.montoHaber = montoHaber;
		this.conceptoId = conceptoId;
		this.reservas = reservas;
		this.planilla = planilla;
	}


	public Integer getPlanillaDetalleId() {
		return planillaDetalleId;
	}

	public void setPlanillaDetalleId(Integer planillaDetalleId) {
		this.planillaDetalleId = planillaDetalleId;
	}


	public Integer getMontoDebe() {
		return montoDebe;
	}


	public void setMontoDebe(Integer montoDebe) {
		this.montoDebe = montoDebe;
	}


	public Integer getMontoHaber() {
		return montoHaber;
	}

	public void setMontoHaber(Integer montoHaber) {
		this.montoHaber = montoHaber;
	}


	public Integer getConceptoId() {
		return conceptoId;
	}

	public void setConceptoId(Integer conceptoId) {
		this.conceptoId = conceptoId;
	}

	public ReservaDetalle getReservas() {
		return reservas;
	}

	public void setReservas(ReservaDetalle reservas) {
		this.reservas = reservas;
	}


	public Planilla getPlanilla() {
		return planilla;
	}

	public void setPlanilla(Planilla planilla) {
		this.planilla = planilla;
	}


	@Override
	public String toString() {
		return "PlanillaDetalle [planillaDetalleId=" + planillaDetalleId + ", montoDebe=" + montoDebe + ", montoHaber="
				+ montoHaber + ", conceptoId=" + conceptoId + ", reservas=" + reservas + ", planilla=" + planilla + "]";
	}
    
  
    

}
