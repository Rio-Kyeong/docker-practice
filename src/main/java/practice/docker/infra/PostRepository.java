package practice.docker.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.docker.domain.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
