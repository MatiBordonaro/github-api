package com.github.tp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateRepoRequest(
    String name,
    String description,
    @JsonProperty("private") boolean isPrivate
) {}
