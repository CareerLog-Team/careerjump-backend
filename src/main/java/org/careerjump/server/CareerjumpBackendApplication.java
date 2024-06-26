package org.careerjump.server;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.TimeZone;

@SpringBootApplication
public class CareerjumpBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(CareerjumpBackendApplication.class, args);
    }

    @PostConstruct
    public void setTime() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }
}
