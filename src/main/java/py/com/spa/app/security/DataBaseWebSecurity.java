/*
package py.com.spa.app.security;

import java.util.Arrays;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
@Configuration
@EnableWebSecurity
public class DataBaseWebSecurity extends WebSecurityConfigurerAdapter {

	
  @Autowired
  DataSource dataSource;
 
  @Autowired
  public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication().dataSource(dataSource)
        .usersByUsernameQuery("select username,password, estado from clientes where username=?")
        .authoritiesByUsernameQuery("select u.username, p.nombre_perfil from cliente_perfil up " + 
        		" inner join clientes u on u.cliente_id = up.cliente_id" + 
        		" inner join perfiles p on p.perfil_id = up.perfil_id" + 
        		" where username = ? ");
  }
  
  
 
  @Override
  protected void configure(HttpSecurity http) throws Exception {
	http.cors().and().csrf().disable();
    http.authorizeRequests()
    	.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
        .antMatchers("/", "/home", "/login", "/registrar" ).permitAll()
        .antMatchers("/api/cliente/listar").hasAnyAuthority("ADMIN")
        .antMatchers("/api/categoria/listar").hasAnyAuthority("ADMIN")
        .antMatchers(HttpMethod.POST,"/api/cliente/agregar").hasAnyAuthority("ADMIN", "USER")
        .anyRequest().authenticated()
        .and().formLogin().permitAll();
       
   }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
      CorsConfiguration configuration = new CorsConfiguration();
      configuration.setAllowedOrigins(Arrays.asList("*"));
      configuration.setAllowedMethods(Arrays.asList("*"));
      configuration.setAllowedHeaders(Arrays.asList("*"));
      configuration.setAllowCredentials(true);
      configuration.addAllowedHeader("*");
      configuration.addAllowedOrigin("*");
      configuration.addAllowedMethod("OPTIONS");
      configuration.addAllowedMethod("POST");
      configuration.addAllowedMethod("GET");
      configuration.addAllowedMethod("PUT");
      configuration.addAllowedMethod("DELETE");
      
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", configuration);
      return source;
  }
 
  
} 


*/






