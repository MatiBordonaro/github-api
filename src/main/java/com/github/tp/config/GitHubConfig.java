package com.github.tp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class GitHubConfig {

    @Bean
    public RestClient gitHubRestClient(@Value("${github.api.base-url}") String baseUrl) {
        String token = System.getenv("GITHUB_TOKEN");
        if (token == null || token.isBlank()) {
            throw new IllegalStateException(
                "Variable de entorno GITHUB_TOKEN no configurada");
        }
        return RestClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader("Authorization", "Bearer " + token)
            .defaultHeader("Accept", "application/vnd.github.v3+json")
            .build();
    }
}
