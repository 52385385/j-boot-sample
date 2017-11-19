package jpamongo.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import jpamongo.model.common.Aqi;
import jpamongo.model.common.Info;
import jpamongo.model.common.Pm25inAqi;

@Service
public class Pm25inService {
	@Autowired
	private RestTemplate rt;
	@Value("${appConfig.pm25in.baseUri}")
	private String baseUri;
	@Value("${appConfig.pm25in.token}")
	private String token;
	
	private HttpHeaders headers() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return headers;
	}
	
	public Aqi fetchPm25BasicInfo(String city) {
		String url = String.format("%s?city=%s&token=%s", baseUri, city, token);
		HttpEntity<?> request = new HttpEntity<>(headers());
		ResponseEntity<List<Pm25inAqi>> response;
		try {
			response = rt
					.exchange(url, HttpMethod.GET, request, new ParameterizedTypeReference<List<Pm25inAqi>>(){});
			if (response == null) {
				Info.remoteError("null");
				return null;
			}
		} catch (RestClientException e) {
			Info.remoteError(e.getMessage().toString());
			return null;
		}
		List<Pm25inAqi> plain = response.getBody();
		Aqi aqi = new Aqi(plain);
		Info.ok();
		return aqi;
	}
}
