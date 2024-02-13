package practice.docker.post.infrastructure;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import practice.docker.post.domain.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, UUID> {
}
