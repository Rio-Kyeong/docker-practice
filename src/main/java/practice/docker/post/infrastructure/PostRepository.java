package practice.docker.post.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.docker.post.domain.Post;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
}
