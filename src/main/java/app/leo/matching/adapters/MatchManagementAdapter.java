package app.leo.matching.adapters;


import app.leo.matching.DTO.MatchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class MatchManagementAdapter {
    @Value("${MatchManagement_API_URL}")
    private String matchManagementApiUrl;

    private Logger logger = LoggerFactory.getLogger(MatchManagementAdapter.class);
    public MatchDTO getMatchByMatchId(String token,long matchId){
        MatchDTO match = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = String.format(matchManagementApiUrl + "matches/" + matchId);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", token);
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<>(headers);
            System.out.println(entity.getHeaders());
            ResponseEntity<MatchDTO> responseEntity =restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<MatchDTO>(){}
            );
            match = responseEntity.getBody();
        }catch (NullPointerException exception){
            logger.warn(exception.getMessage());
        }
        return match;
    }
}
