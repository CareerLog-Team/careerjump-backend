package org.careerjump.server.file;


import jakarta.persistence.*;
import lombok.*;
import org.careerjump.server.common.entity.BaseTimeEntity;
import org.careerjump.server.user.domain.User;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class File extends BaseTimeEntity {

    @Id
    @Column(name = "FILE_ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String fileId;

    @Column(name = "ORIGINAL_FILE_NAME")
    private String originalFileName;

    @Column(name = "S3_URL")
    private String s3Url;

    @Column(name = "EXTENSION")
    private String extension;

    @Column(name = "FILE_SIZE")
    private String fileSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

}
