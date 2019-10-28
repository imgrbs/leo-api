package app.leo.matching.adapters;

import app.leo.matching.DTO.ApplicantProfile;
import app.leo.matching.DTO.DocumentDTO;
import app.leo.matching.DTO.RecruiterProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ProfileAdapter {

    @Value("${profile.api.url}")
    private String profileApiUrl;

    public RecruiterProfile getRecruiterProfileByUserId(String token, long userId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = profileApiUrl + "/profile/" + userId +"/recruiter";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<RecruiterProfile> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity,
                new ParameterizedTypeReference<RecruiterProfile>() {
                });
        return responseEntity.getBody();
    }

    public ApplicantProfile getApplicantProfileByUserId(String token, long userId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = profileApiUrl + "/profile/" + userId + "/applicant";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<ApplicantProfile> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity,
                new ParameterizedTypeReference<ApplicantProfile>() {
                });
        return responseEntity.getBody();
    }


    public List<DocumentDTO> getDocumentByDocumentIdList(String token,List<Long> documentIdList){
        RestTemplate restTemplate = new RestTemplate();

        String url = createUrlToGetDocumentList(documentIdList);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<List<Long>> entity = new HttpEntity<>(headers);
        ResponseEntity<List<DocumentDTO>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity,
                new ParameterizedTypeReference<List<DocumentDTO>> () {
                });
        return responseEntity.getBody();
    }

    private String createUrlToGetDocumentList(List<Long> documentIdList) {
        StringBuilder url = new StringBuilder(profileApiUrl + "/profile/documents/get-in-list?idlist=");
        int i = 0;
        for(long id:documentIdList){
            if(i ==1){
                url.append(",");
            }
            url.append(id);
            i=1;
        }
        return url.toString();
    }
}
