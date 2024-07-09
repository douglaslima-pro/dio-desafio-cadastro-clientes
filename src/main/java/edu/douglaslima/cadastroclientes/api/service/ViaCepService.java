package edu.douglaslima.cadastroclientes.api.service;

import java.util.Optional;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import edu.douglaslima.cadastroclientes.api.exception.CepNaoEncontradoException;
import edu.douglaslima.cadastroclientes.api.model.Cep;

@Service
public class ViaCepService {
	
	private final RestClient restClient;
	
	public ViaCepService() {
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
				.onStatus(
						httpStatusCode -> httpStatusCode.is4xxClientError(),
						(request, response) -> {
							throw new CepNaoEncontradoException("Não foram encontrados dados para o CEP informado '%s'!", cep);
						})
				.body(Cep.class);
		return Optional.ofNullable(cepEncontrado);
	}

}
