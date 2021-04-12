package py.com.spa.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import py.com.spa.app.entities.Empleados;
import py.com.spa.app.entities.Horario;
import py.com.spa.app.entities.Ventas;

public interface IHorarioDao extends  JpaRepository<Horario, Integer> {
	
	 Horario findByEmpleadoId(Empleados id);
	 
	 @Query(value = "select * from horario where empleado_id = :id and dia_trabajo=  cast(:dia as varchar)",  nativeQuery = true)
   	 Horario findByEmpleado(@Param("id") Integer id, @Param("dia") Integer dia );
	 
	 @Query(value = "select * from horario where empleado_id = :id",  nativeQuery = true)
	 List<Horario> findByIdEmpleadoLista(@Param("id") Integer id);

	 @Query(value = "select * from horario where empleado_id = :id order by dia_trabajo asc",  nativeQuery = true)
	 List<Horario> getListaHorarios(Integer id);

}
