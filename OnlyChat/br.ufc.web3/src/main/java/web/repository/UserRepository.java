package web.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import web.model.User;
@Repository
public interface UserRepository extends CrudRepository< User, Integer>{

    Optional<User> findById(Long receiverId);

   boolean existsById(Long id);

boolean existsByEmail(String email);


}
