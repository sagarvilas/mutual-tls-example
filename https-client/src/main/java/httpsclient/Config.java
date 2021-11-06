package httpsclient;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

@Configuration
public class Config {
    @Value("${client.ssl.key-store-password}")
    private String keyStorePassword;
    @Value("${client.ssl.key-store}")
    private String keyStore;
    @Value("${client.ssl.trust-store-password}")
    private String trustStorePassword;
    @Value("${client.ssl.trust-store}")
    private String trustStore;


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) throws Exception {

        SSLContext sslContext = SSLContextBuilder
                .create()
                .loadKeyMaterial(ResourceUtils.getFile(keyStore), keyStorePassword.toCharArray(), keyStorePassword.toCharArray())
                .loadTrustMaterial(ResourceUtils.getFile(trustStore), trustStorePassword.toCharArray())
                .build();

        CloseableHttpClient client = HttpClients.custom()
                .setSslcontext(sslContext)
                .build();

        return builder
                .requestFactory(() -> new HttpComponentsClientHttpRequestFactory(client))
                .build();
    }

}
