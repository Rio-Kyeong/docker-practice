package practice.docker.database.application;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.docker.database.domain.PostEntity;
import practice.docker.database.error.PostErrorCode;
import practice.docker.database.infrastructure.PostRepository;
import practice.docker.database.presentation.dto.PostDto;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public UUID savePost(PostDto.CreateRequest createRequest, int userId) {
        PostEntity savePostEntity = postRepository.save(createRequest.toEntity(userId));

        return savePostEntity.getId();
    }

    public PostDto.ReadResponse findOnePost(UUID postId, int userId) {
        PostEntity postEntity = postRepository.findById(postId)
            .orElseThrow(PostErrorCode.POST_NOT_FOUND::toException);

        if (postEntity.getUserId() != userId) {
            throw PostErrorCode.NOT_MATCH_USER_POST.toException();
        }

        return PostDto.ReadResponse.of(postEntity);
    }
}
