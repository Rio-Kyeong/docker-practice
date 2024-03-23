package practice.docker.database.presentation;

import static practice.docker.core.util.HttpServletUtil.createUriWithPostIdFromCurrentRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import practice.docker.database.application.PostService;
import practice.docker.database.presentation.dto.PostDto;
import practice.docker.database.presentation.dto.PostDto.ReadResponse;

@RestController
@RequestMapping("/users/{userId}/post")
@RequiredArgsConstructor
@Tag(name = "Post", description = "Post APIs")
public class PostController {

    private final PostService postService;

    @Operation(summary = "Post write a post", operationId = "/users/{userId}/post")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "게시글 생성 성공")
    })
    @PostMapping
    public ResponseEntity<SuccessResponse<Object>> savePost(
        @Parameter(description = "고객 ID", example = "12345678", required = true) @PathVariable int userId,
        @Valid @RequestBody PostDto.CreateRequest createRequest) {
        UUID newPostId = postService.savePost(createRequest, userId);

        return ResponseEntity.created(createUriWithPostIdFromCurrentRequest(newPostId))
            .body(SuccessResponse.builder()
                .build());
    }

    @Operation(summary = "Get view single post", operationId = "/users/{userId}/post/{postId}")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "단건 게시글 조회 성공"),
        @ApiResponse(responseCode = "400", description = "입력된 회원과 찜 목록의 회원이 불일치"),
        @ApiResponse(responseCode = "404", description = "게시글 정보 없음")
    })
    @GetMapping("/{postId}")
    public ResponseEntity<SuccessResponse<ReadResponse>> findOnePost(
        @Parameter(description = "고객 ID", example = "12345678", required = true) @PathVariable int userId,
        @Parameter(description = "게시글 ID", example = "11eecefd-f24a-3049-b579-9fea36fac2b2", required = true) @PathVariable UUID postId) {
        ReadResponse readResponse = postService.findOnePost(postId, userId);

        return ResponseEntity.ok()
            .body(SuccessResponse.<ReadResponse>builder()
                .result(readResponse)
                .build());
    }
}
