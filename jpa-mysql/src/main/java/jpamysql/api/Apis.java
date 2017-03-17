package jpamysql.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jpamysql.model.Mail;
import jpamysql.service.UserMailService;

@Controller
public class Apis {
	@Autowired
	private UserMailService ums;
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> getAllMails(@RequestParam(value = "userid", required = true) String userid) {
		return new ResponseEntity<List<Mail>>(ums.getMail(userid), HttpStatus.OK);
	}
}
