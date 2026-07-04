package com.github.tp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PullRequestDto(
    long id,
    int number,
    String title,
    String body,
    String state,
    @JsonProperty("html_url") String htmlUrl,
    @JsonProperty("created_at") String createdAt
) {}
