package edu.douglaslima.cadastroclientes.api.doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("API REST - Cadastro de clientes")
						.description("API REST de cadastro de clientes que faz a comunicação com a API ViaCEP.")
						.version("1.0")
						.contact(new Contact()
								.name("Douglas Souza de Lima")
								.email("douglaslima-pro@outlook.com")
								.url("https://github.com/douglaslima-pro")
								)
						.termsOfService("Termo de uso: Open Source")
						);
	}
	
}
