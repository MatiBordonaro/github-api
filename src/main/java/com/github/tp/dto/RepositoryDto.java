package com.github.tp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RepositoryDto(
    long id,
    String name,
    @JsonProperty("full_name") String fullName,
    @JsonProperty("private") boolean isPrivate,
    @JsonProperty("html_url") String htmlUrl,
    String description,
    String visibility,
    @JsonProperty("default_branch") String defaultBranch
) {}
