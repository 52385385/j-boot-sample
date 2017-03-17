package jpamysql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jpamysql.model.Mail;
@Repository
public interface MailRepository extends JpaRepository<Mail, String> {
	List<Mail> findByUserId(String userid);
}
