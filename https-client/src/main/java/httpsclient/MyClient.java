package httpsclient;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MyClient {

    private final RestTemplate restTemplate;

    public MyClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String executeRequest() {
        var headers = new HttpHeaders();
        headers.add("client-type", "spring-client");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange("https://localhost:7443/hello", HttpMethod.GET, entity, String.class);
        return response.getBody();
    }
}
