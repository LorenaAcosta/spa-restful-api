package py.com.spa.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import py.com.spa.app.dao.daoImpl;
import py.com.spa.app.entities.Empleados;
import py.com.spa.app.entities.Turnos;

@Component
public class ServiceService {
	
	@Autowired
    private daoImpl daoimpl;

	
	
	
    public Turnos obtenerTurnos(Integer empleadoId) {
       
        Turnos response = new Turnos();

        try {
            response = daoImpl.obtenerTurnos(empleadoId);
        } catch (Exception e) {
            System.out.print( "CooperativaServiceImpl|proccessCargosCooperativos|ERROR:  " + e.getStackTrace()
                    + " - " + e.getMessage());
        }

        return response;
    }
    
    
    

}
