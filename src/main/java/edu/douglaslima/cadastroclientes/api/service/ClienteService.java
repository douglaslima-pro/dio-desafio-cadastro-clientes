package edu.douglaslima.cadastroclientes.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.douglaslima.cadastroclientes.api.exception.CepNaoEncontradoException;
import edu.douglaslima.cadastroclientes.api.model.Cep;
import edu.douglaslima.cadastroclientes.api.model.Cliente;
import edu.douglaslima.cadastroclientes.api.model.Endereco;
import edu.douglaslima.cadastroclientes.api.model.Telefone;
import edu.douglaslima.cadastroclientes.api.repository.ClienteRepository;

/**
 * Classe do tipo {@code Service} que engloba os principais métodos referentes à entidade {@code Cliente}.
 * @author Douglas Lima
 * @version 1.0
 * @since 2024-07-08
 */
@Service
public class ClienteService {


	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private CepApiService cepApiService;

	/**
	 * Realiza o cadastro de um novo cliente.
	 * @param cliente Objeto que representa uma entidade do tipo {@code Cliente} com todos os seus atributos.
	 */
	public void cadastrarCliente(Cliente cliente) {
		if (!cliente.getTelefones().isEmpty()) {
			List<Telefone> telefones = cliente.getTelefones();
			telefones.stream()
				.map(telefone -> {
					telefone.setCliente(cliente);
					return telefone;
				})
				.toList();
			cliente.setTelefones(telefones);
		}
		this.clienteRepository.save(cliente);
	}
	
	/**
	 * Realiza o cadastro de um novo cliente e preenche os dados de endereço de forma automática a partir do argumento {@code cep}.
	 * @param cliente Objeto que representa uma entidade do tipo {@code Cliente} com todos os seus atributos.
	 * @param cep CEP (Código de Endereçamento Postal)
	 * @throws CepNaoEncontradoException indica que a API ViaCep não encontrou dados para o CEP informado
	 */
	public void cadastrarCliente(Cliente cliente, String cep) throws CepNaoEncontradoException {
		Optional<Cep> cepEncontrado = this.cepApiService.pesquisarCep(cep);
		if (cepEncontrado.isEmpty()) {
			throw new CepNaoEncontradoException("Não foram encontrados dados para o CEP informado %s!", cep);
		} else {
			if (!cliente.getTelefones().isEmpty()) {
				List<Telefone> telefones = cliente.getTelefones();
				telefones.stream()
					.map(telefone -> {
						telefone.setCliente(cliente);
						return telefone;
					})
					.toList();
				cliente.setTelefones(telefones);
			}
			Endereco endereco = new Endereco();
			endereco.setCep(cepEncontrado.get().cep());
			endereco.setEstado(cepEncontrado.get().uf());
			endereco.setCidade(cepEncontrado.get().localidade());
			endereco.setBairro(cepEncontrado.get().bairro());
			endereco.setLogradouro(cepEncontrado.get().logradouro());
			endereco.setComplemento(cepEncontrado.get().complemento());
			cliente.setEndereco(endereco);
		}
		this.clienteRepository.save(cliente);
	}
	
	/**
	 * Pesquisa um cliente através do CPF.
	 * @param cpf CPF (Cadastro de Pessoa Física)
	 * @return um {@code Optional} contendo os dados do cliente encontrado
	 */
	public Optional<Cliente> pesquisarClientePeloCpf(String cpf) {
		return this.clienteRepository.findByCpf(cpf);
	}
	
	/**
	 * Pesquisa todos os clientes cadastrados no sistema.
	 * @return um {@code List} contendo objetos do tipo {@code Cliente}
	 */
	public List<Cliente> pesquisarClientes() {
		return this.clienteRepository.findAll();
	}
	
	/**
	 * Exclui um cliente do sistema com base no argumento id.
	 * @param id id do cliente
	 */
	public void excluirCliente(Integer id) {
		this.clienteRepository.deleteById(id);
	}
	
	/**
	 * Retorna {@code true} se um cliente com {@code id} do argumento existir.
	 * @param id id do cliente
	 * @return {@code true} se o cliente existir, caso contrário retorna {@code false}
	 */
	public boolean existeClientePeloId(Integer id) {
		return this.clienteRepository.existsById(id);
	}
	
}
