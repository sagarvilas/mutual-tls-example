package httpswebclient;


import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.util.ResourceUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.security.KeyStore;


@Configuration
public class Config {

    @Value("${client.ssl.key-store-password}")
    private String keyStorePassword;
    @Value("${client.ssl.key-store}")
    private String keyStorePath;
    @Value("${client.ssl.trust-store-password}")
    private String trustStorePassword;
    @Value("${client.ssl.trust-store}")
    private String trustStorePath;


    @Bean
    public WebClient webclient() throws Exception {
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        KeyStore keyStore = KeyStore.getInstance("jks");
        keyStore.load(new FileInputStream(ResourceUtils.getFile(keyStorePath)), keyStorePassword.toCharArray());
        keyManagerFactory.init(keyStore, keyStorePassword.toCharArray());


        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        KeyStore trustStore = KeyStore.getInstance("jks");
        trustStore.load(new FileInputStream((ResourceUtils.getFile(trustStorePath))), trustStorePassword.toCharArray());
        trustManagerFactory.init(trustStore);

        SslContext sslContext = SslContextBuilder
                .forClient()
                .keyManager(keyManagerFactory)
                .trustManager(trustManagerFactory)
                .build();

        HttpClient client = HttpClient.create().secure(t -> t.sslContext(sslContext));

        return WebClient.builder().clientConnector(new ReactorClientHttpConnector(client)).build();

    }
}
