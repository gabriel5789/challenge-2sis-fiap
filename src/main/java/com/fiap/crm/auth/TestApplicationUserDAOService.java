package com.fiap.crm.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.fiap.crm.security.ApplicationUserRole.*;

@Repository("teste")
public class TestApplicationUserDAOService implements ApplicationUserDAO {

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public TestApplicationUserDAOService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
		return getApplicationUsers()
				.stream()
				.filter(applicationUser -> username.equals(applicationUser.getUsername()))
				.findFirst();
	}

	private List<ApplicationUser> getApplicationUsers() {
		return Lists.newArrayList(
				new ApplicationUser(
						"gabriel.guedes@gmail.com",
						passwordEncoder.encode("clientepass123"),
						CLIENTE.getGrantedAuthorities(),
						true,
						true,
						true,
						true
				),
				new ApplicationUser(
						"funcionario@bancopan.com",
						passwordEncoder.encode("funcionariopass123"),
						FUNCIONARIO.getGrantedAuthorities(),
						true,
						true,
						true,
						true
				)
		);
	}

}
