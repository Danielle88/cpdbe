package br.com.fiap.epictaskapi;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCaching
public class EpictaskapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpictaskapiApplication.class, args);
	}

}
