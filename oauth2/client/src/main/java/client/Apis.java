package client;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Apis {

	@RequestMapping(value = "/demo", method = RequestMethod.GET)
	public String demo() {
		return "Public demo unsecured message.";
	}
	
	@RequestMapping(value = "/resources", method = RequestMethod.GET)
	public String resources() {
		return "Protected resources.";
	}
	
}
