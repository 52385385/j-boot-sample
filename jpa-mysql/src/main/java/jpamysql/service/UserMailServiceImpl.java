package jpamysql.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jpamysql.model.Mail;
import jpamysql.model.User;
import jpamysql.repository.MailRepository;
import jpamysql.repository.UserRepository;
@Service
public class UserMailServiceImpl implements UserMailService {
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UserRepository ur;
	
	@Autowired
	private MailRepository mr;

	@Override
	public List<Mail> getMail(String userid) {
		List<Mail> ret = mr.findByUserId(userid);
		return ret;
	}

}
