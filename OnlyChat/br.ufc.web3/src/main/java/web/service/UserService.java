package web.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.model.User;
import web.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	User user = new User();

	public Iterable<User> getsUser() {

		return userRepository.findAll();
	}

	public User addUser(User user) {

		return userRepository.save(user);

	}
	
	public void removeUserById(long id) {
	    Optional<User> optionalUser = userRepository.findById(user.getId());

	    if (optionalUser.isPresent()) {
	        User user = optionalUser.get();
	        userRepository.delete(user);
	    } else {
	        throw new IllegalArgumentException("Não foi encontrado nenhum herói com o código fornecido: " + user.getId());
	    }
	}

	
	public void removeUser(User user) {
		userRepository.delete(user);
	}

	public User createUser(User user2) {
		return null;
	}

	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}
	

    public List<User> getAllUsers() {
        return null;
    }

	
    public User registerUser(User user) {
        // Verificar se o e-mail já está cadastrado
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("E-mail já está em uso");
        }

        // Implementar a lógica para salvar o usuário no banco de dados
        User savedUser = userRepository.save(user);

        // Você pode realizar outras operações relacionadas ao registro do usuário aqui, como enviar um e-mail de confirmação, etc.

        return savedUser;
    }

}
