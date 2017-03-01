package resttemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Apis {
	@Autowired
	@Qualifier("myRestTemplate")
	private RestTemplate rt;
	
	@Value("${baseurl}")
	private String baseurl;
	
	@Bean
	@Scope("request")
	HttpHeaders headers() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return headers;
	}
	
	@GetMapping("/getobject")
	public UserModel getString(@RequestParam(value = "param", required = true) String param, 
			@PathVariable(value = "id") String id) {
		String url = baseurl + "/{id}/foo";
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		data.add("param", param);
		Map<String, Object> pathvars = new HashMap<String, Object>();
		pathvars.put("id", id);
		HttpEntity<?> request = new HttpEntity<>(data, headers());
		ResponseEntity<UserModel> response = 
				rt.exchange(url, HttpMethod.GET, request, UserModel.class, pathvars);
		UserModel ret = response.getBody();
		return ret;
	}
	
	@GetMapping("/postobject")
	public List<UserModel> postObject (
			@RequestHeader(value = "Authorization", required = true) String token) {
		String url = baseurl + "/foo";
		HttpHeaders headers = headers();
		headers.set("Authorization", token);
		HttpEntity<?> request = new HttpEntity<>(headers);
		ResponseEntity<List<UserModel>> response = 
				rt.exchange(url, HttpMethod.POST, request, 
						new ParameterizedTypeReference<List<UserModel>>(){});
		List<UserModel> ret = response.getBody();
		return ret;
	}
	
	class UserModel {
		private String username;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		@Override
		public int hashCode() {
			final int PRIME = 31;
			return PRIME * 17 + (username == null ? 0 : username.hashCode());
		}
		@Override
		public boolean equals(Object o) {
			if (!(o instanceof UserModel)) {
				return false;
			}
			UserModel u = (UserModel) o;
			return u.getUsername().equals(username);
		}
		@Override
		public String toString() {
			return String.format("username: %s", username);
		}
	}
}
