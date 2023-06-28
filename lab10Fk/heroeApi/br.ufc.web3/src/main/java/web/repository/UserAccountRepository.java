package web.repository;
import web.model.UserAccount;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends CrudRepository< UserAccount, Integer>{

	
	@Query("select u from UserAccount u where u.name like %?1")
    List<UserAccount> findUserByUsername(String username);

}