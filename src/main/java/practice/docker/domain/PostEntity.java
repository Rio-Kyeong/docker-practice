package practice.docker.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "POST")
public class PostEntity extends BaseEntity{
    @Column(name = "name")
    private String name;
}
