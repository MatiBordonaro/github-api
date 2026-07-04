package com.github.tp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.tp.dto.CommentDto;
import com.github.tp.dto.CreateCommentRequest;
import com.github.tp.service.GitHubService;

@RestController
@RequestMapping("/api/repos/{owner}/{repo}/issues/{number}/comments")
public class CommentController {

    private final GitHubService gitHubService;

    public CommentController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto createComment(
            @PathVariable String owner,
            @PathVariable String repo,
            @PathVariable int number,
            @RequestBody CreateCommentRequest request) {
        return gitHubService.createComment(owner, repo, number, request);
    }
}
