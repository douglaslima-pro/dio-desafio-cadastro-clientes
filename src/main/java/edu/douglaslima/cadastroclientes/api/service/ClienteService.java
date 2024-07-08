package edu.douglaslima.cadastroclientes.api.service;

import java.util.List;

import edu.douglaslima.cadastroclientes.api.model.Cliente;

public interface ClienteService {
	
	void cadastrarCliente(Cliente cliente);
	
	void cadastrarCliente(Cliente cliente, String cep);
	
	Cliente pesquisarClientePeloCpf(String cpf);
	
	List<Cliente> pesquisarClientes();
	
	void excluirCliente(Integer id);
	
	boolean existeClientePeloId(Integer id);

}
