package practice.docker.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import practice.docker.domain.PostEntity;
import practice.docker.infra.PostRepository;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;

    @PostMapping("/post")
    public Long savePost(@RequestBody PostEntity postEntity) {
        PostEntity post = postRepository.save(postEntity);
        return post.getId();
    }

    @GetMapping("/post/{postId}")
    public PostEntity findOnePost(@PathVariable Long postId) {
        return postRepository.findById(postId).get();
    }
}
