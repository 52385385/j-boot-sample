package provider;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Apis {
	@Autowired
	private TokenStore ts;
	
	@RequestMapping(value = "/me", method = RequestMethod.GET)
	public Principal me(Principal user) {
		return user;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public void logout(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token != null && token.startsWith("Bearer")) {
			OAuth2AccessToken oAuth2AccessToken = ts.readAccessToken(token.split(" ")[1]);
			if (oAuth2AccessToken != null) {
				ts.removeAccessToken(oAuth2AccessToken);
				ts.removeRefreshToken(oAuth2AccessToken.getRefreshToken());
			}
		}
	}
}
