package com.github.tp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class GitHubConfig {

    @Bean
    public RestClient gitHubRestClient(
            @Value("${github.api.base-url}") String baseUrl,
            @Value("${GITHUB_TOKEN:}") String token) {
        if (token.isBlank()) {
            throw new RuntimeException("GITHUB_TOKEN no configurada");
        }
        return RestClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader("Authorization", "Bearer " + token)
            .defaultHeader("Accept", "application/vnd.github.v3+json")
            .build();
    }
}
