package practice.docker.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.docker.domain.PostEntity;

import java.util.UUID;

public interface PostRepository extends JpaRepository<PostEntity, UUID> {
}
