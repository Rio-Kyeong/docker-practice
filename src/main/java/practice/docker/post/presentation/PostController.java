package practice.docker.post.presentation;

import static practice.docker.core.util.HttpServletUtil.createUriWithPostIdFromCurrentRequest;

import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.docker.core.presentation.SuccessResponse;
import practice.docker.post.application.PostService;
import practice.docker.post.presentation.dto.PostDto;
import practice.docker.post.presentation.dto.PostDto.ReadResponse;

@RestController
@RequestMapping("/users/{userId}/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<SuccessResponse<Object>> savePost(
        @PathVariable int userId,
        @Valid @RequestBody PostDto.CreateRequest createRequest) {
        UUID newPostId = postService.savePost(createRequest, userId);

        return ResponseEntity.created(createUriWithPostIdFromCurrentRequest(newPostId))
            .body(SuccessResponse.builder()
                .build());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<SuccessResponse<ReadResponse>> findOnePost(
        @PathVariable int userId,
        @PathVariable UUID postId) {
        ReadResponse readResponse = postService.findOnePost(postId, userId);

        return ResponseEntity.ok()
            .body(SuccessResponse.<ReadResponse>builder()
                .result(readResponse)
                .build());
    }
}
