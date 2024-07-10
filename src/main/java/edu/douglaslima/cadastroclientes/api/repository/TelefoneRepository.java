package edu.douglaslima.cadastroclientes.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.douglaslima.cadastroclientes.api.model.Cliente;
import edu.douglaslima.cadastroclientes.api.model.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Integer> {
	
	List<Telefone> findByCliente(Cliente cliente);

	Optional<Telefone> findByDddAndPrefixoAndSufixo(String ddd, String prefixo, String sufixo);
	
}
