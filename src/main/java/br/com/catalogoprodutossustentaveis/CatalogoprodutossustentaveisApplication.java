package br.com.catalogoprodutossustentaveis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "br.com.catalogoprodutossustentaveis" })
public class CatalogoprodutossustentaveisApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoprodutossustentaveisApplication.class, args);
	}
}
