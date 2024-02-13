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

        public PostEntity toEntity() {
            return PostEntity.builder()
                .title(this.title)
                .build();
        }
    }

    @Setter
    @Getter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {

        private UUID id;
        private String title;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static Response of(PostEntity postEntity) {
            return ModelMapperUtil.modelMapper().map(postEntity, Response.class);
        }

        public static List<Response> of(List<PostEntity> postEntityList) {
            return postEntityList.stream()
                .map(Response::of)
                .collect(Collectors.toList());
        }
    }
}
