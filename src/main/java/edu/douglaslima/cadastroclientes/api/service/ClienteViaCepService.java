package edu.douglaslima.cadastroclientes.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.douglaslima.cadastroclientes.api.exception.ClienteNaoEncontradoException;
import edu.douglaslima.cadastroclientes.api.model.Cep;
import edu.douglaslima.cadastroclientes.api.model.Cliente;
import edu.douglaslima.cadastroclientes.api.model.Endereco;
import edu.douglaslima.cadastroclientes.api.model.Telefone;
import edu.douglaslima.cadastroclientes.api.repository.ClienteRepository;
import edu.douglaslima.cadastroclientes.api.repository.TelefoneRepository;

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
	private TelefoneRepository telefoneRepository;

	@Autowired
	private ViaCepService viaCepService;

	/**
	 * Realiza o cadastro de um novo cliente.
	 * @param cliente Objeto que representa uma entidade do tipo {@code Cliente} com todos os seus atributos.
	 */
	@Override
	public void inserir(Cliente cliente) {
		if (cliente.getTelefones() != null && !cliente.getTelefones().isEmpty()) {
			List<Telefone> telefones = cliente.getTelefones();
			telefones.stream()
				.map(telefone -> {
					telefone.setCliente(cliente);
					return telefone;
				})
				.toList();
			cliente.setTelefones(telefones);
		}
		clienteRepository.save(cliente);
	}
	
	/**
	 * Realiza o cadastro de um novo cliente e preenche os dados de endereço de forma automática a partir do argumento {@code cep}.
	 * @param cliente Objeto que representa uma entidade do tipo {@code Cliente} com todos os seus atributos.
	 * @param cep CEP (Código de Endereçamento Postal)
	 */
	public void inserir(Cliente cliente, String cep) {
		if (cliente.getTelefones() != null && !cliente.getTelefones().isEmpty()) {
			List<Telefone> telefones = cliente.getTelefones();
			telefones.stream()
				.map(telefone -> {
					telefone.setCliente(cliente);
					return telefone;
				})
				.toList();
			cliente.setTelefones(telefones);
		}
		Cep cepEncontrado = viaCepService.pesquisarCep(cep);
		Endereco endereco = new Endereco();
		endereco.setCep(cepEncontrado.cep());
		endereco.setEstado(cepEncontrado.uf());
		endereco.setCidade(cepEncontrado.localidade());
		endereco.setBairro(cepEncontrado.bairro());
		endereco.setLogradouro(cepEncontrado.logradouro());
		endereco.setComplemento(cepEncontrado.complemento());
		cliente.setEndereco(endereco);
		clienteRepository.save(cliente);
	}
	
	/**
	 * Atualiza os dados do cliente.
	 * @param cliente Objeto que representa uma entidade do tipo {@code Cliente} com todos os seus atributos.
	 * @throws ClienteNaoEncontradoException indica que nenhum cliente foi encontrado com os dados informados
	 */
	@Override
	public void atualizar(Cliente cliente) throws ClienteNaoEncontradoException {
		List<Telefone> telefones;
		if (cliente.getTelefones() == null) {
			telefones = telefoneRepository.findByCliente(cliente);
			cliente.setTelefones(telefones);
		} else {
			telefones = cliente.getTelefones();
			telefones = telefones.stream()
					.map(telefone -> {
						telefone.setCliente(cliente);
						return telefone;
						})
					.toList();
			cliente.setTelefones(telefones);
		}
		if (existePorId(cliente.getId())) {
			clienteRepository.save(cliente);
		} else {
			throw new ClienteNaoEncontradoException("Nenhum cliente foi encontrado com os dados informados!");
		}
	}
	
	/**
	 * Atualiza os dados do cliente e preenche os dados de endereço de forma automática a partir do argumento {@code cep}.
	 * @param cliente Objeto que representa uma entidade do tipo {@code Cliente} com todos os seus atributos.
	 * @param cep CEP (Código de Endereçamento Postal)
	 * @throws ClienteNaoEncontradoException indica que nenhum cliente foi encontrado com os dados informados
	 */
	public void atualizar(Cliente cliente, String cep) throws ClienteNaoEncontradoException {
		List<Telefone> telefones;
		if (cliente.getTelefones() == null) {
			telefones = telefoneRepository.findByCliente(cliente);
			cliente.setTelefones(telefones);
		} else {
			telefones = cliente.getTelefones();
			telefones = telefones.stream()
					.map(telefone -> {
						telefone.setCliente(cliente);
						return telefone;
						})
					.toList();
			cliente.setTelefones(telefones);
		}
		if (existePorId(cliente.getId())) {
			Cep cepEncontrado = viaCepService.pesquisarCep(cep);
			Endereco endereco = new Endereco();
			endereco.setCep(cepEncontrado.cep());
			endereco.setEstado(cepEncontrado.uf());
			endereco.setCidade(cepEncontrado.localidade());
			endereco.setBairro(cepEncontrado.bairro());
			endereco.setLogradouro(cepEncontrado.logradouro());
			endereco.setComplemento(cepEncontrado.complemento());
			cliente.setEndereco(endereco);
			clienteRepository.save(cliente);
		} else {
			throw new ClienteNaoEncontradoException("Nenhum cliente foi encontrado com os dados informados!");
		}
	}
	
	/**
	 * Pesquisa um cliente através do CPF.
	 * @param cpf CPF (Cadastro de Pessoa Física)
	 * @return um objeto do tipo {@code Cliente}
	 * @throws ClienteNaoEncontradoException indica que nenhum cliente foi encontrado com o {@code CPF} informado
	 */
	@Override
	public Cliente buscarPorCpf(String cpf) throws ClienteNaoEncontradoException {
		return clienteRepository.findByCpf(cpf)
				.orElseThrow(() -> {
					return new ClienteNaoEncontradoException(String.format("Nenhum cliente foi encontrado com o CPF '%s'!", cpf));
					});
	}
	
	/**
	 * Pesquisa todos os clientes cadastrados no sistema.
	 * @return um {@code List} contendo objetos do tipo {@code Cliente}
	 */
	@Override
	public List<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}
	
	/**
	 * Exclui um cliente do sistema com base no argumento id.
	 * @param id id do cliente
	 * @throws ClienteNaoEncontradoException indica que nenhum cliente foi encontrado com o {@code id} informado
	 */
	@Override
	public void deletar(Integer id) throws ClienteNaoEncontradoException {
		if (!existePorId(id)) {
			throw new ClienteNaoEncontradoException("Nenhum cliente foi encontrado com o ID %d!", id);
		}
		clienteRepository.deleteById(id);
	}
	
	/**
	 * Retorna {@code true} se existir um cliente com o {@code id} informado.
	 * @param id id do cliente
	 * @return {@code true} se o cliente existir, caso contrário retorna {@code false}
	 */
	@Override
	public boolean existePorId(Integer id) {
		return clienteRepository.existsById(id);
	}
	
}
