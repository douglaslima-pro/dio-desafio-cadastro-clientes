package edu.douglaslima.cadastroclientes.api.service;

import java.util.Optional;

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

	/**
	 * Realiza uma solicitação HTTP GET na API ViaCep, passando como argumento um {@code cep}.
	 * @param cep CEP (Código de Endereçamento Postal)
	 * @return um {@code Optional} contendo os dados do CEP encontrado
	 * @see <a href='https://viacep.com.br'>https://viacep.com.br</a>
	 */
	public Optional<Cep> pesquisarCep(String cep) {
		Cep cepEncontrado = this.restClient.get()
				.uri("/{cep}/json", cep)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.body(Cep.class);
		return Optional.ofNullable(cepEncontrado);
	}

}
