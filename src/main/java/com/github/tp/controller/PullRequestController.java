package com.github.tp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.tp.dto.CreatePullRequestRequest;
import com.github.tp.dto.PullRequestDto;
import com.github.tp.service.GitHubService;

@RestController
@RequestMapping("/api/repos/{owner}/{repo}/pulls")
public class PullRequestController {

    private final GitHubService gitHubService;

    public PullRequestController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PullRequestDto createPullRequest(
            @PathVariable String owner,
            @PathVariable String repo,
            @RequestBody CreatePullRequestRequest request) {
        return gitHubService.createPullRequest(owner, repo, request);
    }
}
