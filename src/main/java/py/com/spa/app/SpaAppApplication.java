package py.com.spa.app;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import py.com.spa.app.entities.Categorias;
import py.com.spa.app.services.FileService;


@SpringBootApplication
@EntityScan(basePackageClasses=Categorias.class) 
public class SpaAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpaAppApplication.class, args);
	}
	
	@Resource
	FileService fileService;

	@Override
	public void run(String... arg) throws Exception{
		fileService.deleteAll();
		fileService.init();
	}
	
	  /* En caso que tenga problema con los cors agregar este Bean */
		@Bean
		public WebMvcConfigurer corsConfigurer() {
			return new WebMvcConfigurer() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST","PUT", "DELETE");
				}
			};
		}

}
