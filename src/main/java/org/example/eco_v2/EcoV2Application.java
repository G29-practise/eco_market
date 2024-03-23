package org.example.eco_v2;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients
@RequiredArgsConstructor
public class EcoV2Application {

    public static void main(String[] args) {
        SpringApplication.run(EcoV2Application.class, args);
    }

}
