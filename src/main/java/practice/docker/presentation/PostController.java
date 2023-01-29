package practice.docker.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import practice.docker.domain.PostEntity;
import practice.docker.infra.PostRepository;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;

    @PostMapping("/post")
    public String savePost(@RequestBody PostEntity postEntity) {
        PostEntity post = postRepository.save(postEntity);
        return post.getId().toString();
    }

    @GetMapping("/post/{postId}")
    public PostEntity findOnePost(@PathVariable UUID postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("없음")
        );
    }
}
