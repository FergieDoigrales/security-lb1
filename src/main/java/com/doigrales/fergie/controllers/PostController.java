package com.doigrales.fergie.controllers;

import com.doigrales.fergie.DTOs.PostCreateRequest;

import com.doigrales.fergie.DTOs.PostResponse;
import com.doigrales.fergie.models.Post;
import com.doigrales.fergie.models.User;
import com.doigrales.fergie.repositories.PostRepository;
import com.doigrales.fergie.repositories.UserRepository;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }
    @GetMapping
    public List<PostResponse> getData() {
        return postRepository.findAll().stream().map(PostResponse::from).toList();
    }
    @PostMapping
    public PostResponse create(@Valid @RequestBody PostCreateRequest req, Authentication auth) {
        var user = userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new IllegalStateException("Authenticated user not found"));

        Post post = new Post();
        post.setTitle(req.getTitle());
        post.setContent(req.getContent());
        post.setCreatedAt(Instant.now());
        post.setAuthor(user);
        post.setAuthorUsername(user.getUsername());

        return PostResponse.from(postRepository.save(post));
    }
}
