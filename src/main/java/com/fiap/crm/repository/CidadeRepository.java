package com.fiap.crm.repository;

import com.fiap.crm.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	@Override
	<S extends Cidade> S save(S entity);

	@Override
	Optional<Cidade> findById(Long aLong);
}
