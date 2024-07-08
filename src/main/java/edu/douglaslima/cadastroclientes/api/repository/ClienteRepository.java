package edu.douglaslima.cadastroclientes.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.douglaslima.cadastroclientes.api.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	Optional<Cliente> findByCpf(String cpf);

}
