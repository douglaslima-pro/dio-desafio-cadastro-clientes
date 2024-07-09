package edu.douglaslima.cadastroclientes.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import edu.douglaslima.cadastroclientes.api.exception.CepNaoEncontradoException;
import edu.douglaslima.cadastroclientes.api.exception.ClienteNaoEncontradoException;
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
public class ClienteViaCepService implements ClienteService {


	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ViaCepService viaCepService;

	/**
	 * Realiza o cadastro de um novo cliente.
	 * @param cliente Objeto que representa uma entidade do tipo {@code Cliente} com todos os seus atributos.
	 */
	@Override
	public void inserir(Cliente cliente) {
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
	public void inserir(Cliente cliente, String cep) throws CepNaoEncontradoException {
		Optional<Cep> cepEncontrado = this.viaCepService.pesquisarCep(cep);
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
	@Override
	public Cliente buscarPorCpf(String cpf) throws ClienteNaoEncontradoException {
		Optional<Cliente> clienteEncontrado = this.clienteRepository.findByCpf(cpf);
		if (clienteEncontrado.isEmpty()) {
			throw new ClienteNaoEncontradoException(String.format("Nenhum cliente foi encontrado com o CPF %s!", cpf));
		}
		return clienteEncontrado.get();
	}
	
	/**
	 * Pesquisa todos os clientes cadastrados no sistema.
	 * @return um {@code List} contendo objetos do tipo {@code Cliente}
	 */
	@Override
	public List<Cliente> buscarTodos() {
		return this.clienteRepository.findAll();
	}
	
	/**
	 * Exclui um cliente do sistema com base no argumento id.
	 * @param id id do cliente
	 */
	@Override
	public void deletar(Integer id) throws ClienteNaoEncontradoException {
		if (!this.existePorId(id)) {
			throw new ClienteNaoEncontradoException("Nenhum cliente foi encontrado com o ID %d!", id);
		}
		this.clienteRepository.deleteById(id);
	}
	
	/**
	 * Retorna {@code true} se um cliente com {@code id} do argumento existir.
	 * @param id id do cliente
	 * @return {@code true} se o cliente existir, caso contrário retorna {@code false}
	 */
	@Override
	public boolean existePorId(Integer id) {
		return this.clienteRepository.existsById(id);
	}
	
}
