package com.fiap.crm.repository;

import com.fiap.crm.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
	@Override
	<S extends Endereco> S save(S entity);

	@Override
	Optional<Endereco> findById(Long aLong);

	List<Endereco> findAllByClienteId(Long id);

	void deleteAllByClienteId(Long id);
}
