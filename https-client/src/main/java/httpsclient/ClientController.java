package httpsclient;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final MyClient client;

    @GetMapping("/hello")
    public String hello(){
        return client.executeRequest();
    }
}
