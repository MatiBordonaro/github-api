package com.github.tp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class GitHubConfig {

    private static final Logger log = LoggerFactory.getLogger(GitHubConfig.class);

    @Bean
    public RestClient gitHubRestClient(@Value("${github.api.base-url}") String baseUrl) {
        String token = System.getenv("GITHUB_TOKEN");
        RestClient.Builder builder = RestClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader("Accept", "application/vnd.github.v3+json");
        if (token == null || token.isBlank()) {
            log.warn("GITHUB_TOKEN no configurada. Las llamadas a la API fallarán.");
        } else {
            builder.defaultHeader("Authorization", "Bearer " + token);
        }
        return builder.build();
    }
}
