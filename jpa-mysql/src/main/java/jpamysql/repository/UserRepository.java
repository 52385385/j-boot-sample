package jpamysql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jpamysql.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, String> {
	User findById(String id);
	List<User> findAllCustom();
}
