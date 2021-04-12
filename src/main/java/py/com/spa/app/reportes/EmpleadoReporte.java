package py.com.spa.app.reportes;

public interface EmpleadoReporte {
	
	Integer getItem();
	Integer getCedula();
	String getNombre();
	String getApellido();
	String getDireccion();
	String getTelefono();
	String getCelular();
	String getCorreo();
	String getNacionalidad();
	String getCiudad();
	String getEstadocivil();
	String getFechaingreso();
	String getFechanac();
	String getFechasalida();
	String getFuncion();
	String getImagen();
	String getSueldo();
	String getEstado();
}

/*
 Select ROW_NUMBER() OVER (ORDER BY r.reserva_id) as item, cedula, nombre, apellido, 
 direccion, telefono, celular, correo, nacionalidad, ciudad, estado_civil as estadocivil, 
 fecha_ingreso as fechaingreso, fecha_nac as fechanac, fecha_salida as fechasalida, funcion, 
 image_name as imagen, sueldo, estado
 */