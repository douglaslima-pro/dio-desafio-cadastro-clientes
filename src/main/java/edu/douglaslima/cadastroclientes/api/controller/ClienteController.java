package edu.douglaslima.cadastroclientes.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<String> inserir(@RequestBody Cliente cliente, @PathVariable String cep) {
		clienteViaCepService.inserir(cliente, cep);
		return ResponseEntity.ok("Cliente cadastrado com sucesso!");
	}
	
	@PutMapping("/atualizacao")
	public ResponseEntity<String> atualizar(@RequestBody Cliente cliente) {
		clienteViaCepService.atualizar(cliente);
		return ResponseEntity.ok("Os dados do cliente foram atualizados com sucesso!");
	}
	
	@PutMapping("/atualizacao/{cep}")
	public ResponseEntity<String> atualizar(@RequestBody Cliente cliente, @PathVariable String cep) {
		clienteViaCepService.atualizar(cliente, cep);
		return ResponseEntity.ok("Os dados do cliente foram atualizados com sucesso!");
	}

	@GetMapping("/pesquisa")
	public ResponseEntity<Cliente> buscarPorCpf(@RequestParam("cpf") String cpf) {
		Cliente cliente = clienteViaCepService.buscarPorCpf(cpf);
		return ResponseEntity.ok(cliente);
	}

	@GetMapping("/pesquisa/todos")
	public ResponseEntity<Object> buscarTodos() {
		List<Cliente> clientes = clienteViaCepService.buscarTodos();
		if (!clientes.isEmpty()) {
			return ResponseEntity.ok(clientes);
		} else {
			return ResponseEntity.ok("Nenhum cliente cadastrado no sistema!");
		}
	}

	@DeleteMapping("/exclusao/{id}")
	public ResponseEntity<String> deletar(@PathVariable Integer id) {
		clienteViaCepService.deletar(id);
		return new ResponseEntity<String>(String.format("Cliente de ID %d excluído com sucesso!", id), HttpStatus.NO_CONTENT);
	}

}
