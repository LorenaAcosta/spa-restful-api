package py.com.spa.app.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import py.com.spa.app.entities.Categorias;
import py.com.spa.app.entities.ImageModel;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
}
