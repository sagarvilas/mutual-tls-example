package httpswebclient;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final MyClient client;

    @GetMapping("/hello")
    public Mono<String> hello() {
        return client.executeRequest();
    }
}
