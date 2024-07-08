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
import edu.douglaslima.cadastroclientes.api.service.ClienteViaCepService;

@RestController
@RequestMapping("/cadastroclientes/api/clientes")
public class ClienteController {

	@Autowired
	private ClienteViaCepService clienteViaCepService;

	@PostMapping("/cadastro")
	public ResponseEntity<String> cadastrarCliente(@RequestBody Cliente cliente) {
		this.clienteViaCepService.cadastrarCliente(cliente);
		return ResponseEntity.ok("Cliente cadastrado com sucesso!");
	}

	@PostMapping("/cadastro/{cep}")
	public ResponseEntity<String> cadastrarCliente(@RequestBody Cliente cliente, @PathVariable("cep") String cep) {
		try {
			this.clienteViaCepService.cadastrarCliente(cliente, cep);
			return ResponseEntity.ok("Cliente cadastrado com sucesso!");
		} catch (CepNaoEncontradoException e) {
			throw new CepNaoEncontradoException(e.getMessage());
		}
	}

	@GetMapping("/pesquisa/{cpf}")
	public ResponseEntity<Cliente> pesquisarClientePeloCpf(@PathVariable("cpf") String cpf) {
		try {
			Cliente cliente = this.clienteViaCepService.pesquisarClientePeloCpf(cpf);
			return ResponseEntity.ok(cliente);
		} catch(ClienteNaoEncontradoException e) {
			throw new ClienteNaoEncontradoException(e.getMessage());
		}
	}
	
	@GetMapping("/pesquisa")
	public ResponseEntity<List<Cliente>> pesquisarClientes() {
		List<Cliente> clientes = this.clienteViaCepService.pesquisarClientes();
		return ResponseEntity.ok(clientes);
	}

	@DeleteMapping("/exclusao/{id}")
	public ResponseEntity<String> excluirCliente(@PathVariable("id") Integer id) {
		try {
			this.clienteViaCepService.excluirCliente(id);
			return ResponseEntity.ok(String.format("Cliente de ID %d excluído com sucesso!", id));
		} catch(ClienteNaoEncontradoException e) {
			throw new ClienteNaoEncontradoException(e.getMessage());
		}
	}

}
