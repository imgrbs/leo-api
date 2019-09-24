package app.leo.matching.adapters;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import app.leo.matching.DTO.MatchDTO;

@Service
public class MatchManagementAdapter {
	@Value("${matchmanagement.api.url}")
	private String matchManagementApiUrl;

	private Logger logger = LoggerFactory.getLogger(MatchManagementAdapter.class);

	public MatchDTO getMatchByMatchId(String token, long matchId) {
		RestTemplate restTemplate = new RestTemplate();
		String url = String.format(matchManagementApiUrl + "matches/" + matchId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization",  token);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<MatchDTO> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity,
				new ParameterizedTypeReference<MatchDTO>() {
				});
		return responseEntity.getBody();
	}
}
