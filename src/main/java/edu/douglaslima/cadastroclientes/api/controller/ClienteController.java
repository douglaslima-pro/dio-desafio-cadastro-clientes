package edu.douglaslima.cadastroclientes.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.douglaslima.cadastroclientes.api.exception.CepNaoEncontradoException;
import edu.douglaslima.cadastroclientes.api.exception.ClienteNaoEncontradoException;
import edu.douglaslima.cadastroclientes.api.model.Cep;
import edu.douglaslima.cadastroclientes.api.model.Cliente;
import edu.douglaslima.cadastroclientes.api.model.Endereco;
import edu.douglaslima.cadastroclientes.api.repository.ClienteRepository;
import edu.douglaslima.cadastroclientes.api.service.CepApiService;

@RestController
@RequestMapping("/cadastroclientes/api/clientes")
public class ClienteController {
	
	@Autowired
	ClienteRepository repository;
	
	@Autowired
	CepApiService cepApiService;

	@PostMapping("/cadastro")
	public ResponseEntity<String> cadastrarCliente(@RequestBody Cliente cliente) {
		this.repository.save(cliente);
		return ResponseEntity.ok("Cliente cadastrado com sucesso!");
	}
	
	@PostMapping("/cadastro/{cep}")
	public ResponseEntity<String> cadastrarClienteComCep(@RequestBody Cliente cliente, @PathVariable("cep") Optional<String> cep) throws CepNaoEncontradoException {
		if (cep.isPresent()) {
			Optional<Cep> cepEncontrado = Optional.ofNullable(this.cepApiService.pesquisarCep(cep.get()));
			if (cepEncontrado.isPresent()) {
				Endereco endereco = new Endereco();
				endereco.setCep(cepEncontrado.get().cep());
				endereco.setEstado(cepEncontrado.get().uf());
				endereco.setCidade(cepEncontrado.get().localidade());
				endereco.setBairro(cepEncontrado.get().bairro());
				endereco.setLogradouro(cepEncontrado.get().logradouro());
				endereco.setComplemento(cepEncontrado.get().complemento());
				cliente.setEndereco(endereco);
				this.repository.save(cliente);
				return ResponseEntity.ok("Cliente cadastrado com sucesso!");
			} else {
				throw new CepNaoEncontradoException("CEP inválido ou inexistente!");
			}
		} else {
			return ResponseEntity.badRequest().body("CEP não informado!");
		}
	}
	
	@GetMapping("/pesquisa/{cpf}")
	public Cliente pesquisarClientePeloCpf(@PathVariable("cpf") String cpf) throws ClienteNaoEncontradoException {
		Optional<Cliente> clienteEncontrado = Optional.ofNullable(this.repository.findByCpf(cpf));
		if (clienteEncontrado.isPresent()) {
			return clienteEncontrado.get();
		} else {
			throw new ClienteNaoEncontradoException(String.format("Nenhum cliente foi encontrado com o CPF %s!", cpf));
		}
	}
	
	@GetMapping("/pesquisa")
	public List<Cliente> pesquisarClientes() {
		return this.repository.findAll();
	}
	
	@DeleteMapping("/exclusao/{id}")
	public ResponseEntity<String> excluirCliente(@PathVariable("id") Integer id) {
		this.repository.deleteById(id);
		return ResponseEntity.ok("Cliente excluído com sucesso!");
	}
	
}
