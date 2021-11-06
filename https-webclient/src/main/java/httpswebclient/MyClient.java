package httpswebclient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class MyClient {

    private final WebClient client;

  public Mono<String> executeRequest(){

      return  client.get().uri(URI.create("https://localhost:7443/hello")).retrieve().bodyToMono(String.class);
  }
}
