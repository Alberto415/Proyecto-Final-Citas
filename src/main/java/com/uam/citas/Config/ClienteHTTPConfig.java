package com.uam.citas.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClienteHTTPConfig {

    @Bean
    public WebClient clienteHttp() {
        return WebClient.builder().build();
    }
}
