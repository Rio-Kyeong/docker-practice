package practice.docker.post.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
        private String title;

        @NotBlank(message = "post contents cannot be empty or null")
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

        private UUID id;
        private int userId;
        private String title;
        private String contents;
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
