package com.github.tp.dto;

public record CreatePullRequestRequest(
    String title,
    String body,
    String head,
    String base
) {}
