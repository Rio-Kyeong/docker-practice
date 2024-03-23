package practice.docker.database.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import practice.docker.core.domain.entity.BaseEntity;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity(name = "post")
public class PostEntity extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "title", nullable = false, length = 45)
    private String title;

    @Column(name = "contents", nullable = false, length = 200)
    private String contents;
}
