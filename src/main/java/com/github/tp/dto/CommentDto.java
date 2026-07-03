package com.github.tp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CommentDto(
    long id,
    String body,
    CommentUser user,
    @JsonProperty("created_at") String createdAt,
    @JsonProperty("updated_at") String updatedAt
) {
    public record CommentUser(String login) {}
}
