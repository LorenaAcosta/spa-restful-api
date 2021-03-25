package py.com.spa.app.dao;

import javax.sql.DataSource;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import py.com.spa.app.entities.Turnos;

@Component
public class daoImpl {
	
	@Autowired
	private static DataSource dataSource;
	@Autowired 
	private JdbcTemplate jdbcTemplateObject;
 
     
    public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	      this.jdbcTemplateObject = new JdbcTemplate(this.dataSource);
	   }
    
   
    //VERIFICAR SOCIO
    public static Turnos obtenerTurnos(Integer empleadoId) throws Exception {

  
	  SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("turnos"); 
		
		//MapSqlParameterSource in = new MapSqlParameterSource();
		//in.addValue("p_documento",requestVerificarSocio.getHeader().getCiCliente());
		// in.addValue("fnacimiento",requestVerificarSocio.getData().getFnacimiento());
		
	//	Map<String, Object> out = jdbcCall.execute(in);
		Map<String, Object> out = jdbcCall.execute();
		
		Turnos u =  new Turnos();
		u.setTurno(   (String) out.get("descripcion"));
		return u;

    }

   
	

}
