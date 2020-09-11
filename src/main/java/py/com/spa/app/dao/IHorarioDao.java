package py.com.spa.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.Horario;

public interface IHorarioDao extends JpaRepository<Horario, Integer> {
	
}