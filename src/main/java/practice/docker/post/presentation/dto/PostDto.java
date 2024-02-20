package practice.docker.post.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import practice.docker.core.util.ModelMapperUtil;
import practice.docker.post.domain.PostEntity;

public class PostDto {

    @Setter
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CreateRequest {

        @NotBlank(message = "post title cannot be empty or null")
        @Schema(description = "게시글 명", example = "게시글 제목", requiredMode = RequiredMode.REQUIRED)
        private String title;

        @NotBlank(message = "post contents cannot be empty or null")
        @Schema(description = "게시글 내용", example = "게시글 내용", requiredMode = RequiredMode.REQUIRED)
        private String contents;

        public PostEntity toEntity(int userId) {
            return PostEntity.builder()
                .title(this.title)
                .contents(this.contents)
                .userId(userId)
                .build();
        }
    }

    @Setter
    @Getter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ReadResponse {

        @Schema(description = "게시글 ID", example = "11eecefd-f24a-3049-b579-9fea36fac2b2")
        private UUID postId;

        @Schema(description = "고객 ID", example = "12345678")
        private int userId;

        @Schema(description = "게시글 명", example = "게시글 제목")
        private String title;

        @Schema(description = "게시글 내용", example = "게시글 내용")
        private String contents;

        @Schema(description = "데이터 최초 생성 일시", example = "2024-02-19T17:07:38")
        private LocalDateTime createdAt;

        public static ReadResponse of(PostEntity postEntity) {
            return ModelMapperUtil.modelMapper().map(postEntity, ReadResponse.class);
        }

        public static List<ReadResponse> of(List<PostEntity> postEntityList) {
            return postEntityList.stream()
                .map(ReadResponse::of)
                .collect(Collectors.toList());
        }
    }
}
