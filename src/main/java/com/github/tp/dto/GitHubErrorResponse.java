package com.github.tp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GitHubErrorResponse(
    String message,
    @JsonProperty("documentation_url") String documentationUrl
) {}
