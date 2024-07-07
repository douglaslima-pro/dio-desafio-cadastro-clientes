package edu.douglaslima.cadastroclientes.api.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import edu.douglaslima.cadastroclientes.api.model.Cep;

@Service
public class CepApiService {
	
	private final RestClient restClient;
	
	public CepApiService() {
		this.restClient = RestClient.builder()
				.baseUrl("https://viacep.com.br/ws")
				.build();
	}

	public Cep pesquisarCep(String cep) {
		return this.restClient.get()
				.uri("/{cep}/json", cep)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.body(Cep.class);
	}

}
