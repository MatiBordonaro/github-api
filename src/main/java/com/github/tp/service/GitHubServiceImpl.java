package com.github.tp.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.github.tp.dto.CommentDto;
import com.github.tp.dto.CreateCommentRequest;
import com.github.tp.dto.CreatePullRequestRequest;
import com.github.tp.dto.CreateRepoRequest;
import com.github.tp.dto.GitHubErrorResponse;
import com.github.tp.dto.PullRequestDto;
import com.github.tp.dto.RepositoryDto;
import com.github.tp.model.GitHubApiException;

@Service
public class GitHubServiceImpl implements GitHubService {

    private final RestClient restClient;

    public GitHubServiceImpl(RestClient gitHubRestClient) {
        this.restClient = gitHubRestClient;
    }

    @Override
    public List<RepositoryDto> listRepositories() {
        RepositoryDto[] repos = restClient.get()
            .uri("/user/repos?per_page=100")
            .retrieve()
            .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                (request, response) -> {
                    throw new GitHubApiException(
                        response.getStatusCode().value(),
                        "Error al listar repositorios");
                })
            .body(RepositoryDto[].class);
        return Arrays.asList(repos);
    }

    @Override
    public RepositoryDto createRepository(CreateRepoRequest request) {
        return restClient.post()
            .uri("/user/repos")
            .body(request)
            .retrieve()
            .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                (req, response) -> {
                    throw new GitHubApiException(
                        response.getStatusCode().value(),
                        "Error al crear repositorio");
                })
            .body(RepositoryDto.class);
    }

    @Override
    public PullRequestDto createPullRequest(String owner, String repo, CreatePullRequestRequest request) {
        return restClient.post()
            .uri("/repos/{owner}/{repo}/pulls", owner, repo)
            .body(request)
            .retrieve()
            .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                (req, response) -> {
                    throw new GitHubApiException(
                        response.getStatusCode().value(),
                        "Error al crear Pull Request");
                })
            .body(PullRequestDto.class);
    }

    @Override
    public CommentDto createComment(String owner, String repo, int issueNumber, CreateCommentRequest request) {
        return restClient.post()
            .uri("/repos/{owner}/{repo}/issues/{issue_number}/comments", owner, repo, issueNumber)
            .body(request)
            .retrieve()
            .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                (req, response) -> {
                    throw new GitHubApiException(
                        response.getStatusCode().value(),
                        "Error al crear comentario");
                })
            .body(CommentDto.class);
    }
}
