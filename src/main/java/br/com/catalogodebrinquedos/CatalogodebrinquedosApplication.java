package br.com.catalogodebrinquedos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação de Catálogo de Brinquedos.
 * 
 * <p>Responsável por iniciar a aplicação Spring Boot.</p>
 * 
 * <p>Utiliza a anotação {@code @SpringBootApplication} para indicar a 
 * configuração base da aplicação e definir o pacote de escaneamento.</p>
 */
@SpringBootApplication(scanBasePackages = {"br.com.catalogodebrinquedos"})
public class CatalogodebrinquedosApplication {

	/**
	 * Método principal da aplicação que inicializa o Spring Boot.
	 * 
	 * @param args Argumentos da linha de comando para a execução da aplicação.
	 */
	public static void main(String[] args) {
		SpringApplication.run(CatalogodebrinquedosApplication.class, args);
	}
}
