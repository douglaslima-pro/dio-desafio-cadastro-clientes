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
@RequestMapping("/cadastroclientes/clientes")
public class ClienteController {

	@Autowired
	private ClienteViaCepService clienteViaCepService;

	@PostMapping("/cadastro")
	public ResponseEntity<String> inserir(@RequestBody Cliente cliente) {
		clienteViaCepService.inserir(cliente);
		return ResponseEntity.ok("Cliente cadastrado com sucesso!");
	}

	@PostMapping("/cadastro/{cep}")
	public ResponseEntity<String> inserir(@RequestBody Cliente cliente, @PathVariable("cep") String cep) {
		clienteViaCepService.inserir(cliente, cep);
		return ResponseEntity.ok("Cliente cadastrado com sucesso!");
	}

	@GetMapping("/pesquisa/{cpf}")
	public ResponseEntity<Cliente> buscarPorCpf(@PathVariable("cpf") String cpf) {
		Cliente cliente = clienteViaCepService.buscarPorCpf(cpf);
		return ResponseEntity.ok(cliente);
	}

	@GetMapping("/pesquisa")
	public ResponseEntity<Object> buscarTodos() {
		List<Cliente> clientes = clienteViaCepService.buscarTodos();
		if (!clientes.isEmpty()) {
			return ResponseEntity.ok(clientes);
		} else {
			return new ResponseEntity<Object>("Nenhum cliente cadastrado no sistema!", HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/exclusao/{id}")
	public ResponseEntity<String> deletar(@PathVariable("id") Integer id) {
		clienteViaCepService.deletar(id);
		return new ResponseEntity<String>(String.format("Cliente de ID %d excluído com sucesso!", id), HttpStatus.NO_CONTENT);
	}

}
