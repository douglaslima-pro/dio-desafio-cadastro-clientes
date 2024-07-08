package edu.douglaslima.cadastroclientes.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.douglaslima.cadastroclientes.api.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	@Override
	@Query("SELECT c FROM Cliente c INNER JOIN c.telefones")
	List<Cliente> findAll();

	@Query("SELECT c FROM Cliente c INNER JOIN c.telefones WHERE c.cpf = (:cpf)")
	Optional<Cliente> findByCpf(@Param("cpf") String cpf);

}
