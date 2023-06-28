package web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.service.UserService;
import web.model.Heroe;
import web.model.UserAccount;
import web.repository.UserAccountRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {
   
	@Autowired
	private UserService userService;
   
    @GetMapping
    public Iterable<UserAccount> getUsers() {
		return userService.getsUsers();
	}

}
