package py.com.spa.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.Categorias;

public interface ICategoriaDao extends JpaRepository<Categorias, Integer>{

}
