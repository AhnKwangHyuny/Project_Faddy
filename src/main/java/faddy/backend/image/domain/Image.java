package faddy.backend.image.domain;

import faddy.backend.global.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id", nullable = false)
    private Long imageId;

    @Column(name = "redis_key", nullable = false)
    private String redisKey;

    @Column(name = "image_name", nullable = false)
    private String imageName;

    @Column(name = "image_size", nullable = false)
    private Long imageSize; // Assuming the size is in bytes

    @Column(name = "image_format", nullable = false)
    private String imageFormat; // e.g., "jpeg", "png", etc.


}
