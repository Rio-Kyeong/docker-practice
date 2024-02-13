package practice.docker.core.domain.entity;

import com.fasterxml.uuid.Generators;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    protected UUID id;

    @CreatedDate
    protected LocalDateTime createdAt;

    @LastModifiedDate
    protected LocalDateTime updatedAt;

    @PrePersist
    public void initSequentialUUID() {
        final String UUID_SPLIT = "-";
        UUID uuid = Generators.timeBasedGenerator().generate();
        String[] uuidSplitArray = uuid.toString().split(UUID_SPLIT);
        String sequentialUUIDString = uuidSplitArray[2] + uuidSplitArray[1] + uuidSplitArray[0] + uuidSplitArray[3] + uuidSplitArray[4];
        this.id = UUID.fromString(
            new StringBuilder(sequentialUUIDString)
                .insert(8, UUID_SPLIT)
                .insert(13, UUID_SPLIT)
                .insert(18, UUID_SPLIT)
                .insert(23, UUID_SPLIT)
                .toString()
        );
    }
}
