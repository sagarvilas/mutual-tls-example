package httpsclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HttpsClientApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(HttpsClientApplication.class, args);
    }

}
