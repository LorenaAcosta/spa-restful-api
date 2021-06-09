package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import py.com.spa.app.dao.IPlanillaDetalleDao;
import py.com.spa.app.entities.Planilla;
import py.com.spa.app.entities.PlanillaDetalle;
import py.com.spa.app.reportes.DetallesConceptos;
import py.com.spa.app.reportes.RankingP;

@Service
public class PlanillaDetalleService {
	
	@Autowired
	private IPlanillaDetalleDao planillaDao;
	
	
	@Transactional(readOnly=true)
	public List<PlanillaDetalle> findAll(){
		return (List<PlanillaDetalle>) planillaDao.findAll();
	}
	
	@Transactional
	public PlanillaDetalle addPlanilla(PlanillaDetalle planilla) {
		return planillaDao.save(planilla);
	}
	
	@Transactional(readOnly=true)
	public PlanillaDetalle findByPlanillaId(Integer id) {
		return (PlanillaDetalle) planillaDao.findById(id).orElse(null);
	}

	@Transactional(readOnly=true)
	public List<PlanillaDetalle> findByPlanillaId2(Integer id) {
		return (List<PlanillaDetalle>) planillaDao.findByPlanillaId(id);
	}

	@Transactional
	public void updatePlanilla(PlanillaDetalle planilla) {
		planillaDao.save(planilla);
	}
	
	@Transactional
	public void deletePlanilla(Integer id) {
		planillaDao.deleteById(id);
	}

	public Integer getComisiones(Integer id) {
		return planillaDao.getComisiones(id);
	}

	public Integer getSalario(Integer id) {
		return planillaDao.getSalario(id);
	}

	@Transactional
	public List<DetallesConceptos> getDetalles(Integer id) {
		return (List<DetallesConceptos>) planillaDao.getDetalles(id);
	}
	
}







