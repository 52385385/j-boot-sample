package jpamysql.service;

import java.util.List;

import jpamysql.model.Mail;

public interface UserMailService {
	List<Mail> getMail(String userid);
}
