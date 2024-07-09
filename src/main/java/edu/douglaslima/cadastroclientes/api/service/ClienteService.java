package edu.douglaslima.cadastroclientes.api.service;

import java.util.List;

import edu.douglaslima.cadastroclientes.api.model.Cliente;

public interface ClienteService {
	
	void inserir(Cliente cliente);
	
	Cliente buscarPorCpf(String cpf);
	
	List<Cliente> buscarTodos();
	
	void deletar(Integer id);
	
	boolean existePorId(Integer id);

}
