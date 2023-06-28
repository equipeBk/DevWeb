package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.model.UserAccount;
import web.repository.UserAccountRepository;

@Service
public class UserService {

	@Autowired
	UserAccountRepository userAccountRepository;
	UserAccount userAccount = new UserAccount();

	public Iterable<UserAccount> getsUsers() {

		return userAccountRepository.findAll();
	}
	
}
