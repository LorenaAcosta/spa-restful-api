package py.com.spa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.IHorarioDao;
import py.com.spa.app.entities.Horario;
import py.com.spa.params.PaginadoParam;
import py.com.spa.result.PaginadoResult;

@Service
public class HorarioService {
	
	@Autowired
	private IHorarioDao horarioDao;
	
	
	@Transactional(readOnly=true)
	public List<Horario> findAll(){
		return (List<Horario>) horarioDao.findAll();
	}
	
	@Transactional
	public void addHorario(Horario horario) {
		horarioDao.save(horario);
	}
	
	@Transactional(readOnly=true)
	public Horario findByHorarioId(Integer id) {
		return (Horario) horarioDao.findById(id).orElse(null);
	}

	@Transactional
	public void updateHorario(Horario horario) {
		horarioDao.save(horario);
	}
	
	@Transactional
	public void deleteHorario(Integer id) {
		horarioDao.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	public PaginadoResult<Horario> listar (PaginadoParam<Horario> param) {
		
		
		
		ExampleMatcher matcher = ExampleMatcher.matching()
	            .withStringMatcher(StringMatcher.CONTAINING);
	        Example<Horario> example = Example.of(param.getFiltros(),matcher);
	            Page<Horario> lista = horarioDao.findAll(example,
	                PageRequest.of(
	                        param.getPagina(), 
	                        param.getCantidad(), 
	                        Sort.by(
	                                param.getOrderDir().equals("ASC")? Sort.Direction.ASC:Sort.Direction.DESC,
	                                param.getOrderBy())
	                        ));
	                PaginadoResult<Horario> result = new PaginadoResult<>(lista);
	                return result;
		
	}

	
}







