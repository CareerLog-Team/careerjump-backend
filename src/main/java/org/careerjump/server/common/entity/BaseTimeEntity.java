package org.careerjump.server.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseTimeEntity {

    @Column(name = "CREATED_DATE_TIME", updatable = false)
    @CreatedDate
    private LocalDateTime createdDateTime;

    @Column(name = "MODIFIED_DATE_TIME")
    @LastModifiedDate
    private LocalDateTime modifiedDateTime;

}
