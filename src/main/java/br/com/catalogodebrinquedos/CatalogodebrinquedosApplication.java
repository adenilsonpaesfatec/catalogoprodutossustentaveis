package br.com.catalogodebrinquedos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"br.com.catalogodebrinquedos"})
public class CatalogodebrinquedosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogodebrinquedosApplication.class, args);
	}

}
