package py.com.spa.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.spa.app.dao.IClienteDao;
import py.com.spa.app.entities.Clientes	;
import py.com.spa.app.entities.Reservas;

@Service
public class ClienteService {
	@Autowired
	private IClienteDao clienteDao;
	
	@Transactional(readOnly=true)
	public List<Clientes> findAll(){
		return (List<Clientes>) clienteDao.findAll(); 
	}
	
	@Transactional
	public void addCliente(Clientes cliente) {
		clienteDao.save(cliente);
	}

	@Transactional
	public void updateCliente(Clientes cliente) {
		clienteDao.save(cliente);
	}
	
	@Transactional
	public void deleteCliente(Integer id) {
		clienteDao.deleteById(id);
	}
	
	@Transactional
	public List<Reservas> getReservasByClienteId(Integer id){
		List<Reservas> reservas= clienteDao.getReservasByClienteId(id);
		return reservas;
	}

	@Transactional(readOnly=true)	
	public Clientes findClienteById(Integer id) {
		return (Clientes) clienteDao.findById(id).orElse(null);
	}


}
