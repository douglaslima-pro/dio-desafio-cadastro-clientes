package edu.douglaslima.cadastroclientes.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.douglaslima.cadastroclientes.api.exception.CepNaoEncontradoException;
import edu.douglaslima.cadastroclientes.api.exception.ClienteNaoEncontradoException;
import edu.douglaslima.cadastroclientes.api.model.Cliente;
import edu.douglaslima.cadastroclientes.api.service.ClienteService;

@RestController
@RequestMapping("/cadastroclientes/api/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@PostMapping("/cadastro")
	public ResponseEntity<String> cadastrarCliente(@RequestBody Cliente cliente) {
		this.clienteService.cadastrarCliente(cliente);
		return ResponseEntity.ok("Cliente cadastrado com sucesso!");
	}

	@PostMapping("/cadastro/{cep}")
	public ResponseEntity<String> cadastrarCliente(@RequestBody Cliente cliente, @PathVariable("cep") String cep) {
		try {
			this.clienteService.cadastrarCliente(cliente, cep);
			return ResponseEntity.ok("Cliente cadastrado com sucesso!");
		} catch (CepNaoEncontradoException e) {
			throw new CepNaoEncontradoException(e.getMessage());
		}
	}

	@GetMapping("/pesquisa/{cpf}")
	public Cliente pesquisarClientePeloCpf(@PathVariable("cpf") String cpf)
			throws ClienteNaoEncontradoException {
		Optional<Cliente> clienteEncontrado = this.clienteService.pesquisarClientePeloCpf(cpf);
		if (clienteEncontrado.isPresent()) {
			return clienteEncontrado.get();
		} else {
			throw new ClienteNaoEncontradoException(String.format("Nenhum cliente foi encontrado com o CPF %s!", cpf));
		}
	}
	
	@GetMapping("/pesquisa")
	public List<Cliente> pesquisarClientes() {
		return this.clienteService.pesquisarClientes();
		
	}

	@DeleteMapping("/exclusao/{id}")
	public ResponseEntity<String> excluirCliente(@PathVariable("id") Integer id) throws ClienteNaoEncontradoException {
		if (this.clienteService.existeClientePeloId(id)) {
			this.clienteService.excluirCliente(id);
			return ResponseEntity.ok(String.format("Cliente de ID %d excluído com sucesso!", id));
		} else {
			throw new ClienteNaoEncontradoException("Nenhum cliente foi encontrado com o ID %d!", id);
		}
	}

}
