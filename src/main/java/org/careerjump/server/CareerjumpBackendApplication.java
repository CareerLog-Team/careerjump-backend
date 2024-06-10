package org.careerjump.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CareerjumpBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CareerjumpBackendApplication.class, args);
    }



}
