package practice.docker.post.application;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.docker.post.domain.PostEntity;
import practice.docker.post.infrastructure.PostRepository;
import practice.docker.post.presentation.dto.PostDto;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void savePost(PostDto.CreateRequest createRequest) {
        postRepository.save(createRequest.toEntity());
    }

    public PostDto.Response findOnePost(UUID postId) {
        if (postId == null) {
            throw new IllegalArgumentException("Post ID cannot be null.");
        }

        PostEntity postEntity = postRepository.findById(postId).orElseThrow(
            () -> new IllegalArgumentException(String.format("No post found with ID %s.", postId)));

        return PostDto.Response.of(postEntity);
    }
}
