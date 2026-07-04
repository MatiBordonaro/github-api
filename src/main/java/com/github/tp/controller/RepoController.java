package com.github.tp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.tp.dto.CreateRepoRequest;
import com.github.tp.dto.RepositoryDto;
import com.github.tp.service.GitHubService;

@RestController
@RequestMapping("/api/repos")
public class RepoController {

    private final GitHubService gitHubService;

    public RepoController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping
    public List<RepositoryDto> listRepositories() {
        return gitHubService.listRepositories();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RepositoryDto createRepository(@RequestBody CreateRepoRequest request) {
        return gitHubService.createRepository(request);
    }
}
