package practice.docker.database.infrastructure;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import practice.docker.database.domain.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, UUID> {
}
