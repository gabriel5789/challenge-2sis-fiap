package com.fiap.crm.repository;

import com.fiap.crm.model.Cliente;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	// para teste
	@Override
	List<Cliente> findAll();

	@Override
	Optional<Cliente> findById(Long integer);

	Optional<Cliente> findByCpf(String cpf);

	Optional<Cliente> findByEmail(String email);

	@Override
	<S extends Cliente> S save(S entity);


}
