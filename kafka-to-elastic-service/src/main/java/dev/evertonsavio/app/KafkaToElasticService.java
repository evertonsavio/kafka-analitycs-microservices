package dev.evertonsavio.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("dev.evertonsavio")
public class KafkaToElasticService {

    public static void main(String[] args) {
        SpringApplication.run(KafkaToElasticService.class, args);
    }
}
