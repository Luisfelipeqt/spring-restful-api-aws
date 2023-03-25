package br.com.erudio.services;

import br.com.erudio.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UsersServices implements UserDetailsService {
	
	private Logger logger = Logger.getLogger(UsersServices.class.getName());
	
	@Autowired
	UsersRepository repository;

	public UsersServices(UsersRepository repository) {
		this.repository = repository;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Finding one user by name " + username + "!");
		var user = repository.findByUsername(username);
		if(user != null){
			return user;
		}
		else {
			throw new UsernameNotFoundException("Username " + username + " not found!");
		}
	}
}
