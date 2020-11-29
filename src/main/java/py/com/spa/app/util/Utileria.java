package py.com.spa.app.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class Utileria {

	public static String guardarArchivo(MultipartFile multiPart, String ruta) {
		// Obtenemos el nombre original del archivo.
		String nombreOriginal = multiPart.getOriginalFilename();
		try {
		// Formamos el nombre del archivo para guardarlo en el disco duro.
		File imageFile = new File(ruta + nombreOriginal);
		System.out.println("Archivo: " + imageFile.getAbsolutePath());
		//Guardamos fisicamente el archivo en HD.
		multiPart.transferTo(imageFile);
		return nombreOriginal;
		} catch (IOException e) {
		System.out.println("Error " + e.getMessage());
		return null;
		}
	}
}
