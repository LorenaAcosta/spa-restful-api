package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.IPlanillaDao;
import py.com.spa.app.entities.Planilla;

@Service
public class PlanillaService {
	
	@Autowired
	private IPlanillaDao planillaDao;
	
	
	@Transactional(readOnly=true)
	public List<Planilla> findAll(){
		return (List<Planilla>) planillaDao.findAll();
	}
	
	@Transactional
	public Planilla addPlanilla(Planilla planilla) {
		return planillaDao.save(planilla);
	}
	
	@Transactional(readOnly=true)
	public Planilla findByPlanillaId(Integer id) {
		return (Planilla) planillaDao.findById(id).orElse(null);
	}

	@Transactional
	public void updatePlanilla(Planilla planilla) {
		planillaDao.save(planilla);
	}
	
	@Transactional
	public void deletePlanilla(Integer id) {
		planillaDao.deleteById(id);
	}

	public List<Planilla> listarpormes(String id) {
		return (List<Planilla>) planillaDao.listarpormes(id);
	}
}







