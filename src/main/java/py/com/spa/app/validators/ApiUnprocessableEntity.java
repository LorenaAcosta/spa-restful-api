package py.com.spa.app.validators;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Excepcion persionalizada de status 422: Unprocessable entity
 * @author PC
 *
 */


@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ApiUnprocessableEntity extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3442517689786789197L;
	public ApiUnprocessableEntity(String mensaje) {
		super(mensaje);
	}

	

}
