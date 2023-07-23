package practice.docker.post.domain;

import lombok.*;
import practice.docker.shared.core.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
public class Post extends BaseEntity {
    @Column(name = "name")
    private String name;
}
