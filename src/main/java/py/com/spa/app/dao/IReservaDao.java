package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.Reserva;
import py.com.spa.app.entities.ReservaDetalle;
import py.com.spa.app.entities.Usuario;

public interface IReservaDao extends JpaRepository<Reserva, Integer>{

//	public List<Reserva> findByUsuario(Integer usuarioId);

}
