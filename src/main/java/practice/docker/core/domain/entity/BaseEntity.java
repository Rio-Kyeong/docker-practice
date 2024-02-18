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

    /**
     * UUID 는 36자로 VARCHAR 타입으로 저장하게 되면 BIGINT 타입보다 많은 저장 공간을 필요로 한다. 그렇기 때문에 UUID 를 사용한 컬럼의 저장 공간을 최소화하기 위해 해당 컬럼을 BINARY(16) 타입으로 지정하도록 한다. 다만
     * BINARY 타입이기 때문에 직접 컬럼을 조회할 때는 BIN_TO_UUID 를 통해 조회해야 human-readable 한 값을 얻을 수 있다.
     */
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
