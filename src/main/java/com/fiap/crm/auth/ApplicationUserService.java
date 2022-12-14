package com.fiap.crm.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {
	private final ApplicationUserDAO applicationUserDao;

	@Autowired
	public ApplicationUserService(@Qualifier("teste") ApplicationUserDAO applicationUserDao) {
		this.applicationUserDao = applicationUserDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return applicationUserDao
				.selectApplicationUserByUsername(username)
				.orElseThrow(() ->
						new UsernameNotFoundException(String.format("Usuário %s não encontrado", username))
				);
	}
}
