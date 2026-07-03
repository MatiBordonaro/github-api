package com.github.tp.service;

import java.util.List;

import com.github.tp.dto.CommentDto;
import com.github.tp.dto.CreateCommentRequest;
import com.github.tp.dto.CreatePullRequestRequest;
import com.github.tp.dto.CreateRepoRequest;
import com.github.tp.dto.PullRequestDto;
import com.github.tp.dto.RepositoryDto;

public interface GitHubService {

    List<RepositoryDto> listRepositories();

    RepositoryDto createRepository(CreateRepoRequest request);

    PullRequestDto createPullRequest(String owner, String repo, CreatePullRequestRequest request);

    CommentDto createComment(String owner, String repo, int issueNumber, CreateCommentRequest request);
}
