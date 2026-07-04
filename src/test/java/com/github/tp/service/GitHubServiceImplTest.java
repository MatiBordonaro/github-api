package com.github.tp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

import com.github.tp.dto.CommentDto;
import com.github.tp.dto.CreateCommentRequest;
import com.github.tp.dto.CreatePullRequestRequest;
import com.github.tp.dto.CreateRepoRequest;
import com.github.tp.dto.PullRequestDto;
import com.github.tp.dto.RepositoryDto;
import com.github.tp.model.GitHubApiException;

@ExtendWith(MockitoExtension.class)
class GitHubServiceImplTest {

    @Mock
    private RestClient restClient;

    @Mock
    private RestClient.RequestHeadersUriSpec getUriSpec;

    @Mock
    private RestClient.RequestBodyUriSpec postUriSpec;

    @Mock
    private RestClient.RequestBodySpec requestBodySpec;

    @Mock
    private RestClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private RestClient.ResponseSpec responseSpec;

    private GitHubServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new GitHubServiceImpl(restClient);
    }

    @Test
    void listRepositoriesDeberiaRetornarListaDeRepos() {
        RepositoryDto repo1 = new RepositoryDto(1, "repo1", "user/repo1", false,
            "https://github.com/user/repo1", "desc", "public", "main");
        RepositoryDto repo2 = new RepositoryDto(2, "repo2", "user/repo2", true,
            "https://github.com/user/repo2", null, "private", "main");
        RepositoryDto[] reposArray = {repo1, repo2};

        doReturn(getUriSpec).when(restClient).get();
        doReturn(getUriSpec).when(getUriSpec).uri("/user/repos?per_page=100");
        doReturn(responseSpec).when(getUriSpec).retrieve();
        doReturn(responseSpec).when(responseSpec).onStatus(any(), any());
        doReturn(reposArray).when(responseSpec).body(RepositoryDto[].class);

        var result = service.listRepositories();

        assertEquals(2, result.size());
        assertEquals("repo1", result.get(0).name());
    }

    @Test
    void createRepositoryDeberiaRetornarRepoCreado() {
        CreateRepoRequest request = new CreateRepoRequest("nuevo-repo", "desc", false);
        RepositoryDto expected = new RepositoryDto(3, "nuevo-repo", "user/nuevo-repo", false,
            "https://github.com/user/nuevo-repo", "desc", "public", "main");

        doReturn(postUriSpec).when(restClient).post();
        doReturn(requestBodySpec).when(postUriSpec).uri("/user/repos");
        doReturn(requestBodySpec).when(requestBodySpec).body(request);
        doReturn(responseSpec).when(requestBodySpec).retrieve();
        doReturn(responseSpec).when(responseSpec).onStatus(any(), any());
        doReturn(expected).when(responseSpec).body(RepositoryDto.class);

        var result = service.createRepository(request);

        assertNotNull(result);
        assertEquals("nuevo-repo", result.name());
    }

    @Test
    void createPullRequestDeberiaRetornarPRCreado() {
        CreatePullRequestRequest request = new CreatePullRequestRequest(
            "Mi PR", "Descripción", "feature-branch", "main");
        PullRequestDto expected = new PullRequestDto(
            1, 42, "Mi PR", "Descripción", "open",
            "https://github.com/user/repo/pull/42", null, null, null);

        doReturn(postUriSpec).when(restClient).post();
        doReturn(requestBodySpec).when(postUriSpec).uri(
            "/repos/{owner}/{repo}/pulls", "user", "repo");
        doReturn(requestBodySpec).when(requestBodySpec).body(request);
        doReturn(responseSpec).when(requestBodySpec).retrieve();
        doReturn(responseSpec).when(responseSpec).onStatus(any(), any());
        doReturn(expected).when(responseSpec).body(PullRequestDto.class);

        var result = service.createPullRequest("user", "repo", request);

        assertNotNull(result);
        assertEquals("Mi PR", result.title());
        assertEquals(42, result.number());
    }

    @Test
    void createCommentDeberiaRetornarComentarioCreado() {
        CreateCommentRequest request = new CreateCommentRequest("Revisado OK");
        CommentDto expected = new CommentDto(1, "Revisado OK", null, null, null);

        doReturn(postUriSpec).when(restClient).post();
        doReturn(requestBodySpec).when(postUriSpec).uri(
            "/repos/{owner}/{repo}/issues/{issue_number}/comments",
            "user", "repo", 5);
        doReturn(requestBodySpec).when(requestBodySpec).body(request);
        doReturn(responseSpec).when(requestBodySpec).retrieve();
        doReturn(responseSpec).when(responseSpec).onStatus(any(), any());
        doReturn(expected).when(responseSpec).body(CommentDto.class);

        var result = service.createComment("user", "repo", 5, request);

        assertNotNull(result);
        assertEquals("Revisado OK", result.body());
    }

    @Test
    void listRepositoriesDeberiaLanzarExcepcionEnError() {
        doReturn(getUriSpec).when(restClient).get();
        doReturn(getUriSpec).when(getUriSpec).uri("/user/repos?per_page=100");
        doReturn(responseSpec).when(getUriSpec).retrieve();
        doAnswer(invocation -> { throw new GitHubApiException(401, "Error al listar repositorios"); })
            .when(responseSpec).onStatus(any(), any());

        assertThrows(GitHubApiException.class, () -> service.listRepositories());
    }

    @Test
    void createRepositoryDeberiaLanzarExcepcionEnError() {
        CreateRepoRequest request = new CreateRepoRequest("repo", null, false);

        doReturn(postUriSpec).when(restClient).post();
        doReturn(requestBodySpec).when(postUriSpec).uri("/user/repos");
        doReturn(requestBodySpec).when(requestBodySpec).body(request);
        doReturn(responseSpec).when(requestBodySpec).retrieve();
        doAnswer(invocation -> { throw new GitHubApiException(422, "Error al crear repositorio"); })
            .when(responseSpec).onStatus(any(), any());

        assertThrows(GitHubApiException.class, () -> service.createRepository(request));
    }
}
