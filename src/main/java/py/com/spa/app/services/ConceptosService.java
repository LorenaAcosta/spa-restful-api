package py.com.spa.app.services;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.ICategoriaDao;
import py.com.spa.app.dao.IConceptosDao;
import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.Conceptos;

@Service
public class ConceptosService {
	
	@Autowired
	private IConceptosDao conceptosDao;
	
	
	@Transactional(readOnly=true)
	public List<Conceptos> findAll(){
		return (List<Conceptos>) conceptosDao.findAll();
	}

}
