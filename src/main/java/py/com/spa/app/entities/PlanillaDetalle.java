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
    @NamedQuery(name = "PlanillaDetalle.findAll", query = "SELECT p FROM Planilla p")})
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
    @ManyToOne(optional = false)
    private ReservaDetalle reservas;

    
    @JsonBackReference(value="detalle-planilla")
    @JoinColumn(name = "planilla_id", referencedColumnName = "planilla_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Planilla planillaId;
    

	public PlanillaDetalle() {
		super();
	}

	public PlanillaDetalle(Integer planillaDetalleId,  ReservaDetalle reservas) {
		super();
		this.planillaDetalleId = planillaDetalleId;
		this.reservas = reservas;
	}

	public Integer getPlanillaDetalleId() {
		return planillaDetalleId;
	}

	public void setPlanillaDetalleId(Integer planillaDetalleId) {
		this.planillaDetalleId = planillaDetalleId;
	}



	public ReservaDetalle getReservas() {
		return reservas;
	}

	public void setReservas(ReservaDetalle reservas) {
		this.reservas = reservas;
	}
    
    
    

}
