package practice.docker.post.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import practice.docker.post.domain.Post;
import practice.docker.post.infrastructure.PostRepository;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;

    @PostMapping("/post")
    public String savePost(@RequestBody Post postEntity) {
        Post post = postRepository.save(postEntity);
        return post.getId().toString();
    }

    @GetMapping("/post/{postId}")
    public Post findOnePost(@PathVariable UUID postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("없음")
        );
    }
}
